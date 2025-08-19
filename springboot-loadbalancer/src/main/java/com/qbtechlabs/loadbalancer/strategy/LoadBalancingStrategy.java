package com.example.loadbalancer.strategy;

import com.example.loadbalancer.domain.Server;
import java.util.List;

public interface LoadBalancingStrategy {
    Server selectServer(List<Server> servers);
}