package entities;

public class Tour {
    //id, name, description, persons_number, price, hot, tour_type_id, hotel_type_id
    private int id;
    private String name;
    private String description;
    private int personsNumber;
    private int price;
    private int hot;
    private String tourType;
    private int hotelType;

    @Override
    public String toString() {
        return "Tour{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", persons_number=" + personsNumber +
                ", price=" + price +
                ", hot=" + hot +
                ", tourTypeId=" + tourType +
                ", hotelType=" + hotelType +
                '}';
    }

    public Tour(int id, String name, String description, int personsNumber,
                int price, int hot, String tourType, int hotelType) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.personsNumber = personsNumber;
        this.price = price;
        this.hot = hot;
        this.tourType = tourType;
        this.hotelType = hotelType;
    }

    public Tour(String name, String description, int persons_number,
                int price, int hot, String tourType, int hotelType) {
        this.name = name;
        this.description = description;
        this.personsNumber = persons_number;
        this.price = price;
        this.hot = hot;
        this.tourType = tourType;
        this.hotelType = hotelType;
    }

    public int getId() {
        return id;
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

    public String getTourType() {
        return tourType;
    }

    public void setTourType(String tourType) {
        this.tourType = tourType;
    }

    public int getHotelType() {
        return hotelType;
    }

    public void setHotelType(int hotelType) {
        this.hotelType = hotelType;
    }
}
