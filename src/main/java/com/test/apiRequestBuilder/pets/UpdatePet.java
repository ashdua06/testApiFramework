package com.test.apiRequestBuilder.pets;
import com.test.apiRequestBuilder.APIInterface;
import com.test.exceptions.PetException;
import com.test.global.APIEndpoints;
import com.test.global.LocalConfig;
import com.test.helpers.api.BaseApi;
import com.test.helpers.api.*;
import com.test.model.request.pets.UpdatePetRequestPojo;
import com.test.model.response.pets.UpdatePetResponsePojo;
import io.restassured.response.Response;
public class UpdatePet extends BaseApi implements APIInterface{
    private Response response;
    private String request;
    UpdatePetRequestPojo updatePetRequestPojo;
    UpdatePetResponsePojo updatePetResponsePojo;

    public UpdatePet(UpdatePetRequestPojo updatePetRequest){
        this.updatePetRequestPojo=updatePetRequest;
        setMethod(MethodType.PUT);
        setContentType(ContentType.JSON);
        setBaseUri(LocalConfig.PET_STORE_URL);
        setBasePath(APIEndpoints.PET_STORE.UPDATE_PET);
    }

    @Override
    public UpdatePetRequestPojo getRequestPojo() {
        return updatePetRequestPojo;
    }

    @Override
    public UpdatePetResponsePojo getResponsePojo() {
        return updatePetResponsePojo;
    }

    @Override
    public Response getApiResponse() {
        return response;
    }

    @Override
    public void createRequestJsonAndExecute() {
        try{
            this.request=JacksonJsonImpl.getInstance().toJSon(updatePetRequestPojo.getRequest());
            setBody(request);
            response=execute();
            updatePetResponsePojo=JacksonJsonImpl.getInstance().fromJson(response.asString(),UpdatePetResponsePojo.class);
        }
        catch (Exception e){
            throw new PetException("Error in update pet api ",e);
        }
    }
}
