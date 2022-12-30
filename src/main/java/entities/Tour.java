package entities;

public class Tour {
    //id, name, description, persons_number, price, hot, tour_type_id, hotel_type_id
    private String name;
    private String description;
    private int persons_number;
    private int price;
    private int hot;
    private int tour_type_id;
    private int hotel_type_id;

    @Override
    public String toString() {
        return "Tour{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", persons_number=" + persons_number +
                ", price=" + price +
                ", hot=" + hot +
                ", tour_type_id=" + tour_type_id +
                ", hotel_type_id=" + hotel_type_id +
                '}';
    }

    public Tour(String name, String description, int persons_number, int price, int hot, int tour_type_id, int hotel_type_id) {
        this.name = name;
        this.description = description;
        this.persons_number = persons_number;
        this.price = price;
        this.hot = hot;
        this.tour_type_id = tour_type_id;
        this.hotel_type_id = hotel_type_id;
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

    public int getPersons_number() {
        return persons_number;
    }

    public void setPersons_number(int persons_number) {
        this.persons_number = persons_number;
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

    public int getTour_type_id() {
        return tour_type_id;
    }

    public void setTour_type_id(int tour_type_id) {
        this.tour_type_id = tour_type_id;
    }

    public int getHotel_type_id() {
        return hotel_type_id;
    }

    public void setHotel_type_id(int hotel_type_id) {
        this.hotel_type_id = hotel_type_id;
    }
}
