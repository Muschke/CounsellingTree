package com.example.CounselingTree.exception;

public class InvalidCounselorException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public InvalidCounselorException(String errormessage){
        super(errormessage);
    }
}
