# Testing Akka Actors

## Testing 'plain' Akka Actors


## Testing Akka Actor project with Google Guice DI

## Testing Akka Actor project with Cluster, Google Guice DI
- Don't start seed node in test specs. using dedicated seed node(s) instead, or else binding tcp port will fail.
- Choose different port for each test spec, since test specs run in concurrency. 
- To avoid hang tests, call `cluster.leave(address)` and `cluster.down(address)` before end of each test. if not, the seed node(s) will try to re-contact `cluster member(s)` started in test code, holding your tests. what's more, there're overwhelming amount of annoying log messages states that cluster member(s) is out of reaching.

 
