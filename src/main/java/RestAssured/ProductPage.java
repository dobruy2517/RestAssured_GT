package RestAssured;

/**
 * Created by ihor on 06.01.17.
 */
public class ProductPage {
    private String location;
    private String checkInDate;


    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(String checkInDate) {
        this.checkInDate = checkInDate;
    }

    public ProductPage(){
        super();
    }

    public ProductPage(String location, String checkInDate){
        this.location = location;
        this.checkInDate = checkInDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductPage product = (ProductPage) o;

        if (checkInDate != product.checkInDate) return false;
        return location != null ? location.equals(product.location) : product.location == null;
    }

    @Override
    public int hashCode() {
        return 0;
    }

}
