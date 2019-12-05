package images;

public interface IPosterDBService {

    void addImage(String path);
    Poster readImage(String imgName);

}
