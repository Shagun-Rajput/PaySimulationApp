package com.qbtechlabs.loadbalancer.enums;

public enum NumberEnum {
        MINUS_ONE(-1),
        ZERO(0),
        ONE(1);

        //*******************Method below, used to get the value of the enum constant******************//
        private final int value;
        NumberEnum(int value) {
            this.value = value;
        }
        public int value() {
            return value;
        }
}
