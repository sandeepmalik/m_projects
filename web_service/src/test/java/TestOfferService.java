import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.personal.Error;
import com.personal.Offer;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Author: Monika
 * Date  : 1/26/14
 * Time  : 5:38 PM
 */
public class TestOfferService {
    private HttpClient httpClient;
    private final Gson gson = new GsonBuilder().setDateFormat("MM/dd/yyyy").create();
    private Properties errorMessages;
    private Properties serverProperties;

    @BeforeClass
    public void init() throws Exception {
        errorMessages = new Properties();
        errorMessages.load(this.getClass().getClassLoader().getResourceAsStream("error_messages.properties"));
        serverProperties = new Properties();
        serverProperties.load(this.getClass().getClassLoader().getResourceAsStream("server.properties"));
        // make HTTP client:
        httpClient = new DefaultHttpClient();
    }

    @Test
    public void testOfferCreation() throws Exception {
        // Step 1: read the json content from requestJson:
        String requestJson = Utils.fileContent("offer1.json");

        // Step 2: de serialize the JSON content to java object:
        Offer newOffer = gson.fromJson(requestJson, Offer.class);

        // create a new Post request with URL:
        HttpPost postMethod = new HttpPost("http://" + serverProperties.getProperty("server_url") + "/offer/create");

        // set request headers:
        postMethod.setHeader("Content-Type", serverProperties.getProperty("content_type"));
        postMethod.setHeader("Accept", serverProperties.getProperty("accept"));

        // set request payload:
        StringEntity stringEntity = new StringEntity(requestJson);
        postMethod.setEntity(stringEntity);

        // send the call to server and get the http response:
        HttpResponse httpResponse = httpClient.execute(postMethod);

        // get the status code:
        int statusCode = httpResponse.getStatusLine().getStatusCode();

        // compare the status code:
        Assert.assertEquals(statusCode, 200);

        // get the response body / payload:
        HttpEntity responseBodyEntity = httpResponse.getEntity();
        String responseJson = EntityUtils.toString(responseBodyEntity);

        // de serialize server json response:
        Offer offerFromServer = gson.fromJson(responseJson, Offer.class);

        Assert.assertNotNull(offerFromServer.getId());

        newOffer.setId(offerFromServer.getId());

        Assert.assertEquals(newOffer, offerFromServer);
    }

    @Test
    public void testXXX() {
        Offer offer = new Offer();
        offer.setType("My Type");
        System.out.println(offer);

        // serialization:
        String json = gson.toJson(offer);

        System.out.println(json);

        // de-serialization:
        Offer offer1 = gson.fromJson(json, Offer.class);
        System.out.println(offer1);
    }

    @Test
    public void testOfferCreation1() throws Exception {
        String requestJson = Utils.fileContent("offer2.json");
        Offer newOffer = gson.fromJson(requestJson, Offer.class);
        HttpPost postMethod = new HttpPost("http://" + serverProperties.getProperty("server_url") + "/offer/create");
        postMethod.setHeader("Content-Type", serverProperties.getProperty("content_type"));
        postMethod.setHeader("Accept", serverProperties.getProperty("accept"));
        StringEntity stringEntity = new StringEntity(requestJson);
        postMethod.setEntity(stringEntity);
        HttpResponse httpResponse = httpClient.execute(postMethod);
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode, 200);
        HttpEntity responseBodyEntity = httpResponse.getEntity();
        String responseJson = EntityUtils.toString(responseBodyEntity);
        Offer offerFromServer = gson.fromJson(responseJson, Offer.class);
        Assert.assertNotNull(offerFromServer.getId());
        newOffer.setId(offerFromServer.getId());
        Assert.assertEquals(newOffer, offerFromServer);
    }

    @Test
    public void testEndDateRequiredIfStartDatePresent() throws Exception {
        String requestJson = Utils.fileContent("offer_with_no_end_date.json");
        Offer newOffer = gson.fromJson(requestJson, Offer.class);
        HttpPost postMethod = new HttpPost("http://" + serverProperties.getProperty("server_url") + "/offer/create");
        postMethod.setHeader("Content-Type", serverProperties.getProperty("content_type"));
        postMethod.setHeader("Accept", serverProperties.getProperty("accept"));
        StringEntity stringEntity = new StringEntity(requestJson);
        postMethod.setEntity(stringEntity);
        HttpResponse httpResponse = httpClient.execute(postMethod);
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode, 400);
        HttpEntity responseBodyEntity = httpResponse.getEntity();
        String responseJson = EntityUtils.toString(responseBodyEntity);
        com.personal.Error error = gson.fromJson(responseJson, Error.class);
        Assert.assertEquals(error.getMessage(), errorMessages.getProperty("end_date_required"));
    }

    @Test
    public void testEndDateGreaterThanStartDate() throws Exception {
        /*Offer offer = new Offer();
        offer.setStartDate(new Date(2014,01,13));
        offer.setEndDate(new Date(2014,01,11));
        String requestJson = gson.toJson(offer);*/


        String requestJson = Utils.fileContent("offer_with_end_date_less_than_start_date.json");
        Offer newOffer = gson.fromJson(requestJson, Offer.class);
        HttpPost postMethod = new HttpPost("http://" + serverProperties.getProperty("server_url") + "/offer/create");
        postMethod.setHeader("Content-Type", serverProperties.getProperty("content_type"));
        postMethod.setHeader("Accept", serverProperties.getProperty("accept"));
        StringEntity stringEntity = new StringEntity(requestJson);
        postMethod.setEntity(stringEntity);
        HttpResponse httpResponse = httpClient.execute(postMethod);
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode, 400);
        HttpEntity responseBodyEntity = httpResponse.getEntity();
        String responseJson = EntityUtils.toString(responseBodyEntity);
        com.personal.Error error = gson.fromJson(responseJson, Error.class);
        Assert.assertEquals(error.getMessage(), errorMessages.getProperty("end_date_bigger_than_start_date"));
    }

    @Test
    public void testNonExistentOffer() throws Exception {
        httpClient = new DefaultHttpClient();
        HttpGet getMethod = new HttpGet("http://" + serverProperties.getProperty("server_url") + "/offer?id=some_id");
        getMethod.setHeader("Accept", serverProperties.getProperty("accept"));
        HttpResponse httpResponse = httpClient.execute(getMethod);
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode, 404);
        HttpEntity responseEntity = httpResponse.getEntity();
        String responseJson = EntityUtils.toString(responseEntity);
        com.personal.Error error = gson.fromJson(responseJson, Error.class);
        Assert.assertEquals(error.getMessage(), errorMessages.getProperty("not_found_offer"));
    }

    @Test
    public void testOfferPersistent() throws Exception {

        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", serverProperties.getProperty("content_type"));
        headers.put("Accept", serverProperties.getProperty("accept"));

        Map<String, Offer> offers = new HashMap<String, Offer>();

        for (int i = 0; i < 3; i++) {
            Offer offer = new Offer();
            offer.setType("Type" + i);
            // serialization:
            String request = gson.toJson(offer);
            // http exchange:
            String response = Utils.process(httpClient, headers, "post", request, "http://" + serverProperties.getProperty("server_url") + "/offer/create");
            // de serialize:
            Offer returnedOffer = gson.fromJson(response, Offer.class);
            Assert.assertNotNull(returnedOffer.getId());
            offers.put(returnedOffer.getId(), returnedOffer);
        }

        Assert.assertEquals(offers.size(), 3);

        for (String offerId : offers.keySet()) {
            Offer offer = offers.get(offerId);
            String response = Utils.process(httpClient, headers, "get", null, "http://" + serverProperties.getProperty("server_url") + "/offer?id=" + offerId);
            // de serialize:
            Offer returnedOffer = gson.fromJson(response, Offer.class);
            Assert.assertEquals(offer, returnedOffer);
        }
    }
}
