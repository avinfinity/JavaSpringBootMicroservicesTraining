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