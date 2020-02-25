package kamiltm.project_sm.user;

/**
 * Created by Kamil Lenartowicz on 2017-12-27.
 */

public class User {
    private int id;
    private String username;
    private String email;
    private String sex;
    private String height;
    private String weight;
    private String target_weight;
    private String weight_lose;
    private int kcal;

    public int getKcal() {
        return kcal;
    }

    public void setKcal(int kcal) {
        this.kcal = kcal;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", sex='" + sex + '\'' +
                ", height='" + height + '\'' +
                ", weight='" + weight + '\'' +
                ", target_weight='" + target_weight + '\'' +
                ", weight_lose='" + weight_lose + '\'' +
                '}';
    }

    public User(String username, String email, String sex) {
        this.username = username;
        this.email = email;
        this.sex = sex;
    }

    public User(int id, String username, String email, String sex, String height, String weight, String target_weight, String weight_lose, int kcal) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.sex = sex;
        this.height = height;
        this.weight = weight;
        this.target_weight = target_weight;
        this.weight_lose = weight_lose;
        this.kcal = kcal;
    }

    public User(int id, String username, String email, String sex, String height, String weight, String target_weight, String weight_lose) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.sex = sex;
        this.height = height;
        this.weight = weight;
        this.target_weight = target_weight;
        this.weight_lose = weight_lose;
    }

    public User(String username, String email, String sex, String height, String weight, String target_weight, String weight_lose) {
        this.username = username;
        this.email = email;
        this.sex = sex;
        this.height = height;
        this.weight = weight;
        this.target_weight = target_weight;
        this.weight_lose = weight_lose;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getTarget_weight() {
        return target_weight;
    }

    public void setTarget_weight(String target_weight) {
        this.target_weight = target_weight;
    }

    public String getWeight_lose() {
        return weight_lose;
    }

    public void setWeight_lose(String weight_lose) {
        this.weight_lose = weight_lose;
    }
}
