package magdamiu.com.sqlitebasicexample.model;

public class Country {

    private int countryId;
    private String name;
    private String town;

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    @Override
    public String toString() {
        return "Country{" +
                "countryId=" + countryId +
                ", name='" + name + '\'' +
                ", town='" + town + '\'' +
                '}';
    }

    public Country(int countryId, String name, String town) {
        this.countryId = countryId;
        this.name = name;
        this.town = town;
    }



    public Country() {
    }
}
