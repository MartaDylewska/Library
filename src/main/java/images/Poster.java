package images;

import java.util.Arrays;
import java.util.Objects;

public class Poster {

    private byte[] imgBytes;
    private String imgName;

    public Poster(){}

    public Poster(byte[] imgBytes, String imgName) {
        this.imgBytes = imgBytes;
        this.imgName = imgName;
    }

    public byte[] getImgBytes() {
        return imgBytes;
    }

    public void setImgBytes(byte[] imgBytes) {
        this.imgBytes = imgBytes;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Poster)) return false;
        Poster poster = (Poster) o;
        return Arrays.equals(getImgBytes(), poster.getImgBytes()) &&
                Objects.equals(getImgName(), poster.getImgName());
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(getImgName());
        result = 31 * result + Arrays.hashCode(getImgBytes());
        return result;
    }

}
