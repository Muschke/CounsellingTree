package com.example.CounselingTree.exception;

public class ExistsAlreadyInDatabaseException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    public ExistsAlreadyInDatabaseException(String errormessage){
        super(errormessage);
    }
}
