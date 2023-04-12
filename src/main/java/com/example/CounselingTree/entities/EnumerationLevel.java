package com.example.CounselingTree.entities;

import com.example.CounselingTree.exception.InvalidLevelCodeException;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "enumerationlevels")
public class EnumerationLevel {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank
    private String code;
    @NotBlank
    private String description;

    public EnumerationLevel(String code, String description) {
        setCode(code);
        setDescription(description);
    }

    protected EnumerationLevel(){};

    private void setCode(String code){
           if(!code.matches("^[A-F]\\d$")){
                throw new InvalidLevelCodeException("Invalid code, first charachter must be captial A-F, second has to be digit");
           }
          this.code = code;
    }

    private void setDescription(String description){
        if(description.isEmpty() || description.isBlank()){
            throw new IllegalArgumentException("Description cannot be blank or empty");
        }
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
