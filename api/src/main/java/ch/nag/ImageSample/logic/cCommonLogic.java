package ch.nag.ImageSample.logic;

import ch.nag.ImageSample.domainModel.MyClub;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

@Stateless
public class cCommonLogic {

    @Inject
    private DataAccessManager data;

    public void saveImage(File file) {

        MyClub myClub = data.getEntityById(MyClub.class, 1);
        if (myClub == null) {

            MyClub newClub = new MyClub();
            newClub.setId(1);
            newClub.setLogo(this.fileToByteArray(file));
            data.persist(newClub);
            return;
        }

        myClub.setLogo(this.fileToByteArray(file));
        data.update(myClub);
    }

    public byte[] getImage() {

        MyClub myClub = data.getEntityById(MyClub.class, 1);
        if (myClub == null)
            return null;

        return myClub.getLogo();
    }

    public void storeImage(byte[] image) {

        MyClub myClub = data.getEntityById(MyClub.class, 1);
        myClub.setLogo(image);
        data.update(myClub);
    }

    private byte[] fileToByteArray(File file) {

        try {
            FileInputStream inputStream = new FileInputStream(file);
            byte[] fileAsArray = new byte[(int)file.length()];
            inputStream.read(fileAsArray);
            inputStream.close();
            return fileAsArray;
        }
        catch (Exception ex) {
            return null;
        }
    }

    private File byteArrayToFile(byte[] fileAsArray) {

        try {
            File tmpFile = File.createTempFile("tmp", "tmp", null);
            FileOutputStream outputStream = new FileOutputStream(tmpFile);
            outputStream.write(fileAsArray);
            outputStream.close();
            return tmpFile.getAbsoluteFile();
        }
        catch (Exception ex) {
            return null;
        }
    }
}
