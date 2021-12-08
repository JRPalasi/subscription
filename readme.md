Subscription service of subscription system

Uses spring boot with H2 in memory database and JPA.
Rest API for controller.
SpringDoc for OpenAPI documentation.
Spring security with basic auth. Next step is to implement JWT.

Run with maven plugin spring-boot:run.
Database is populated on start by com.adidas.subscription.DatabaseInitializer. Can be deactivated by removing the 
@Configuration annotation.

Swagger UI
http://localhost:8081/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config