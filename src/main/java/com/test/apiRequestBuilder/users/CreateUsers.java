package com.test.apiRequestBuilder.users;

import com.test.apiRequestBuilder.APIInterface;
import com.test.exceptions.UserException;
import com.test.global.APIEndpoints;
import com.test.global.LocalConfig;
import com.test.helpers.api.BaseApi;
import com.test.helpers.api.*;
import com.test.model.request.users.CreateUserRequestPojo;
import com.test.model.request.users.UserRequestPojo;
import com.test.model.response.users.CreateUsersResponsePojo;
import io.restassured.response.Response;

import java.util.List;
import java.util.stream.Collectors;

public class CreateUsers extends BaseApi implements APIInterface {
    private Response response;
    private String request;
    CreateUserRequestPojo createUserRequestPojo;
    CreateUsersResponsePojo createUsersResponsePojo;

    public CreateUsers(CreateUserRequestPojo createUserRequest){
        this.createUserRequestPojo=createUserRequest;
        setMethod(MethodType.POST);
        setContentType(ContentType.JSON);
        setBaseUri(LocalConfig.PET_STORE_URL);
        setBasePath(APIEndpoints.PET_STORE.CREATE_MULTIPLE_USERS);
    }

    @Override
    public CreateUserRequestPojo getRequestPojo() {
        return createUserRequestPojo;
    }

    @Override
    public CreateUsersResponsePojo getResponsePojo() {
        return createUsersResponsePojo;
    }

    @Override
    public Response getApiResponse() {
        return response;
    }

    @Override
    public void createRequestJsonAndExecute() {
        try{
            this.request=JacksonJsonImpl.getInstance().toJSon(createUserRequestPojo.getRequest());
            setBody(request);
            response=execute();
            createUsersResponsePojo=JacksonJsonImpl.getInstance().fromJson(response.asString(),CreateUsersResponsePojo.class);
        }
        catch (Exception e){
            throw new UserException("Error in create multiple users api ",e);
        }
    }
}
