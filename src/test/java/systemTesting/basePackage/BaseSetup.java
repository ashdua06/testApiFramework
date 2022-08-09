package systemTesting.basePackage;

import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.test.helpers.utils.*;
import com.test.helpers.api.JacksonJsonImpl;
import com.test.helpers.report.ReportHelper;
import com.test.helpers.utils.JsonFileUtils;

import java.util.List;

public class BaseSetup extends ReportHelper {


    public List<Object> getTestDataFromSheet(String workBook, String sheetName){
        List<Object> excelData=ExcelUtils.getInstance().getSheetDataAsList(workBook,sheetName);
        return excelData;
    }
    public List<Object> getTestDataFromJsonFile(String jsonFileName){
        List<Object> jsonData= JsonFileUtils.getInstance().getTestDataFromJsonFile(jsonFileName);
        return jsonData;
    }
    public <T> List<T> getTestData(String jsonFileName, Class<T> clas) {
        List<Object> jsonData = getTestDataFromJsonFile(jsonFileName);
        try {
            CollectionType typeReference =
                    TypeFactory.defaultInstance().constructCollectionType(List.class, clas);
            List<T> li= JacksonJsonImpl.getInstance().fromJsonArray(jsonData.toString(),typeReference);
            return li;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    //Get Test Data
    public <T> List<T> getTestData(String workBook, String sheetName, Class<T> clas) {
        List<Object> excelData = getTestDataFromSheet(workBook, sheetName);
        try {
            CollectionType typeReference =
                    TypeFactory.defaultInstance().constructCollectionType(List.class, clas);
            List<T> li= JacksonJsonImpl.getInstance().fromJsonArray(excelData.toString(),typeReference);
            return li;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
