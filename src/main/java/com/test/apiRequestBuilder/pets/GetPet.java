package com.test.apiRequestBuilder.pets;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.test.apiRequestBuilder.APIInterface;
import com.test.exceptions.PetException;
import com.test.global.APIEndpoints;
import com.test.global.LocalConfig;
import com.test.helpers.api.BaseApi;
import com.test.helpers.api.*;
import com.test.model.request.pets.GetPetRequestPojo;
import com.test.model.response.pets.GetPetResponsePojo;
import io.restassured.response.Response;

import java.util.List;

public class GetPet extends BaseApi implements APIInterface{
    private Response response;
    GetPetRequestPojo getPetRequestPojo;
    List<GetPetResponsePojo> getPetResponsePojoList;

    public GetPet(GetPetRequestPojo getPetRequest){
        this.getPetRequestPojo=getPetRequest;
        setMethod(MethodType.GET);
        setContentType(ContentType.JSON);
        setBaseUri(LocalConfig.PET_STORE_URL);
        setBasePath(APIEndpoints.PET_STORE.GET_PET_BY_STATUS);
        addQueryParam("status",getPetRequestPojo.getStatus());
    }

    @Override
    public GetPetRequestPojo getRequestPojo() {
        return getPetRequestPojo;
    }

    @Override
    public ResponsePojo getResponsePojo() {
        return null;
    }

    public List<GetPetResponsePojo> getResponsePojoList(){
        return getPetResponsePojoList;
    }

    @Override
    public Response getApiResponse() {
        return response;
    }

    @Override
    public void createRequestJsonAndExecute() {
        try{
            response=execute();
            CollectionType typeReference = TypeFactory.defaultInstance().constructCollectionType(List.class, GetPetResponsePojo.class);
            getPetResponsePojoList= JacksonJsonImpl.getInstance().fromJsonArray(response.asString(),typeReference);
        }
        catch (Exception e){
            throw new PetException("Error in get pet api ",e);
        }
    }
}
