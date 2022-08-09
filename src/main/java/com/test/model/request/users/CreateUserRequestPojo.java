package com.test.model.request.users;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import com.test.apiRequestBuilder.APIInterface;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateUserRequestPojo implements APIInterface.RequestPojo{
    private List<Request> request;
    private TestMeta testMeta;
    private String testCaseId;
    private String active_test;
    private List<String> test_tag;

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Request {
        private long id;
        private String username;
        private String firstName;
        private String lastName;
        private String email;
        private String password;
        private String phone;
        private int userStatus;
    }
    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class TestMeta{
        private Map<String,String> tcDescription;
        private int expectedStatusCode;
        private String expectedType;
        private String expectedMessage;
        private int expectedCode;
    }
}
