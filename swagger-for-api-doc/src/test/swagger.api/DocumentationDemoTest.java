package swagger.api;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import swagger.api.model.DemoResponse;
import swagger.api.model.Person;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by J on 16-10-2017.
 */
public class DocumentationDemoTest {

    public static final String API_URL = "http://localhost:8080/APIDocumentation/api/person";
    private static final int SUCCESS_RESPONSE = 200;

    @Test
    public void testGET() throws Exception {
        DocumentationDemo demo=new DocumentationDemo();
        DocumentationDemo.personList.add(CreatePerson());
        Response response = demo.getAllPersons();
        Assert.assertNotNull(response);
        List<Person> personList = (List<Person>) response.getEntity();
        if(personList != null){
            for (Person p :
                    personList) {
                System.out.println("p.toString() = " + p.toString());
            }
        }
    }

    @Test
    public void testCreate() throws Exception {
        DocumentationDemo demo=new DocumentationDemo();
        Response response = demo.createPerson(CreatePerson());
        Assert.assertNotNull(response);
        DemoResponse entity = (DemoResponse) response.getEntity();
        Assert.assertNotNull(entity);
        System.out.println(entity);
        Assert.assertEquals(response.getStatus(), SUCCESS_RESPONSE);
    }

    private Person CreatePerson() {
        Person request = new Person();
        request.setId(System.currentTimeMillis());
        request.setName("JB");
        return request;
    }

    @Test
    @Ignore
    public void testGetAPI() throws Exception {
        Client client = getClient();
        WebResource webResource = client.resource(API_URL);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode response = webResource.get(JsonNode.class);
        Assert.assertNotNull(response);
        List<Person> personList = mapper.readValue(
                mapper.treeAsTokens(response),
                new TypeReference<List<Person>>(){}
        );
        if(personList != null){
            for (Person p :
                    personList) {
                System.out.println("p.toString() = " + p.toString());
            }
        }
        client.destroy();
    }

    @Test
    @Ignore
    public void testCreateAPI() throws Exception {
        Client client = getClient();
        WebResource webResource = client.resource(API_URL+"/create");
        ClientResponse response = webResource.type(MediaType.APPLICATION_JSON).
                post(ClientResponse.class, CreatePerson() );
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
