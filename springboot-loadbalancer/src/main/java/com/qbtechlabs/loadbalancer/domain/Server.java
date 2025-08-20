package com.qbtechlabs.loadbalancer.domain;

import com.qbtechlabs.loadbalancer.enums.NumberEnum;

/**
 * @author shagun.rajput
 */
public class Server {
    private String url;
    private boolean alive;
    private int weight;
    private int activeConnections;
    public Server(String url, int weight) {
        this.url = url;
        this.weight = weight;
        this.alive = Boolean.FALSE;
        this.activeConnections = NumberEnum.ZERO.value();
    }
    public String getUrl() {
        return url;
    }
    public boolean isAlive() {
        return alive;
    }
    public void setAlive(boolean alive) {
        this.alive = alive;
    }
    public int getWeight() {
        return weight;
    }
    public int getActiveConnections() {
        return activeConnections;
    }
    public void setActiveConnections(int activeConnections) {
        this.activeConnections = activeConnections;
    }
}