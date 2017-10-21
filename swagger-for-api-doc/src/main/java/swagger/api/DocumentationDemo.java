package swagger.api;

/**
 * Created by J on 16-10-2017.
 */
import io.swagger.annotations.*;
import swagger.api.model.DemoResponse;
import swagger.api.model.Person;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/person")
@Api(value = "person")
@Produces({MediaType.APPLICATION_JSON})
@SwaggerDefinition(info = @Info(title = "The Demo API for Person",version = "1.0",
        contact = @Contact(
                name = "JB",
                email = "s.jabiulla@gmail.com"
        )), schemes = {SwaggerDefinition.Scheme.HTTP, SwaggerDefinition.Scheme.HTTPS})
public class DocumentationDemo {

    public static List<Person> personList = new ArrayList<>();

    @GET
    @ApiOperation(value = "get all person details",
            notes = "This operation returns all person details available", response = Person.class)
    @ApiResponses(value = {
            @ApiResponse(code=200, message="Success response from API"),
            @ApiResponse(code=400, message="Error occurred in API")
    })
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPersons() {
        if(personList.size() > 0){
            return Response.ok(personList).build();
        }
        return Response.ok().build();
    }

    @POST
    @Path("/create")
    @ApiOperation(value = "create a person",
            notes = "This operation is used to create a person", response = DemoResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code=200, message="Success response from API"),
            @ApiResponse(code=400, message="Error occurred in API")
    })
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createPerson(
            @ApiParam(name = "request")
            Person request){
        DemoResponse resp = new DemoResponse();
        System.out.println("request in API = [" + request + "]");
        personList.add(request);
        resp.setError_message("");
        resp.setResponse_code(200);
        return Response.ok(resp).build();
    }



}
