package util;

import configManager.DataLoad;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class GenericUtil {


    public String getFileData(String path){
        return readFileAsString(path);
    }


    public String jsonConstruct(Map<String,String> jsonMap, String json){

        String finalJson = json;

        Iterator<String> keyIterator   = jsonMap.keySet().iterator();

        while(keyIterator.hasNext()){
            String aKey   = keyIterator.next();
            String  aValue = jsonMap.get(aKey);
            finalJson = finalJson.replace("<"+aKey+">",aValue);
        }

        DataLoad dataLoad = DataLoad.getInstance();
        dataLoad.setRequest(finalJson);
        return finalJson;

    }

    public String readFileAsString(String file)
    {
        String fileContents = "";
        try{
            fileContents = new String(Files.readAllBytes(Paths.get(file)));
        }catch (Exception e){
            e.printStackTrace();
        }

        return fileContents;
    }




    public String[][] getReportData(List<HashMap<String,String>> dataList){

        String[][] data = new String[dataList.size()+1][4];

        data[0][0] = "VALIDATION";
        data[0][1] = "EXPECTED";
        data[0][2] = "ACTUAL";
        data[0][3] = "STATUS";

        for(int i=0;i<dataList.size();i++){
            data[i+1][0] = dataList.get(i).get("VALIDATION");
            data[i+1][1] = dataList.get(i).get("EXPECTED");
            data[i+1][2] = dataList.get(i).get("ACTUAL");
            data[i+1][3] = dataList.get(i).get("STATUS");
        }

        return  data;
    }

}
