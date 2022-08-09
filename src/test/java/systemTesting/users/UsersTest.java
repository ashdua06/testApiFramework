package systemTesting.users;

import com.test.apiRequestBuilder.users.CreateUsers;
import com.test.apiRequestBuilder.users.GetUser;
import com.test.apiRequestBuilder.users.UpdateUsers;
import com.test.global.Groups;
import com.test.model.request.users.CreateUserRequestPojo;
import com.test.model.request.users.GetUserRequestPojo;
import com.test.model.request.users.UpdateUserRequestPojo;
import com.test.validators.api.UserValidator;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import systemTesting.basePackage.BaseSetup;

import java.util.List;
import java.util.Map;

public class UsersTest extends BaseSetup {
    @DataProvider
    public Object[][] getCreateUserDataProvider() {
          List<CreateUserRequestPojo> testData=getTestData("createUser.json", CreateUserRequestPojo.class);
        Object[][] data = new Object[testData.size()][2];
        for (int i = 0; i < testData.size(); i++) {
            data[i][0] = testData.get(i);
            data[i][1] = testData.get(i).getTestMeta().getTcDescription();
        }
        return data;
    }

    @Test(dataProvider = "getCreateUserDataProvider",priority = 0, groups = {Groups.SMOKE,Groups.REGRESSION})
    public void validateCreateUsersAPi(CreateUserRequestPojo createUserRequest, Map<String,String> tcDescription) {
        CreateUsers createUsers=new CreateUsers(createUserRequest);
        createUsers.createRequestJsonAndExecute();
        UserValidator.getInstance().validateCreateUserResponse(createUsers);
    }



    @DataProvider
    public Object[][] getUpdateUserDataProvider() {
        List<UpdateUserRequestPojo> testData=getTestData("updateUser.json", UpdateUserRequestPojo.class);
        Object[][] data = new Object[testData.size()][2];
        for (int i = 0; i < testData.size(); i++) {
            data[i][0] = testData.get(i);
            data[i][1] = testData.get(i).getTestMeta().getTcDescription();
        }
        return data;
    }

    @Test(dataProvider = "getUpdateUserDataProvider",priority = 1, groups = {Groups.SMOKE,Groups.REGRESSION})
    public void validateUpdateUsersAPi(UpdateUserRequestPojo updateUserRequestPojo, Map<String,String> tcDescription) {
        UpdateUsers updateUsers=new UpdateUsers(updateUserRequestPojo);
        updateUsers.createRequestJsonAndExecute();
        UserValidator.getInstance().validateUpdateUserResponse(updateUsers);
    }


    @DataProvider
    public Object[][] getUserDataProvider() {
        List<GetUserRequestPojo> testData=getTestData("getUser.json", GetUserRequestPojo.class);
        Object[][] data = new Object[testData.size()][2];
        for (int i = 0; i < testData.size(); i++) {
            data[i][0] = testData.get(i);
            data[i][1] = testData.get(i).getTestMeta().getTcDescription();
        }
        return data;
    }

    @Test(dataProvider = "getUserDataProvider",priority = 2, groups = {Groups.SMOKE,Groups.REGRESSION})
    public void validateGetUsersAPi(GetUserRequestPojo getUserRequestPojo, Map<String,String> tcDescription) {
        GetUser getUser=new GetUser(getUserRequestPojo);
        getUser.createRequestJsonAndExecute();
        UserValidator.getInstance().validateGetUserResponse(getUser);
    }



}
