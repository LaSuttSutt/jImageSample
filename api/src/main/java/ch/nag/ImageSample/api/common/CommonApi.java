package ch.nag.ImageSample.api.common;

import ch.nag.ImageSample.domainModel.ImageTransfer;
import ch.nag.ImageSample.logic.CommonLogic;

import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.ws.rs.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

@Path("/common")
public class CommonApi {

    @Inject
    private CommonLogic commonLogic;

    @GET
    @Path("/getImage")
    @Produces("image/jpeg")
    public byte[] getImage() {

        return commonLogic.getImage();
    }

    @POST
    @Path("/saveImage")
    public void saveImage(ImageTransfer imageTransfer) throws IOException {

        InputStream in = new ByteArrayInputStream(imageTransfer.getImage());
        BufferedImage bImageFromConvert = ImageIO.read(in);
        in.close();

        Image scaledImage = bImageFromConvert.getScaledInstance
                (imageTransfer.getClientWidth(), imageTransfer.getClientHeight(), Image.SCALE_AREA_AVERAGING);

        BufferedImage bufferedThumbnail = new BufferedImage
                (imageTransfer.getCutWidth(), imageTransfer.getCutHeight(), BufferedImage.TYPE_INT_ARGB);

        //Graphics2D result = bufferedThumbnail.createGraphics();
        //result.setComposite(AlphaComposite.getInstance(AlphaComposite.CLEAR, 0.0f));
        //result.setColor(new Color(0, 0, 0, 0));
        //result.fillRect(0, 0, bufferedThumbnail.getWidth(), bufferedThumbnail.getHeight());
        //result.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));

        bufferedThumbnail.getGraphics().drawImage(scaledImage, 0, 0,
                imageTransfer.getCutWidth(), imageTransfer.getCutHeight(),
                imageTransfer.getCutLeft(), imageTransfer.getCutTop(),
                imageTransfer.getCutWidth() + imageTransfer.getCutLeft(),
                imageTransfer.getCutTop() + imageTransfer.getCutHeight(), null);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bufferedThumbnail, "png", baos);
        baos.flush();
        commonLogic.storeImage(baos.toByteArray());
        baos.close();
    }

    @POST
    @Path("/storeImage")
    public void storeImage(ImageTransfer imageTransfer) {

        commonLogic.storeImage(imageTransfer.getImage());
    }
}

class Test {
    private String name;
    private byte[] image;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public byte[] getImage() {
        return image;
    }
    public void setImage(byte[] image) {
        this.image = image;
    }
}
