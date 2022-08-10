package com.test.validators.api;

import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.test.apiRequestBuilder.pets.CreatePet;
import com.test.apiRequestBuilder.pets.GetPet;
import com.test.apiRequestBuilder.pets.UpdatePet;
import com.test.exceptions.PetException;
import com.test.helpers.api.JacksonJsonImpl;
import com.test.helpers.utils.JsonFileUtils;
import com.test.model.request.pets.GetPetRequestPojo;
import com.test.model.request.pets.UpdatePetRequestPojo;
import com.test.model.response.pets.CreatePetResponsePojo;
import com.test.model.response.pets.GetPetResponsePojo;
import com.test.model.response.pets.UpdatePetResponsePojo;
import com.test.validators.assertions.CustomAssert;

import java.util.List;

public class PetValidator {
    private static volatile PetValidator instance;

    private PetValidator() {
    }

    public static PetValidator getInstance() {
        if (instance == null) {
            synchronized (PetValidator.class) {
                if (instance == null) {
                    instance = new PetValidator();
                }
            }
        }
        return instance;
    }

    public void validateCreatePetResponse(CreatePet obj) {
        try {
            CustomAssert.assertEquals(obj.getApiResponse().getStatusCode(), obj.getRequestPojo().getTestMeta().getExpectedStatusCode(), " HTTP Status code validation");
            if(obj.getRequestPojo().getTestMeta().getExpectedStatusCode()==200) {
                CustomAssert.assertEquals(obj.getResponsePojo().getId(), obj.getRequestPojo().getRequest().getId(), "ID validation in response");
                CustomAssert.assertEquals(obj.getResponsePojo().getStatus(), obj.getRequestPojo().getRequest().getStatus(), "Pet Status validation in response");
                CustomAssert.assertEquals(obj.getResponsePojo().getName(), obj.getRequestPojo().getRequest().getName(), "Pet name validation in response");
                CustomAssert.assertEquals(obj.getResponsePojo().getCategory().getId(), obj.getRequestPojo().getRequest().getCategory().getId(), "Category ID validation");
                CustomAssert.assertEquals(obj.getResponsePojo().getCategory().getName(), obj.getRequestPojo().getRequest().getCategory().getName(), "Category Name validation");
                obj.getRequestPojo().getRequest().getTags().stream().forEach(expectedTag -> {
                    CreatePetResponsePojo.Tags actualTag = obj.getResponsePojo().getTags().stream().filter(tag -> tag.getId() == expectedTag.getId()).findFirst().get();
                    CustomAssert.assertEquals(actualTag.getId(), expectedTag.getId(), "Tag Id validation");
                    CustomAssert.assertEquals(actualTag.getName(), expectedTag.getName(), "Tag name validation");
                });
                CustomAssert.assertEquals(obj.getResponsePojo().getPhotoUrls().size(), obj.getRequestPojo().getRequest().getPhotoUrls().size(), "No of photo urls validation in response");
                obj.getRequestPojo().getRequest().getPhotoUrls().stream().forEach(expectedPhotoUrl -> {
                    CustomAssert.assertTrue(obj.getResponsePojo().getPhotoUrls().contains(expectedPhotoUrl), "photo url exist in response :" + expectedPhotoUrl);
                });
            }
        } catch (Exception e) {
            throw new PetException("Error in validating create pet api response ", e);
        }
    }

    public void validateUpdatePetResponse(UpdatePet obj) {
        try {
            CustomAssert.assertEquals(obj.getApiResponse().getStatusCode(), obj.getRequestPojo().getTestMeta().getExpectedStatusCode(), " HTTP Status code validation");
            if(obj.getRequestPojo().getTestMeta().getExpectedStatusCode()==200) {
                CustomAssert.assertEquals(obj.getResponsePojo().getId(), obj.getRequestPojo().getRequest().getId(), "ID validation in response");
                CustomAssert.assertEquals(obj.getResponsePojo().getStatus(), obj.getRequestPojo().getRequest().getStatus(), "Pet Status validation in response");
                CustomAssert.assertEquals(obj.getResponsePojo().getName(), obj.getRequestPojo().getRequest().getName(), "Pet name validation in response");
                CustomAssert.assertEquals(obj.getResponsePojo().getCategory().getId(), obj.getRequestPojo().getRequest().getCategory().getId(), "Category ID validation");
                CustomAssert.assertEquals(obj.getResponsePojo().getCategory().getName(), obj.getRequestPojo().getRequest().getCategory().getName(), "Category Name validation");
                obj.getRequestPojo().getRequest().getTags().stream().forEach(expectedTag -> {
                    UpdatePetResponsePojo.Tags actualTag = obj.getResponsePojo().getTags().stream().filter(tag -> tag.getId() == expectedTag.getId()).findFirst().get();
                    CustomAssert.assertEquals(actualTag.getId(), expectedTag.getId(), "Tag Id validation");
                    CustomAssert.assertEquals(actualTag.getName(), expectedTag.getName(), "Tag name validation");
                });
                CustomAssert.assertEquals(obj.getResponsePojo().getPhotoUrls().size(), obj.getRequestPojo().getRequest().getPhotoUrls().size(), "No of photo urls validation in response");
                obj.getRequestPojo().getRequest().getPhotoUrls().stream().forEach(expectedPhotoUrl -> {
                    CustomAssert.assertTrue(obj.getResponsePojo().getPhotoUrls().contains(expectedPhotoUrl), "photo url exist in response :" + expectedPhotoUrl);
                });
            }
        } catch (Exception e) {
            throw new PetException("Error in validating update pet api response ", e);
        }
    }

    public void validateGetPetResponse(GetPet obj){
        CustomAssert.assertEquals(obj.getApiResponse().getStatusCode(), obj.getRequestPojo().getTestMeta().getExpectedStatusCode(), " HTTP Status code validation");
        if(obj.getRequestPojo().getTestMeta().getExpectedStatusCode()==200) {
            obj.getRequestPojo().getTestMeta().getExpectedPetId().stream().forEach(petId->{
                UpdatePetRequestPojo.Request expectedPet=getUpdatedPetRequestDetails(petId.longValue());
                GetPetResponsePojo actualPet=obj.getResponsePojoList().stream().filter(pet-> pet.getId()==petId.longValue()).findFirst().get();
                CustomAssert.assertEquals(actualPet.getId(), expectedPet.getId(), "ID validation in response");
                CustomAssert.assertEquals(actualPet.getStatus(), expectedPet.getStatus(), "Pet Status validation in response");
                CustomAssert.assertEquals(actualPet.getName(), expectedPet.getName(), "Pet name validation in response");
                CustomAssert.assertEquals(actualPet.getCategory().getId(), expectedPet.getCategory().getId(), "Category ID validation");
                CustomAssert.assertEquals(actualPet.getCategory().getName(), expectedPet.getCategory().getName(), "Category Name validation");
                expectedPet.getTags().stream().forEach(expectedTag -> {
                    GetPetResponsePojo.Tags actualTag = actualPet.getTags().stream().filter(tag -> tag.getId() == expectedTag.getId()).findFirst().get();
                    CustomAssert.assertEquals(actualTag.getId(), expectedTag.getId(), "Tag Id validation");
                    CustomAssert.assertEquals(actualTag.getName(), expectedTag.getName(), "Tag name validation");
                });
                CustomAssert.assertEquals(actualPet.getPhotoUrls().size(), expectedPet.getPhotoUrls().size(), "No of photo urls validation in response");
                expectedPet.getPhotoUrls().stream().forEach(expectedPhotoUrl -> {
                    CustomAssert.assertTrue(actualPet.getPhotoUrls().contains(expectedPhotoUrl), "photo url exist in response :" + expectedPhotoUrl);
                });
            });
        }
    }

    public UpdatePetRequestPojo.Request getUpdatedPetRequestDetails(long petId){
        List<Object> jsonData= JsonFileUtils.getInstance().getTestDataFromJsonFile("updatePet.json");
        try {
            CollectionType typeReference =
                    TypeFactory.defaultInstance().constructCollectionType(List.class, UpdatePetRequestPojo.class);
            List<UpdatePetRequestPojo> li= JacksonJsonImpl.getInstance().fromJsonArray(jsonData.toString(),typeReference);
            return li.stream().filter(x->x.getRequest().getId()==petId).findFirst().get().getRequest();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}