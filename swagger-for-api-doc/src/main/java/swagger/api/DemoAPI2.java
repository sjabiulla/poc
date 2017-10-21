package swagger.api;

/**
 * Created by J on 16-10-2017.
 */
import io.swagger.annotations.*;
import swagger.api.model.DemoRequest;
import swagger.api.model.DemoResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/message")
@Api(value = "message")
@Produces({MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON})
@SwaggerDefinition(info = @Info(title = "The Demo API",version = "1.0",
        contact = @Contact(
                name = "JB",
                email = "s.jabiulla@gmail.com"
        )), schemes = {SwaggerDefinition.Scheme.HTTP, SwaggerDefinition.Scheme.HTTPS})
public class DemoAPI2 {

    @GET
    @Path("/get")
    @ApiOperation(value = "get the message",
            notes = "This operation returns a default message", response = String.class)
    @ApiResponses(value = {
            @ApiResponse(code=200, message="Success response from API"),
            @ApiResponse(code=400, message="Error occurred in API")
    })
    @Produces(MediaType.TEXT_PLAIN)
    public String getMessage() {
        return "Message from my API";
    }

    @POST
    @Path("/create")
    @ApiOperation(value = "create a message",
            notes = "This operation is used to create a message", response = DemoResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code=200, message="Success response from API"),
            @ApiResponse(code=400, message="Error occurred in API")
    })
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createMessage(
            @ApiParam(name = "request")
            DemoRequest request){
        DemoResponse resp = new DemoResponse();
        System.out.println("request in API = [" + request + "]");
        resp.setError_message("");
        resp.setResponse_code(200);
        return Response.ok(resp).build();
    }



}
