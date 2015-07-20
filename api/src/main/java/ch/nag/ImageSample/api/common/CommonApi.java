package ch.nag.ImageSample.api.common;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.io.File;
import java.net.URL;

@Path("/common")
public class CommonApi {

    @GET
    @Path("/{message}")
    public String test(@PathParam("message") String message) {
        return "Request succeeded: " + message;
    }

    @GET
    @Path("/getImage")
    public File getImage() {

        ClassLoader classLoader = getClass().getClassLoader();

        URL url = classLoader.getResource("files/TUS.png");
        if (url == null)
            return null;

        String loadedFileString = url.getFile();
        if (loadedFileString == null)
            return null;

        return new File(loadedFileString);
    }
}
