package com.qbtechlabs.loadbalancer.enums;

public enum StrategiesEnum {
    ROUND_ROBIN("roundrobin"),
    RANDOM("random"),
    WEIGHTED_ROUND_ROBIN("weightedroundrobin"),
    LEAST_CONNECTIONS("leastconnections");

    //*******************Method below, used to get the value of the enum constant******************//
    private final String value;
    StrategiesEnum(String value) {
        this.value = value;
    }
    public String value() {
        return value;
    }
}
