package com.test.apiRequestBuilder.users;
import com.test.apiRequestBuilder.APIInterface;
import com.test.exceptions.UserException;
import com.test.global.APIEndpoints;
import com.test.global.LocalConfig;
import com.test.helpers.api.BaseApi;
import com.test.helpers.api.*;
import com.test.model.request.users.GetUserRequestPojo;
import com.test.model.response.users.GetUserResponsePojo;
import io.restassured.response.Response;
public class GetUser extends BaseApi implements APIInterface {
    private Response response;
    GetUserRequestPojo getUserRequestPojo;
    GetUserResponsePojo getUserResponsePojo;

    public GetUser(GetUserRequestPojo getUserRequest){
        this.getUserRequestPojo=getUserRequest;
        setMethod(MethodType.GET);
        setContentType(ContentType.JSON);
        setBaseUri(LocalConfig.PET_STORE_URL);
        setBasePath(APIEndpoints.PET_STORE.GET_USERS);
        addPathParam("username",this.getUserRequestPojo.getUserName());
    }

    @Override
    public GetUserRequestPojo getRequestPojo() {
        return getUserRequestPojo;
    }

    @Override
    public GetUserResponsePojo getResponsePojo() {
        return getUserResponsePojo;
    }

    @Override
    public Response getApiResponse() {
        return response;
    }

    @Override
    public void createRequestJsonAndExecute() {
        try{
            response=execute();
            getUserResponsePojo=JacksonJsonImpl.getInstance().fromJson(response.asString(),GetUserResponsePojo.class);
        }
        catch (Exception e){
            throw new UserException("Error in get users api ",e);
        }
    }
}
