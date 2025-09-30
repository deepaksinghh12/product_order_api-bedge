# Product Order Management API

Simple Spring Boot REST API to manage Products, Customers and Orders (H2 in-memory DB).

## Run
1. Requirements: Java 17, Maven, IntelliJ (recommended)
2. Build: mvn clean package
3. Run: mvn spring-boot:run or run ProductOrderApplication in IDE
4. H2 console: http://localhost:8080/h2-console (jdbc:h2:mem:productdb, user=sa, password=)
   
## Endpoints
- POST /api/products
- GET  /api/products
- POST /api/customers
- POST /api/orders
- GET  /api/orders/{id}
- PUT  /api/orders/{id}/cancel
