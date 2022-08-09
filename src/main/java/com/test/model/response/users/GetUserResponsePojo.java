package com.test.model.response.users;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import com.test.apiRequestBuilder.APIInterface;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetUserResponsePojo implements APIInterface.ResponsePojo{
    private long id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private int userStatus;
    private int code;
    private String type;
    private String message;

    @Override
    public boolean isSuccess() {
        return false;
    }

    @Override
    public Object getErrors() {
        return null;
    }

    @Override
    public Object getBody() {
        return null;
    }
}

