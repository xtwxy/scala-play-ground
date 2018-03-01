package com.github.xtwxy.scala.playground.actormixed;

import akka.actor.AbstractActor;
import akka.actor.Actor;
import akka.japi.pf.ReceiveBuilder;

import static java.lang.System.out;

public class JavaReceive {
    private final AbstractActor.Receive hello;
    private final AbstractActor.Receive world;

    public JavaReceive(Actor self) {
        this.hello = hello(self);
        this.world = world(self);
    }

    public AbstractActor.Receive initial() {
        return hello;
    }

    private AbstractActor.Receive hello(Actor self) {
        return ReceiveBuilder.create()
                .matchEquals("hello", s -> {
                    out.println("initial => hello.");
                    self.context().become(world.onMessage(), true);
                    self.sender().tell("world", self.self());
                })
                .matchAny(o -> out.println(o.toString()))
                .build();
    }

    private AbstractActor.Receive world(Actor self) {
        return ReceiveBuilder.create()
                .matchAny(o -> {
                    out.println("initial <= hello.");
                    //self.context().unbecome();
                    self.context().become(hello.onMessage(), true);
                    self.sender().tell("hello", self.self());
                })
                .build();
    }
}
