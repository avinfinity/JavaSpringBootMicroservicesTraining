Each microservice has it's own database

Use REST to communicate between microservices 

OpenFeign:
Making REST call is common requirement in Microservice architecture which we can implement using Rest Template but this is traditional approach
To make REST call declarative, spring cloud offers library known as OpenFeign

Steps to use OpenFeign:
1. Add dependency
2. Create an interface and annotate with @FeignClient annotation 
3. Add appropriate attributed to the annotation
4. Declare methods with the same signature matching the remote method we want to call having same URI (but method name can be different)
5. Declared methods must have matching http URI and method
6. Enable Feign client by annotating application class using @EnableFeignClients annotation
---------------------------------------------------------------------------------------------------
To scale up a specific microservice to able to run on any random unused port, so that we do not hardcode portin application.properties fiel. To achieve, we have to set the port to '0' to server.port property.
In OpenFeign interface we are adding hardcode url that creates 2 problem:
We do not know the port of coupon service is running on.
Loading balancing will not happen. Alays send load to hardcoded url.
Hence hardcoded url must be avoided.
This is where we can use another design pattern "service discovery". Each microservice register itself to the service registry with an unique name. then that microservice can be accessed with the unique name.
The most popular and respected implmentation of service discovery is "Eureka" from Netflix.
		
Eureka
- Steps for Eureka Server
	1) Create new SpringBoot project and Add Eureka server dependency
	2) Annonate application class with @EnableEurekaServer
	3) Chnage the port to 8761 (not mandate but recommended)
- Stpes for Eureka Client
	1) Add EurekaClient dependency
	2) Annonate Application class with @EnableEurekaClient(optional but reccomended for readability)
	3) Specify url for eureka server (Opional if we are choosing default port and ip for eureka but reccomended for readability)
			eureka.client.service-url.defaultZone=http://localhost:8761/eureka

when you have multiple instance of a microservice registered with Eureka , you must make sure
that each instance has a separate id associated with it

In order to do this, we need to add following in each of EurekaClient (microservice)

eureka.instance.instance-id=${spring.application.name}:${random.value}

Eureka server acts as client to eureka , hence it registers itself as eureka service

eureka.client.register-with-eureka=false


