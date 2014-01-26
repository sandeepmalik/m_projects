import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Author: Monika
 * Date  : 1/22/14
 * Time  : 10:18 PM
 */
public class TestUserService {

    private HttpClient httpClient;
    private final Gson gson = new Gson();

    @BeforeClass
    public void init() {
        httpClient = new DefaultHttpClient();
    }

    @AfterClass
    public void destroy() {
        httpClient = null;
    }

    @Test
    public void testBlankUserNameAndPassword() throws IOException {
        // create a new Post request with URL:
        HttpPost postMethod = new HttpPost("http://localhost:8080/user/login");

        // set request headers:
        postMethod.setHeader("Content-Type", "application/json");
        postMethod.setHeader("Accept", "application/json");

        Map<String, String> map = new HashMap<String, String>();
        // serialize to json:
        String requestPayload = gson.toJson(map);

        // set request payload:
        StringEntity stringEntity = new StringEntity(requestPayload);
        postMethod.setEntity(stringEntity);

        // get the http response:
        HttpResponse httpResponse = httpClient.execute(postMethod);

        // get the status code:
        int statusCode = httpResponse.getStatusLine().getStatusCode();

        // compare the status code:
        Assert.assertEquals(statusCode, 400);

        // get the response body / payload:
        HttpEntity responseBodyEntity = httpResponse.getEntity();
        String responseJson = EntityUtils.toString(responseBodyEntity);

        // deserialize:
        Map<String, String> response = gson.fromJson(responseJson, HashMap.class);
        Assert.assertEquals(response.get("status").toString(), "User id and password are mandatory");
    }

}
