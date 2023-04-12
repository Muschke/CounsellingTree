package com.example.CounselingTree.exception;

public class InvalidLevelCodeException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public InvalidLevelCodeException(String errormessage){
        super(errormessage);
    }
}
