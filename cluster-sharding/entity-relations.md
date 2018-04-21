# entity relation.

By taking DDD approach, entities are modeled as Akka Persistent Actors.

The drawbacks with this approach is that modeling relations between entities & bi-directional navigating is not easy, while it's quite easy with entity-relationship model and relational databases.

## solution 1: CQRS
  
## solution 2: piples & filter
for small amount of relations.

```
class Entity extends PersistentActor {
...
}
```

```
class ClusteredEntity extends Actor {
// start clustered sharding of Entity
...

  override def receive: Receive = {
    case x: Command =>
      filter(x)      // filter for something interesting...
      sharding forward x
    case _ =>
  }
}
```



