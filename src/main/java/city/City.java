package city;

public class City {
    private String postalCode;
    private String cityName;

    public City(String postalCode, String cityName) {
        this.postalCode = postalCode;
        this.cityName = cityName;
    }

    public City() {
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @Override
    public String toString() {
        return postalCode +
                " " + cityName;
    }
}
