package ch.nag.ImageSample.api.common;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/common")
public class CommonApi {

    @GET
    @Path("/{message}")
    public String test(@PathParam("message") String message) {
        return "Request succeeded: " + message;
    }

    @GET
    @Path("/getImage")
    public byte[] getImage() {

        return null;
    }
}
