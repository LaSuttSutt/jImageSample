package ch.nag.ImageSample.domainModel;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table
public class MyClub {

    @Id
    private int id;

    @Lob
    private byte[] logo;


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public byte[] getLogo() {
        return logo;
    }
    public void setLogo(byte[] logo) {
        this.logo = logo;
    }
}
