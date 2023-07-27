This demo is using OpenFeign & Eureka service discovery, API Gateway, Load Balancer, Resilience4j

It is very common in microservices architecture to call REST calls
it is quite possible that REST calls might fail
That leads to cascading failures (one service failure causes dependent services to also fail), which must be avoided. 
And our systems must be resilient to failures

In order to solve this problem , we have to use next design pattern - Circuit Beaker

It can be implemented by resilence4j (in old version this hystrix was used)

Steps to implement resilence4j 

1. Add deendency of resilience4j 
2. confgure appropriate properties
3. write a fallback method 

NOTE : Fallback signature must excatly match to the method which is annotated with circuit beaker annotation.
In addition to that we need one more extra argument as Throwable t

