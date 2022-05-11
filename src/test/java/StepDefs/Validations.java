package StepDefs;

import configManager.DataLoad;
import org.testng.Assert;
import java.util.HashMap;

public class Validations {

    final static String PASS_STATUS = "Pass";
    final static String FAIL_STATUS = "Fail";

    public static void assertEquals(String expected, String actual, String validation){
        String status = FAIL_STATUS;
        if(actual.equalsIgnoreCase(expected)){
            status = PASS_STATUS;
        }
        loadValidation(expected,actual,validation,status);
        Assert.assertEquals(actual,expected);
    }

    public static void assertEquals(boolean expected, boolean actual, String validation){
        String status = FAIL_STATUS;
        if(expected == actual){
            status = PASS_STATUS;
        }
        loadValidation(Boolean.toString(expected),Boolean.toString(actual),validation,status);
        Assert.assertEquals(actual,expected);
    }

    public static void assertEquals(int expected, int actual, String validation){
        String status = FAIL_STATUS;
        if(expected == actual){
            status = PASS_STATUS;
        }
        loadValidation(Integer.toString(expected),Integer.toString(actual),validation,status);
        Assert.assertEquals(actual,expected);
    }


    public static void loadValidation(String expected, String actual, String validation, String status){

        HashMap<String,String> dataValidators = new HashMap<String,String>();

        dataValidators.put("VALIDATION",validation);
        dataValidators.put("EXPECTED",expected);
        dataValidators.put("ACTUAL",actual);
        dataValidators.put("STATUS",status);

        DataLoad dataLoad = DataLoad.getInstance();
        dataLoad.setDataList(dataValidators);
    }

    public static void assertTrue(boolean expected,String validation){
        String status = FAIL_STATUS;
        if(expected == true){
            status = PASS_STATUS;
        }
        Assert.assertTrue(expected,validation);
    }
}
