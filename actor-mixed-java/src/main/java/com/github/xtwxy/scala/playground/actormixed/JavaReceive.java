package com.github.xtwxy.scala.playground.actormixed;

import akka.actor.AbstractActor;
import akka.actor.Actor;
import akka.japi.pf.ReceiveBuilder;

import static java.lang.System.out;

public class JavaReceive {
    public static AbstractActor.Receive create(Actor self) {
        return ReceiveBuilder.create()
                .matchEquals("hello", s -> {
                    out.println("initial => hello.");
                    self.context().become(hello(self).onMessage(), true);
                    self.sender().tell("world", self.self());
                })
                .matchAny(o -> out.println(o.toString()))
                .build();
    }

    public static AbstractActor.Receive hello(Actor self) {
        return ReceiveBuilder.create()
                .matchAny(o -> {
                    out.println("initial <= hello.");
                    self.context().unbecome();
                    self.sender().tell("hello", self.self());
                })
                .build();
    }
}
