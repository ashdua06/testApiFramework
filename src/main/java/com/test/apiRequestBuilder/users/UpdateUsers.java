package com.test.apiRequestBuilder.users;
import com.test.apiRequestBuilder.APIInterface;
import com.test.exceptions.UserException;
import com.test.global.APIEndpoints;
import com.test.global.LocalConfig;
import com.test.helpers.api.BaseApi;
import com.test.helpers.api.*;
import com.test.model.request.users.UpdateUserRequestPojo;
import com.test.model.response.users.UpdateUserResponsePojo;
import io.restassured.response.Response;
public class UpdateUsers extends BaseApi implements APIInterface {
    private Response response;
    private String request;
    UpdateUserRequestPojo updateRequest;
    UpdateUserResponsePojo updateUserResponsePojo;
    public UpdateUsers(UpdateUserRequestPojo updateUserRequestPojo){
        this.updateRequest=updateUserRequestPojo;
        setMethod(MethodType.PUT);
        setContentType(ContentType.JSON);
        setBaseUri(LocalConfig.PET_STORE_URL);
        setBasePath(APIEndpoints.PET_STORE.UPDATE_USERS);
        addPathParam("username",this.updateRequest.getUserName());
    }

    @Override
    public UpdateUserRequestPojo getRequestPojo() {
        return updateRequest;
    }

    @Override
    public UpdateUserResponsePojo getResponsePojo() {
        return updateUserResponsePojo;
    }

    @Override
    public Response getApiResponse() {
        return response;
    }

    @Override
    public void createRequestJsonAndExecute() {
        try{
            request=JacksonJsonImpl.getInstance().toJSon(this.updateRequest.getRequest());
            setBody(request);
            response=execute();
            updateUserResponsePojo=JacksonJsonImpl.getInstance().fromJson(response.asString(),UpdateUserResponsePojo.class);
        }
        catch (Exception e){
            throw new UserException("Error in update user api ",e);
        }
    }
}
