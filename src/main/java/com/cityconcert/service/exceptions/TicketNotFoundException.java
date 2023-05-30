package com.cityconcert.service.exceptions;

public class TicketNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public TicketNotFoundException() {
        super("Ticket not found!");
    }
}
