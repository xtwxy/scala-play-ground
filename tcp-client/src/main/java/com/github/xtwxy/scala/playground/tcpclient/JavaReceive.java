package com.github.xtwxy.scala.playground.tcpclient;

import akka.actor.AbstractActor;
import akka.actor.Actor;
import akka.actor.ActorRef;
import akka.io.Tcp;
import akka.io.TcpMessage;
import akka.japi.pf.ReceiveBuilder;
import akka.util.ByteString;

import java.net.InetSocketAddress;

import static java.lang.System.out;

public class JavaReceive {
    private final Actor self;
    public JavaReceive(Actor self) {
        this.self = self;
    }

    public AbstractActor.Receive initial(InetSocketAddress remote) {
        final ActorRef tcp = Tcp.get(self.context().system()).manager();
        tcp.tell(TcpMessage.connect(remote), self.self());
        return connect(self);
    }

    private AbstractActor.Receive connect(Actor self) {
        return ReceiveBuilder.create()
                .match(Tcp.CommandFailed.class, s -> {
                    out.println("connect failed.");
                    self.context().stop(self.self());
                    self.context().system().terminate();
                })
                .match(Tcp.Connected.class, s -> {
                    out.println("initial => connect.");
                    self.sender().tell(TcpMessage.register(self.self()), self.self());
                    self.context().become(connected(self.sender()).onMessage(), true);
                    ByteString msg = ByteString.fromString("connected.");
                    self.sender().tell(TcpMessage.write(msg), self.self());
                    out.println("sender: " + self.sender().toString());
                })
                .matchAny(o -> out.println("unknown message: " + o.toString()))
                .build();
    }

    private AbstractActor.Receive connected(ActorRef connection) {
        return ReceiveBuilder.create()
                .match(ByteString.class, s -> {
                    out.println("byte string received.");
                    connection.tell(TcpMessage.write(s), self.sender());
                })
                .match(Tcp.Received.class, s-> {
                    out.println("data received.");
                    out.println("sender: " + self.sender().toString());
                    out.println("source: " + connection.toString());
                    connection.tell(TcpMessage.write(s.data()), self.sender());
                })
                .match(Tcp.ConnectionClosed.class, msg -> {
                    out.println("connection closed.");
                    self.context().stop(self.self());
                    self.context().system().terminate();
                })
                .match(Tcp.CommandFailed.class, s -> {
                    out.println("connect failed.");
                    self.context().stop(self.self());
                    self.context().system().terminate();
                })
                .matchAny(o -> {
                    out.println("unknown message: " + o.toString());
                })
                .build();
    }
}
