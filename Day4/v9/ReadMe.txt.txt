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
2. Add a property in application.properties file
3. Annotate the controller with refresh scope anotation 
4. localhost/product-service/actuator/refresh

In above approach , we need to hit refresh endpoint individually for all microservice to reflect the changes
this becomes impractical & time consuming. That's whay we should use Bus Refresh
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


------------------

As microservices are distributed , keeping tracks of logs is a challenge
To do distributed tracing , we use spring-cloud-sleuth

sleuth tracks the request, assigns the unique id & logging it in console by default

Steps to enable sleuth:
1. Add depdedency on Sleuth (search & add distributed tracing)

it is however difficult to track these logs from console. Better if I get consolidated log files
thats's where ZipKin helps us to do this.

Steps for ZipKin:
1. Download Zipkin jar
2. Run Zipkin server
3. Add appropriate dependency in each microservice 
4. Add appropriate property in properties file of each microservice

spring.zipkin.base-url=http://localhost:9411 (this is default zipkin setting)


----------------------------------------------------

this demo uses multiple zones of eureka service 
-----------------------------------------------------














