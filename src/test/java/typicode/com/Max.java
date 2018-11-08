package typicode.com;

import com.jayway.jsonpath.JsonPath;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import static io.restassured.RestAssured.given;

class Max {

    /**
     * @param key
     * @return
     */
    public static int getMax(String key) {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        RequestSpecification httpRequest = given();
        Response response = httpRequest.request(Method.GET, "/posts");
        String responseBody = response.getBody().asString();
        String JSON = responseBody;
        List <Integer> ids = JsonPath.read(JSON, "$.[*]." + key + "");
        Integer max = 0;
        for (Integer i : ids) {
            if (max < i) {
                max = i;
            }
        }
        return max;
    }

    public static int getMaxIdForUser(String id, int userid) {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        RequestSpecification httpRequest = given();
        Response response = httpRequest.request(Method.GET, "/posts/?userId=" + userid);
        String responseBody = response.getBody().asString();
        String JSON = responseBody;
        List <Integer> ids = JsonPath.read(JSON, "$.[*]." + id + "");
        Integer max = 0;
        for (Integer i : ids) {
            if (max < i) {
                max = i;
            }
        }
        return max;
    }


    public static String sendPostRequest(String requestUrl, String payload) {
        try {
            URL url = new URL(requestUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
            writer.write(payload);
            writer.close();
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuffer jsonString = new StringBuffer();
            String line;
            while ((line = br.readLine()) != null) {
                jsonString.append(line);
            }
            br.close();
            connection.disconnect();
            return jsonString.toString();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }
}