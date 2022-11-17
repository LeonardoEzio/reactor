package leonardo.ezio.personal.entity;

/**
 * @Description :
 * @Author : LeonardoEzio
 * @Date: 2022-01-05 17:04
 */
public class User {

    private String id;

    private String userName;

    public User() {
    }

    public User(String userName) {
        this.userName = userName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                '}';
    }
}
