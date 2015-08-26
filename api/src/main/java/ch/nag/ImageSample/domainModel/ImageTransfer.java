package ch.nag.ImageSample.domainModel;

public class ImageTransfer {

    private byte[] image;
    private int cutLeft;
    private int cutTop;
    private int cutWidth;
    private int cutHeight;
    private int clientWidth;
    private int clientHeight;

    public byte[] getImage() {
        return image;
    }
    public void setImage(byte[] image) {
        this.image = image;
    }

    public int getCutLeft() {
        return cutLeft;
    }
    public void setCutLeft(int cutLeft) {
        this.cutLeft = cutLeft;
    }

    public int getCutTop() {
        return cutTop;
    }
    public void setCutTop(int cutTop) {
        this.cutTop = cutTop;
    }

    public int getCutWidth() {
        return cutWidth;
    }
    public void setCutWidth(int cutWidth) {
        this.cutWidth = cutWidth;
    }

    public int getCutHeight() {
        return cutHeight;
    }
    public void setCutHeight(int cutHeight) {
        this.cutHeight = cutHeight;
    }

    public int getClientWidth() {
        return clientWidth;
    }
    public void setClientWidth(int clientWidth) {
        this.clientWidth = clientWidth;
    }

    public int getClientHeight() {
        return clientHeight;
    }
    public void setClientHeight(int clientHeight) {
        this.clientHeight = clientHeight;
    }
}
