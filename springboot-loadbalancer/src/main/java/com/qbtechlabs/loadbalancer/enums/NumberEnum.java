package com.qbtechlabs.loadbalancer.enums;

public enum NumberEnum {
        MINUS_ONE(-1),
        ZERO(0),
        ONE(1),
        TWO(2),
        THREE(3),
        FOUR(4),
        FIVE(5);

        //*******************Method below, used to get the value of the enum constant******************//
        private final int value;
        NumberEnum(int value) {
            this.value = value;
        }
        public int value() {
            return value;
        }
}
