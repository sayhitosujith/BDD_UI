package util;

import java.util.HashMap;

public class APIHeaders {


    public static HashMap<String, String> defaultHeaders() {

        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json");

        return headers;

    }


}
