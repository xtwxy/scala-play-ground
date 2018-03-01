package com.github.xtwxy.scala.playground.actormixed;

import akka.actor.AbstractActor;
import akka.actor.Actor;
import akka.japi.pf.ReceiveBuilder;

import static java.lang.System.out;

public class JavaReceive {
    public static AbstractActor.Receive create(Actor actor) {
        return ReceiveBuilder.create()
                .matchEquals("hello", s -> {
                    out.println("initial => hello.");
                    actor.context().become(hello(actor).onMessage(), true);
                    actor.sender().tell("world", actor.self());
                })
                .matchAny(o -> out.println(o.toString()))
                .build();
    }

    public static AbstractActor.Receive hello(Actor actor) {
        return ReceiveBuilder.create()
                .matchAny(o -> {
                    out.println("initial <= hello.");
                    actor.context().unbecome();
                    actor.sender().tell("hello", actor.self());
                })
                .build();
    }
}
