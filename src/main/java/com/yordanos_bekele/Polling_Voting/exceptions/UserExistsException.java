package com.yordanos_bekele.Polling_Voting.exceptions;

public class UserExistsException extends RuntimeException{

    public UserExistsException(String message){
        super(message);
    }
}
