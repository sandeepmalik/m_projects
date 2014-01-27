import com.google.gson.Gson;
import com.personal.Offer;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Author: Monika
 * Date  : 1/26/14
 * Time  : 5:38 PM
 */
public class TestOfferService {
    private HttpClient httpClient;
    private final Gson gson = new Gson();

    @Test
    public void testOfferCreation() throws Exception {
        String file = Utils.fileContent("offer1.json");

        Offer newOffer = gson.fromJson(file, Offer.class);

        httpClient = new DefaultHttpClient();
        //    Assert.assertNotNull();


        // create a new Post request with URL:
        HttpPost postMethod = new HttpPost("http://localhost:8080/offer/create");

        // set request headers:
        postMethod.setHeader("Content-Type", "application/json");
        postMethod.setHeader("Accept", "application/json");

        // set request payload:
        StringEntity stringEntity = new StringEntity(file);
        postMethod.setEntity(stringEntity);

        // get the http response:
        HttpResponse httpResponse = httpClient.execute(postMethod);

        // get the status code:
        int statusCode = httpResponse.getStatusLine().getStatusCode();

        // compare the status code:
        Assert.assertEquals(statusCode, 200);

        // get the response body / payload:
        HttpEntity responseBodyEntity = httpResponse.getEntity();
        String responseJson = EntityUtils.toString(responseBodyEntity);
        Offer offer = gson.fromJson(responseJson, Offer.class);

        Assert.assertNotNull(offer.getId());
        Assert.assertEquals(offer.getType(), newOffer.getType());
    }


}
