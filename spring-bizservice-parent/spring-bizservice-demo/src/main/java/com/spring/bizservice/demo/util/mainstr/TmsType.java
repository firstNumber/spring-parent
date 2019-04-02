package com.spring.bizservice.demo.util.mainstr;

public enum TmsType {
    CUSTOMER("C"),
    WAREHOUSE("H"),
    STORAGE("W"),
    STORE("S"),
    STORE_ADDRESS("A"),
    SUPPLIER("U"),
    SUPPLIER_ADDRESS("E"),
    ORDER("G");
    private String value;

    TmsType(String value) {
        this.value = value;
    }

    public String getCode() {
        return this.value;
    }
}
