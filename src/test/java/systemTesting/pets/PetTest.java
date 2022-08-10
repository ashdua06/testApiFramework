package systemTesting.pets;

import com.test.apiRequestBuilder.pets.CreatePet;
import com.test.apiRequestBuilder.pets.GetPet;
import com.test.apiRequestBuilder.pets.UpdatePet;
import com.test.global.Groups;
import com.test.model.request.pets.CreatePetRequestPojo;
import com.test.model.request.pets.GetPetRequestPojo;
import com.test.model.request.pets.UpdatePetRequestPojo;
import com.test.validators.api.PetValidator;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import systemTesting.basePackage.BaseSetup;
import java.util.List;
import java.util.Map;

public class PetTest extends BaseSetup {

    @DataProvider
    public Object[][] getCreatePetDataProvider() {
        List<CreatePetRequestPojo> testData=getTestData("createPet.json", CreatePetRequestPojo.class);
        Object[][] data = new Object[testData.size()][2];
        for (int i = 0; i < testData.size(); i++) {
            data[i][0] = testData.get(i);
            data[i][1] = testData.get(i).getTestMeta().getTcDescription();
        }
        return data;
    }

    @Test(dataProvider = "getCreatePetDataProvider",priority = 0, groups = {Groups.SMOKE,Groups.REGRESSION})
    public void validateCreatePetAPi(CreatePetRequestPojo createPetRequest, Map<String,String> tcDescription) {
        CreatePet createPet=new CreatePet(createPetRequest);
        createPet.createRequestJsonAndExecute();
        PetValidator.getInstance().validateCreatePetResponse(createPet);
    }

    @DataProvider
    public Object[][] getUpdatePetDataProvider() {
        List<UpdatePetRequestPojo> testData=getTestData("updatePet.json", UpdatePetRequestPojo.class);
        Object[][] data = new Object[testData.size()][2];
        for (int i = 0; i < testData.size(); i++) {
            data[i][0] = testData.get(i);
            data[i][1] = testData.get(i).getTestMeta().getTcDescription();
        }
        return data;
    }

    @Test(dataProvider = "getUpdatePetDataProvider",priority = 1, groups = {Groups.SMOKE,Groups.REGRESSION})
    public void validateUpdatePetAPi(UpdatePetRequestPojo updatePetRequest, Map<String,String> tcDescription) {
        UpdatePet updatePet=new UpdatePet(updatePetRequest);
        updatePet.createRequestJsonAndExecute();
        PetValidator.getInstance().validateUpdatePetResponse(updatePet);
    }


    @DataProvider
    public Object[][] getPetDataProvider() {
        List<GetPetRequestPojo> testData=getTestData("getPet.json", GetPetRequestPojo.class);
        Object[][] data = new Object[testData.size()][2];
        for (int i = 0; i < testData.size(); i++) {
            data[i][0] = testData.get(i);
            data[i][1] = testData.get(i).getTestMeta().getTcDescription();
        }
        return data;
    }

    @Test(dataProvider = "getPetDataProvider",priority = 2, groups = {Groups.SMOKE,Groups.REGRESSION})
    public void validateGetPetAPi(GetPetRequestPojo getPetRequest, Map<String,String> tcDescription) {
        GetPet getPet=new GetPet(getPetRequest);
        getPet.createRequestJsonAndExecute();
        PetValidator.getInstance().validateGetPetResponse(getPet);
    }
}
