package com.exam.account.model;

public enum AccountType {

    AHORROS, CORRIENTE;

    public static AccountType getAccountType(Integer accountType) {
        if (accountType == AHORROS.ordinal()) {
            return AHORROS;
        } else if (accountType == CORRIENTE.ordinal()) {
            return CORRIENTE;
        } else {
            return null;
        }
    }
}
