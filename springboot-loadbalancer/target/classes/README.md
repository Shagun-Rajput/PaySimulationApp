# Load Balancer Application

This Spring Boot application implements a load balancer that supports multiple load balancing algorithms. The application is designed to distribute incoming requests across a set of backend servers based on the selected strategy.

## Features

- **Load Balancing Algorithms**: Supports the following algorithms:
  - Round Robin
  - Random
  - Weighted Round Robin
  - Least Connections

- **Configuration**: The load balancing strategy can be configured via the `application.yml` file.

- **Health Checks**: The application includes a health check service that monitors the status of backend servers.

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

2. **Build the application**:
   ```
   mvn clean install
   ```

3. **Run the application**:
   ```
   mvn spring-boot:run
   ```

4. **Configure the backend servers** in `application.yml` and select the desired load balancing strategy.

## Health Check

The application periodically checks the health of backend servers to ensure that requests are only sent to alive servers. 

## Contributing

Contributions are welcome! Please submit a pull request or open an issue for any enhancements or bug fixes.