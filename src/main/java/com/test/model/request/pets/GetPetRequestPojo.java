package com.test.model.request.pets;
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
public class GetPetRequestPojo implements APIInterface.RequestPojo{
    private String status;
    private TestMeta testMeta;
    private String testCaseId;
    private String active_test;
    private List<String> test_tag;

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class TestMeta{
        private Map<String,String> tcDescription;
        private int expectedStatusCode;
        private List<Long> expectedPetId;
    }
}
