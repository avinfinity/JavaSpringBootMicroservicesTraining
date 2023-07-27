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

In microservices architecture it is comon to have many many microservices & we might havr to change configurations of a specific microservice 
OR for all microservice at once. Doing that manually by visiting application.properties for each microservice is time consuming & practically impossible
Hence we need to use design pattern which allows us to use configurations centrally

it is implemneted using cloud config server

Steps for cloud config server

1. Create a new spring boot project
2. Add appropriate dependencies 
3.  Add @NableConfigServer anotation in application class
4. change port to 8888
5. specify configuration location (by default config server assumes a git repository)
6. Alternatively you can save configurations to local files 

----entries added in application.properties----------
#this property tells config server to search configuration in local system
spring.profiles.active=native
spring.cloud.config.server.native.search-locations=file:C://Training//CentralConfiguration
---------------------------

Config server by default exposes rest point for diferent properties which can be consumed by config client

-- This is the URL for application properties for default profile
http://localhost:8888/application/default  


--Below endpoint is very much specific to each microservice
http://localhost:8888/product-service/default

Each microservice acts as client to config server

Steps for config client
1. Add apropriate depdendcy 
2. configure application.properties to specify the URL of config server







