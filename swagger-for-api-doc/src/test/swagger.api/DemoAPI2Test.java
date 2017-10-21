package swagger.api;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import swagger.api.model.DemoRequest;
import swagger.api.model.DemoResponse;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by J on 16-10-2017.
 */
public class DemoAPI2Test {

    public static final String API_URL = "http://localhost:8080/APIDocumentation/api/message";
    public static final String GET_MESSAGE = "Message from my API";
    private static final int SUCCESS_RESPONSE = 200;

    @Test
    public void testGET() throws Exception {
        DemoAPI2 demo=new DemoAPI2();
        System.out.println("**********Message ="+demo.getMessage());
        Assert.assertSame(demo.getMessage(), GET_MESSAGE);
    }

    @Test
    public void testCreate() throws Exception {
        DemoAPI2 demo=new DemoAPI2();
        Response response = demo.createMessage(CreateDemoRequest());
        Assert.assertNotNull(response);
        DemoResponse entity = (DemoResponse) response.getEntity();
        Assert.assertNotNull(entity);
        System.out.println(entity);
        Assert.assertEquals(response.getStatus(), SUCCESS_RESPONSE);
    }

    private DemoRequest CreateDemoRequest() {
        DemoRequest request = new DemoRequest();
        request.setMessage("Message to create");
        request.setType("dummyType");
        return request;
    }

    @Test
    @Ignore
    public void testGetAPI() throws Exception {
        Client client = getClient();
        WebResource webResource = client.resource(API_URL+"/get");
        String response = webResource.get(String.class);
        System.out.println("response = " + response);
        Assert.assertEquals(response, GET_MESSAGE);
        client.destroy();
    }

    @Test
    @Ignore
    public void testCreateAPI() throws Exception {
        Client client = getClient();
        WebResource webResource = client.resource(API_URL+"/create");
        ClientResponse response = webResource.type(MediaType.APPLICATION_JSON).
                post(ClientResponse.class,CreateDemoRequest() );
        Assert.assertNotNull(response);
        System.out.println("response.getStatus() = " + response.getStatus());
        DemoResponse respEntity = response.getEntity(DemoResponse.class);
        Assert.assertNotNull(respEntity);
        Assert.assertEquals(respEntity.getResponse_code(), SUCCESS_RESPONSE);
        client.destroy();
    }

    private Client getClient() {
        ClientConfig clientConfig = new DefaultClientConfig();
        clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
        return Client.create(clientConfig);
    }

}
