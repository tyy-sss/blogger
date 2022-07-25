package pojo;

import java.sql.Date;

public class User {
    private int  id;
    private String email;
    private int phone;
    private String password;
    private String userName;
    private String signature;
    private String picture;
    private String gender;
    private Date birthday;
    private Date time;

    public User() {
    }

    public User(int id, String email, int phone, String password, String userName, String signature, String picture, String gender, Date birthday, Date time) {
        this.id = id;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.userName = userName;
        this.signature = signature;
        this.picture = picture;
        this.gender = gender;
        this.birthday = birthday;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", phone=" + phone +
                ", password='" + password + '\'' +
                ", userName='" + userName + '\'' +
                ", signature='" + signature + '\'' +
                ", picture='" + picture + '\'' +
                ", gender='" + gender + '\'' +
                ", birthday=" + birthday +
                ", time=" + time +
                '}';
    }
}
