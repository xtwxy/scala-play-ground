# stream actor integration

# using of `Source.actorRef` is not recommended

All the problems that can be soled by `Source.actorRef`
can also be solved by `Source.queue`.

There are stream completion problem in `Source.actorRef`.
By the documentation, to complete the stream normally, 
just send `akka.actor.Status.Success` to the actor. 
However, sending `akka.actor.Status.Success` will teardown
the stream, leaving downstream unfinished.
This is a mistake.

Do not use `Source.actorRef`, use `Source.queue` instead.