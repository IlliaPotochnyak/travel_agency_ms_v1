package entities;

public class Tour {
    //id, name, description, persons_number, price, hot, tour_type_id, hotel_type_id
    private String name;
    private String description;
    private int personsNumber;
    private int price;
    private int hot;
    private int tourTypeId;
    private int hotelTypeId;

    @Override
    public String toString() {
        return "Tour{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", persons_number=" + personsNumber +
                ", price=" + price +
                ", hot=" + hot +
                ", tour_type_id=" + tourTypeId +
                ", hotel_type_id=" + hotelTypeId +
                '}';
    }

    public Tour(String name, String description, int persons_number, int price, int hot, int tour_type_id, int hotel_type_id) {
        this.name = name;
        this.description = description;
        this.personsNumber = persons_number;
        this.price = price;
        this.hot = hot;
        this.tourTypeId = tour_type_id;
        this.hotelTypeId = hotel_type_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPersonsNumber() {
        return personsNumber;
    }

    public void setPersonsNumber(int personsNumber) {
        this.personsNumber = personsNumber;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getHot() {
        return hot;
    }

    public void setHot(int hot) {
        this.hot = hot;
    }

    public int getTourTypeId() {
        return tourTypeId;
    }

    public void setTourTypeId(int tourTypeId) {
        this.tourTypeId = tourTypeId;
    }

    public int getHotelTypeId() {
        return hotelTypeId;
    }

    public void setHotelTypeId(int hotelTypeId) {
        this.hotelTypeId = hotelTypeId;
    }
}
