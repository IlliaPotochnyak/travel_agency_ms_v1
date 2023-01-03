package entities;

public class Receipt {
    //id, tour_id, user_id, discount, amount, order_status_id, datetime
    private int id;
    private int tourId;
    private int userId;
    private int discount;
    private int amount;
    private int orderStatusId;
    private String datetime;

    public Receipt(int id, int tourId, int userId, int discount,
                   int amount, int orderStatusId, String datetime) {
        this.id = id;
        this.tourId = tourId;
        this.userId = userId;
        this.discount = discount;
        this.amount = amount;
        this.orderStatusId = orderStatusId;
        this.datetime = datetime;
    }

    public Receipt(int tourId, int userId, int discount, int amount, int orderStatusId) {
        this.tourId = tourId;
        this.userId = userId;
        this.discount = discount;
        this.amount = amount;
        this.orderStatusId = orderStatusId;

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

    public int getOrderStatusId() {
        return orderStatusId;
    }

    public void setOrderStatusId(int orderStatusId) {
        this.orderStatusId = orderStatusId;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    @Override
    public String toString() {
        return "Receipt{" +
                "id=" + id +
                ", tourId=" + tourId +
                ", userId=" + userId +
                ", discount=" + discount +
                ", amount=" + amount +
                ", orderStatusId=" + orderStatusId +
                ", datetime='" + datetime + '\'' +
                '}';
    }
}
