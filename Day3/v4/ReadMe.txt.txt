Tis demo is using OpenFeign & Eureka service discovery, API Gateway, Load Balancer
Note : No harcoding of microservices url is done.

How to make sure client hit the same URL


URL will route to appropriate microservices
common functionalities across each microservice


Steps to include a API gatway

1. Create a spring boot application
2. Add dependency - API Gateway & Eureka client
3. Configure API gateway to work with Eureka server - to find microservices by their logical names
4. make appropriate changes in each microservices , so that they communicate via API gateway
 	4.1 In Feign client name should be of API Gateway
	4.2 in methods of feign client, prefix URI with microservice name as registered with Eureka
	4.3 - while accessing microservices using api gateway we have to follow given URL pattern
	localhost:{API gateway PORT}/{Service name}/{Service URI}
	for example : localhost:9090/PRODUCT-SERVICE/api/product

