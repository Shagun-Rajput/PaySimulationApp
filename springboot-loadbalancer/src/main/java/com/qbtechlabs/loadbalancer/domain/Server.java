package com.qbtechlabs.loadbalancer.domain;

public class Server {
    private String url;
    private boolean alive;
    private int weight;
    private int activeConnections;

    public Server(String url, int weight) {
        this.url = url;
        this.weight = weight;
        this.alive = true; // Default to alive when created
        this.activeConnections = 0; // Default to 0 active connections
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getActiveConnections() {
        return activeConnections;
    }

    public void setActiveConnections(int activeConnections) {
        this.activeConnections = activeConnections;
    }
}