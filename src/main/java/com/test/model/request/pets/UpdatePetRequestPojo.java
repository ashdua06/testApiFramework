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
public class UpdatePetRequestPojo implements APIInterface.RequestPojo{
    private Request request;
    private TestMeta testMeta;
    private String testCaseId;
    private String active_test;
    private List<String> test_tag;

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Request{
        private long id;
        private Category category;
        private String name;
        private List<String> photoUrls;
        private List<Tags> tags;
        private String status;
    }

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Category{
        private long id;
        private String name;
    }

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Tags{
        private long id;
        private String name;
    }

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class TestMeta{
        private Map<String,String> tcDescription;
        private int expectedStatusCode;
    }
}
