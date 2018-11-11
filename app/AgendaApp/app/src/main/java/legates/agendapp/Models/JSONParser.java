package legates.agendapp.Models;

import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class JSONParser {

    public static JSONArray getKey(Response response, String key){
        JSONObject reader = new JSONObject();
        try {
            reader = new JSONObject(response.body().string());
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONArray json = new JSONArray();
        try {
            json = reader.getJSONArray(key);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return json;
    }


}
