package entities;

public class User {
    //id, first_name, lastLame, email, password, phone, active, role_id
    private int id;
    private String firstName;
    private String lastLame;
    private String email;
    private String password;
    private String phone;
    private int active;
    private String role;

    public User(int id, String firstName, String lastLame, String email, String password, String phone, String role) {
        this.id = id;
        this.firstName = firstName;
        this.lastLame = lastLame;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.active = 1;
        this.role = role;
    }

    public User(String firstName, String lastLame, String email, String phone, int active, String role) {
        this.firstName = firstName;
        this.lastLame = lastLame;
        this.email = email;
        this.phone = phone;
        this.active = active;
        this.role = role;
    }

    public User(String firstName, String lastLame, String email, String password, String phone, int active, String role) {
        this.firstName = firstName;
        this.lastLame = lastLame;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.active = active;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastLame() {
        return lastLame;
    }

    public void setLastLame(String lastLame) {
        this.lastLame = lastLame;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "first_name='" + firstName + '\'' +
                ", lastLame='" + lastLame + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", active=" + active +
                ", role=" + role +
                '}';
    }
}
