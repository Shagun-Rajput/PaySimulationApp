# Spring Boot Load Balancer

This project is a Spring Boot application that implements a load balancer supporting multiple load balancing algorithms. The application can dynamically switch between different strategies based on configuration settings.

## Features

- **Load Balancing Algorithms**: Supports Round Robin, Random, Weighted Round Robin, and Least Connections strategies.
- **Dynamic Configuration**: Load balancing strategy can be configured via `application.yml`.
- **Health Monitoring**: Includes a health check service to monitor the status of backend servers.
- **RESTful API**: Exposes a REST API to handle incoming client requests and route them to the appropriate backend server.

## Project Structure

```
springboot-loadbalancer
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── example
│   │   │           └── loadbalancer
│   │   │               ├── LoadBalancerApplication.java
│   │   │               ├── config
│   │   │               │   └── LoadBalancerConfig.java
│   │   │               ├── controller
│   │   │               │   └── ProxyController.java
│   │   │               ├── domain
│   │   │               │   └── Server.java
│   │   │               ├── strategy
│   │   │               │   ├── LoadBalancingStrategy.java
│   │   │               │   ├── RoundRobinStrategy.java
│   │   │               │   ├── RandomStrategy.java
│   │   │               │   ├── WeightedRoundRobinStrategy.java
│   │   │               │   └── LeastConnectionsStrategy.java
│   │   │               ├── factory
│   │   │               │   └── StrategyFactory.java
│   │   │               ├── service
│   │   │               │   ├── LoadBalancerService.java
│   │   │               │   └── HealthCheckService.java
│   │   └── resources
│   │       ├── application.yml
│   │       └── README.md
├── pom.xml
└── README.md
```

## Getting Started

1. **Clone the repository**:
   ```
   git clone <repository-url>
   cd springboot-loadbalancer
   ```

2. **Build the project**:
   ```
   mvn clean install
   ```

3. **Run the application**:
   ```
   mvn spring-boot:run
   ```

4. **Configure the backend servers** in `src/main/resources/application.yml`:
   ```yaml
   loadbalancer:
     servers:
       - url: http://localhost:8081
         weight: 1
       - url: http://localhost:8082
         weight: 2
     strategy: round-robin # Options: round-robin, random, weighted-round-robin, least-connections
   ```

5. **Access the API**:
   Send requests to the load balancer endpoint, which will route them to the appropriate backend server based on the selected strategy.

## Contributing

Contributions are welcome! Please open an issue or submit a pull request for any enhancements or bug fixes.

## License

This project is licensed under the MIT License. See the LICENSE file for details.