package com.springpoo2023.exceptions;

public class StatusNotFoundException extends Exception{

    private static final long serialVersionUID = -592155226854524751L;

    public StatusNotFoundException() {
        super("Subscription status is not valid");
    }
}
