package com.test.apiRequestBuilder.pets;
import com.test.apiRequestBuilder.APIInterface;
import com.test.exceptions.PetException;
import com.test.global.APIEndpoints;
import com.test.global.LocalConfig;
import com.test.helpers.api.BaseApi;
import com.test.helpers.api.*;
import com.test.model.request.pets.CreatePetRequestPojo;
import com.test.model.response.pets.CreatePetResponsePojo;
import io.restassured.response.Response;
public class CreatePet extends BaseApi implements APIInterface{
    private Response response;
    private String request;
    CreatePetRequestPojo createPetRequestPojo;
    CreatePetResponsePojo createPetResponsePojo;

    public CreatePet(CreatePetRequestPojo createPetRequest){
        this.createPetRequestPojo=createPetRequest;
        setMethod(MethodType.POST);
        setContentType(ContentType.JSON);
        setBaseUri(LocalConfig.PET_STORE_URL);
        setBasePath(APIEndpoints.PET_STORE.CREATE_PET);
    }

    @Override
    public CreatePetRequestPojo getRequestPojo() {
        return createPetRequestPojo;
    }

    @Override
    public CreatePetResponsePojo getResponsePojo() {
        return createPetResponsePojo;
    }

    @Override
    public Response getApiResponse() {
        return response;
    }

    @Override
    public void createRequestJsonAndExecute() {
        try{
            this.request=JacksonJsonImpl.getInstance().toJSon(createPetRequestPojo.getRequest());
            setBody(request);
            response=execute();
            createPetResponsePojo=JacksonJsonImpl.getInstance().fromJson(response.asString(),CreatePetResponsePojo.class);
        }
        catch (Exception e){
            throw new PetException("Error in create pet api ",e);
        }
    }
}
