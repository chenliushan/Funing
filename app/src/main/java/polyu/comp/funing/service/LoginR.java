package polyu.comp.funing.service;

/**
 * Created by liushanchen on 16/3/17.
 */
public class LoginR {
    /*
     "error": 0,
  "name": "chris",
  "email": "0607chris@gmail.com",
  "apiKey": "476ef0c04b7d550dc627a698dd9aff5c",
  "createdAt": "2016-03-11 04:09:19"
     */
    private int error;
    private String message;
    private String name;
    private String email;
    private String apiKey;
    private String createdAt;
    private String address;
    private String phone;
    private int uid;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "LoginR{" +
                "error=" + error +
                ", message='" + message + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", apiKey='" + apiKey + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
