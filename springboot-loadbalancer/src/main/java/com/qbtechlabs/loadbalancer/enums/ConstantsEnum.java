package com.qbtechlabs.loadbalancer.enums;

public enum ConstantsEnum {
    TEST("test");

    //*******************Method below, used to get the value of the enum constant******************//
    private final String value;
    ConstantsEnum(String value) {
        this.value = value;
    }
    public String value() {
        return value;
    }
}
