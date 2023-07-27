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

Dynamic loading for config server:
Updating the config , requires to restart the config server & services
This is not practical. Hence we want any upate to config to be availabe dynamically without starting microservice

In order to do this, we need to follow below steps:

1. Add Actuator dependency 
What is actuator : it offers management port endpoints whch can be accessed by developers internally.
Actuator is mainly used to expose operational information about the running application — health, metrics, info, dump, env, etc. 
It uses HTTP endpoints or JMX beans to enable us to interact with it. 
Once this dependency is on the classpath, several endpoints are available for us out of the box.

2. Add a property in application.properties file
3. Annotate the controller with refresh scope anotation 
4. localhost/product-service/actuator/refresh

In above approach , we need to hit refresh endpoint individually for all microservice to reflect the changes
this becomes impractical & time consuming. That's whay we should use Bus Refresh (offeered by spring.cloud.bus)
this is used to broadcast state changes ex: configuration changes 
Spring cloud bus links microservices of a distributed system with a message broker 

Steps for Bus Refresh:

1. Add appropriate dependency 

<dependency>
<groupId>org.springframework.cloud</groupId>
<artifactId>spring-cloud-starter-bus-amqp</artifactId>
</dependency>

2. add busrefresh property
#Actutaor setting to refresh the config 
management.endpoints.web.exposure.include=refresh,busrefresh

Add appropriate properties for message broker 

spring.rabbitmq.host=localhost
spring.rabbitmq.port=5672
spring.rabbitmq.username=guest
spring.rabbitmq.password=guest

Above properties are not mandatorily , if using default configuration. 
If however these are chanegs then above properties needs to be used

Like in production environment


3. Download & Install Rabbitmq  & open cmd & enable rabbitmq mangement plugin

3.1 rabbitmq-plugins enable rabbitmq_management
3.2 run rabbitmq server : rabbitmq-server.bat

3.3 open in browser : http://localhost:15672/

default username password is guest



Config service is picking , as of now from local 
to make it pick from git is:

----Commenting out the local storage settings in config server--------
#this property tells config server to search configuration in local system
#spring.profiles.active=native
#spring.cloud.config.server.native.search-locations=file:C://Training//JavaSpringBootMicroservicesTraining//CentralConfiguration/
------------------------------------
#enabling git repo to pick the config settings
spring.cloud.config.server.git.uri=https://github.com/avinfinity/ConfigRepo.git


config can have various profiles (dev, prod, test etc.)
you can active various profiles as 

-Dspring.profiles.active=dev

All config files with this profile in name would get picked

















