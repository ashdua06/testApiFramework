package com.test.model.response.pets;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import com.test.apiRequestBuilder.APIInterface;
import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreatePetResponsePojo implements APIInterface.ResponsePojo{
    private long id;
    private Category category;
    private String name;
    private List<String> photoUrls;
    private List<Tags> tags;
    private String status;

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

    @Override
    public int getCode() {
        return 0;
    }
    @Override
    public String getType() {
        return null;
    }
    @Override
    public String getMessage() {
        return null;
    }
}
