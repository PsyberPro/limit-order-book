# limit-order-book

How to run the Limit order book application:
1. Build the project using maven.
2. Run limit-order-book/infrastructure/eureka (Service registry and discovery), using local profile and VM arguments: -Dboot.port=9000 -Dlogger.level=INFO
3. Run limit-order-book/application/repository using local profile and VM arguments: -Dboot.port=9001 -Dlogger.level=INFO
4. Run limit-order-book/application/processor using local profile and VM arguments: -Dboot.port=9002 -Dlogger.level=INFO


Modules:
1. Application module of limit-order-book contain the actual application implimentation
   * processor module contain the implementation of the limit-order-book
   * repository module contain the database layer for the implementation of the limit-order-book
   
2. Infrastructure module of limit-order-book contain the supporting apps
   * eureka module is the application level load balancer (service registry and discovery)
   * config-server is the module that had the application configs (No need to start it, it is not completed)
