package com.test.validators.api;


import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.test.apiRequestBuilder.users.CreateUsers;
import com.test.apiRequestBuilder.users.GetUser;
import com.test.apiRequestBuilder.users.UpdateUsers;
import com.test.exceptions.UserException;
import com.test.helpers.api.JacksonJsonImpl;
import com.test.helpers.utils.JsonFileUtils;
import com.test.model.request.users.UpdateUserRequestPojo;
import com.test.validators.assertions.CustomAssert;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;

public class UserValidator {
    private static volatile UserValidator instance;

    private UserValidator() {
    }

    public static UserValidator getInstance() {
        if (instance == null) {
            synchronized (UserValidator.class) {
                if (instance == null) {
                    instance = new UserValidator();
                }
            }
        }
        return instance;
    }

    public void validateCreateUserResponse(CreateUsers obj) {
        try {
            CustomAssert.assertEquals(obj.getApiResponse().getStatusCode(), obj.getRequestPojo().getTestMeta().getExpectedStatusCode(), " HTTP Status code validation");
            CustomAssert.assertEquals(obj.getResponsePojo().getCode(), obj.getRequestPojo().getTestMeta().getExpectedCode(), " Code Validation in response");
            CustomAssert.assertEquals(obj.getResponsePojo().getType(), obj.getRequestPojo().getTestMeta().getExpectedType(), " Type Validation in response");
            CustomAssert.assertEquals(obj.getResponsePojo().getMessage(), obj.getRequestPojo().getTestMeta().getExpectedMessage(), " Message Validation in response");
        } catch (Exception e) {
            throw new UserException("Error in validating create multiple users api response ", e);
        }
    }

    public void validateUpdateUserResponse(UpdateUsers obj) {
        try {
            CustomAssert.assertEquals(obj.getApiResponse().getStatusCode(), obj.getRequestPojo().getTestMeta().getExpectedStatusCode(), " HTTP Status code validation");
            CustomAssert.assertEquals(obj.getResponsePojo().getCode(), obj.getRequestPojo().getTestMeta().getExpectedCode(), " Code Validation in response");
            CustomAssert.assertEquals(obj.getResponsePojo().getType(), obj.getRequestPojo().getTestMeta().getExpectedType(), " Type Validation in response");
            CustomAssert.assertEquals(obj.getResponsePojo().getMessage(), String.valueOf(obj.getRequestPojo().getRequest().getId()), " Message Validation in response");
        } catch (Exception e) {
            throw new UserException("Error in validating update users api response ", e);
        }
    }

    public void validateGetUserResponse(GetUser obj){
        try {
            CustomAssert.assertEquals(obj.getApiResponse().getStatusCode(), obj.getRequestPojo().getTestMeta().getExpectedStatusCode(), " HTTP Status code validation");
            if(obj.getApiResponse().getStatusCode()==200){
                UpdateUserRequestPojo.Request expectedDetails=getUpdatedRequestDetails(obj.getRequestPojo().getUserName());
                CustomAssert.assertEquals(obj.getResponsePojo().getId(), expectedDetails.getId(), " Id Validation in response");
                CustomAssert.assertEquals(obj.getResponsePojo().getUsername(), expectedDetails.getUsername(), "UserName Validation in response");
                CustomAssert.assertEquals(obj.getResponsePojo().getFirstName(), expectedDetails.getFirstName(), "FirstName Validation in response");
                CustomAssert.assertEquals(obj.getResponsePojo().getLastName(), expectedDetails.getLastName(), "LastName Validation in response");
                CustomAssert.assertEquals(obj.getResponsePojo().getEmail(), expectedDetails.getEmail(), "Email Validation in response");
                CustomAssert.assertEquals(obj.getResponsePojo().getPassword(), expectedDetails.getPassword(), "Password Validation in response");
                CustomAssert.assertEquals(obj.getResponsePojo().getPhone(), expectedDetails.getPhone(), "Phone Validation in response");
                CustomAssert.assertEquals(obj.getResponsePojo().getUserStatus(), expectedDetails.getUserStatus(), "UserStatus Validation in response");
            }
            else {
                CustomAssert.assertEquals(obj.getResponsePojo().getCode(), obj.getRequestPojo().getTestMeta().getExpectedCode(), " Code Validation in response");
                CustomAssert.assertEquals(obj.getResponsePojo().getType(), obj.getRequestPojo().getTestMeta().getExpectedType(), " Type Validation in response");
                CustomAssert.assertEquals(obj.getResponsePojo().getMessage(), obj.getRequestPojo().getTestMeta().getExpectedMessage(), " Message Validation in response");
            }
        } catch (Exception e) {
            throw new UserException("Error in validating Get users api response ", e);
        }
    }

    public UpdateUserRequestPojo.Request getUpdatedRequestDetails(String userName){
        List<Object> jsonData= JsonFileUtils.getInstance().getTestDataFromJsonFile("updateUser.json");
        try {
            CollectionType typeReference =
                    TypeFactory.defaultInstance().constructCollectionType(List.class, UpdateUserRequestPojo.class);
            List<UpdateUserRequestPojo> li= JacksonJsonImpl.getInstance().fromJsonArray(jsonData.toString(),typeReference);
            return li.stream().filter(x->x.getRequest().getUsername().equals(userName)).findFirst().get().getRequest();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}