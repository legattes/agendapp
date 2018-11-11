package legates.agendapp.Models;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.Map;

public class Service {
    private OkHttpClient client = new OkHttpClient();
    private FormEncodingBuilder requestBodyBuilder;
    private RequestBody requestBody;
    private Request request;
    private Response response;

    public Response post(Map<String, String> values, String url) {
        requestBodyBuilder = new FormEncodingBuilder();

        for(String key : values.keySet()){
            requestBodyBuilder.add(key, values.get(key));
        }

        requestBody = requestBodyBuilder.build();

        request = new Request.Builder()
                .url(url)
                .method("POST", requestBody)
                .header("Content-Length", "0")
                .build();

        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {

        }
        return response;
    }

    public Response get(String url) {
        request = new Request.Builder()
                .url(url)
                .build();

        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {

        }
        return response;
    }
}
