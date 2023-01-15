package constant;

public class MySQLConstant {

    /**
     * Receipt queries
     */

    public static final String ADD_RECEIPT = "INSERT INTO receipt (tour_id, user_id, discount, amount, order_status_id, datetime)" +
            "VALUES (?, ?, ?, ?," +
            " (SELECT receipt_status.id FROM receipt_status WHERE receipt_status.receipt_status=?), now())";

    public static final String GET_ALL_USER_RECEIPT = "SELECT SQL_CALC_FOUND_ROWS receipt.id, receipt.tour_id, tour.name, receipt.user_id, user.first_name, user.last_name, receipt.discount, receipt.amount, receipt_status.receipt_status, receipt.datetime \n" +
            "FROM receipt INNER JOIN tour ON receipt.tour_id=tour.id\n" +
            "    INNER JOIN receipt_status ON receipt.order_status_id=receipt_status.id\n" +
            "    INNER JOIN user ON receipt.user_id=user.id " +
            "WHERE user.id=? ORDER BY order_status_id, datetime DESC limit ";

    public static final String GET_ALL_RECEIPTS = "SELECT SQL_CALC_FOUND_ROWS receipt.id, receipt.tour_id, tour.name, receipt.user_id, user.first_name, user.last_name, receipt.discount, receipt.amount, receipt_status.receipt_status, receipt.datetime \n" +
            "FROM receipt INNER JOIN tour ON receipt.tour_id=tour.id\n" +
            "    INNER JOIN receipt_status ON receipt.order_status_id=receipt_status.id\n" +
            "    INNER JOIN user ON receipt.user_id=user.id  ORDER BY order_status_id, datetime DESC limit ";

    public static final String UPDATE_RECEIPT_STATUS = "UPDATE receipt SET order_status_id=" +
            "(SELECT receipt_status.id from receipt_status WHERE receipt_status=?) " +
            "WHERE receipt.id=?;";
    public static final String UPDATE_RECEIPT_DISCOUNT = "UPDATE receipt SET discount=?, amount=? WHERE receipt.id=?;";

    /**
     * Tour queries
     */
    public static final String ADD_TOUR = "INSERT INTO tour (name, description, persons_number, price, max_discount, hot, tour_type_id, hotel_type_id)" +
            "VALUES (?, ?, ?, ?, ?, ?, (SELECT tour_type.id FROM tour_type WHERE tour_type.tour_type=?), " +
            "(SELECT hotel_type.id FROM hotel_type WHERE hotel_type.star_rate=?))";

    public static final String GET_ALL_TOURS = "SELECT SQL_CALC_FOUND_ROWS tour.id, tour.name, tour.description, tour.persons_number, tour.price, tour.max_discount, tour.hot, \n" +
            "tour_type.tour_type, hotel_type.star_rate \n" +
            "FROM ((tour\n" +
            "INNER JOIN tour_type ON tour.tour_type_id=tour_type.id)\n" +
            "INNER JOIN hotel_type ON tour.hotel_type_id=hotel_type.id) ORDER BY hot DESC limit ";
    public static final String GET_SORTED_TOURS = "SELECT SQL_CALC_FOUND_ROWS tour.id, tour.name, tour.description, tour.persons_number, tour.price, tour.max_discount, tour.hot, \n" +
            "tour_type.tour_type, hotel_type.star_rate \n" +
            "FROM ((tour\n" +
            "INNER JOIN tour_type ON tour.tour_type_id=tour_type.id)\n" +
            "INNER JOIN hotel_type ON tour.hotel_type_id=hotel_type.id) WHERE tour_type=? AND price<=? " +
            "AND persons_number=? " +
            "AND star_rate>=? " +
            "ORDER BY hot DESC limit ";
    public static final String DELETE_TOUR_BY_ID = "DELETE FROM tour WHERE id=?;";
    public static final String GET_TOUR_BY_ID = "SELECT tour.id, tour.name, tour.description, tour.persons_number, tour.price, tour.max_discount, tour.hot, \n" +
            "tour_type.tour_type, hotel_type.star_rate \n" +
            "FROM ((tour\n" +
            "INNER JOIN tour_type ON tour.tour_type_id=tour_type.id)\n" +
            "INNER JOIN hotel_type ON tour.hotel_type_id=hotel_type.id) WHERE tour.id=?;";
    public static final String UPDATE_TOUR = "UPDATE tour SET " +
            "name=?, description=?, persons_number=?, price=?, tour.max_discount=?, hot=?, " +
            "tour_type_id=(SELECT tour_type.id FROM tour_type WHERE tour_type=?)," +
            "hotel_type_id=(SELECT hotel_type.id FROM hotel_type WHERE star_rate=?) " +
            "WHERE tour.id=?;";

    /**
     * User queries
     */

    public static final String GET_ALL_USERS = "SELECT SQL_CALC_FOUND_ROWS user.id, user.first_name, user.last_name, user.email, " +
            "user.password, user.phone, user.active, role.role " +
            "FROM user INNER JOIN role ON user.role_id=role.id " +
            "ORDER BY user.id limit ";
    public static final String GET_USER_BY_EMAIL = "SELECT user.id, user.first_name, user.last_name, user.email, user.password, user.phone, role.role \n" +
            "FROM user INNER JOIN role ON user.role_id=role.id WHERE email=?";
    public static final String ADD_USER = "INSERT INTO user (first_name, last_name, email, password, phone, active, role_id)" +
            "VALUES (?, ?, ?, ?, ?, ?, (SELECT role.id FROM role WHERE role.role=?))";
    public static final String SET_USER_ACTIVE_FIELD = "UPDATE user SET active=? WHERE id=?;";

}
