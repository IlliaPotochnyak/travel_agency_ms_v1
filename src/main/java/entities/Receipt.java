package entities;

public class Receipt {
    //id, tour_id, user_id, discount, amount, order_status_id, datetime
    private int id;
    private int tourId;
    private int userId;
    private int discount;
    private int amount;
    private String orderStatus;
    private String datetime;
    private String tourName;
    private String userFirstName;
    private String userLastName;

    public Receipt(int id, int tourId, int userId, int discount, int amount, String orderStatus,
                   String datetime, String tourName, String userFirstName, String userLastName) {
        this.id = id;
        this.tourId = tourId;
        this.userId = userId;
        this.discount = discount;
        this.amount = amount;
        this.orderStatus = orderStatus;
        this.datetime = datetime;
        this.tourName = tourName;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
    }

    public Receipt(int tourId, int userId, int discount, int amount, String orderStatus) {
        this.tourId = tourId;
        this.userId = userId;
        this.discount = discount;
        this.amount = amount;
        this.orderStatus = orderStatus;

    }

    public int getId() {
        return id;
    }

    public int getTourId() {
        return tourId;
    }

    public void setTourId(int tourId) {
        this.tourId = tourId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getTourName() {
        return tourName;
    }

    public void setTourName(String tourName) {
        this.tourName = tourName;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    @Override
    public String toString() {
        return "Receipt{" +
                "id=" + id +
                ", tourId=" + tourId +
                ", userId=" + userId +
                ", discount=" + discount +
                ", amount=" + amount +
                ", orderStatus='" + orderStatus + '\'' +
                ", datetime='" + datetime + '\'' +
                ", tourName='" + tourName + '\'' +
                ", userFirstName='" + userFirstName + '\'' +
                ", userLastName='" + userLastName + '\'' +
                '}';
    }
}
