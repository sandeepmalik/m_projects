import com.sun.xml.internal.ws.message.StringHeader;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.rmi.server.Operation;
import java.util.Map;

/**
 * Author: Monika
 * Date  : 1/26/14
 * Time  : 5:40 PM
 */
public class Utils {

    public static String fileContent(String fileName) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(Utils.class.getClassLoader().getResourceAsStream(fileName)));
            String line;
            StringBuilder stringBuilder = new StringBuilder();
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String process(HttpClient httpClient, Map<String, String> headers, String operation, String requestPayload, String serverUrl) throws Exception {
        if (operation.equals("post")) {
            HttpPost httpPost = new HttpPost(serverUrl);
            for (String headerName : headers.keySet()) {
                String headerValue = headers.get(headerName);
                httpPost.setHeader(headerName, headerValue);
            }
            StringEntity stringEntity = new StringEntity(requestPayload);
            httpPost.setEntity(stringEntity);
            HttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity responseBodyEntity = httpResponse.getEntity();
            String responseJson = EntityUtils.toString(responseBodyEntity);
            return responseJson;
        } else if (operation.equals("get")) {
            HttpGet httpPost = new HttpGet(serverUrl);
            for (String headerName : headers.keySet()) {
                String headerValue = headers.get(headerName);
                httpPost.setHeader(headerName, headerValue);
            }
            HttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity responseBodyEntity = httpResponse.getEntity();
            String responseJson = EntityUtils.toString(responseBodyEntity);
            return responseJson;
        } else {
            throw new IllegalArgumentException("Invalid operation " + operation);
        }
    }
}

