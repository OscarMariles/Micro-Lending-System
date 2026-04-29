package com.microlending.micro_lending_system.exception;

public class InstallmentAlreadyPaid extends RuntimeException {
    public InstallmentAlreadyPaid(String message) {
        super(message);
    }
}
