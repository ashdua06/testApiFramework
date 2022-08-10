package com.test.model.response.users;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import com.test.apiRequestBuilder.APIInterface;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpdateUserResponsePojo implements APIInterface.ResponsePojo {
    private int code;
    private String type;
    private String message;
}

