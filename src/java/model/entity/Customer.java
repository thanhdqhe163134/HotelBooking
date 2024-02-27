package model.entity;

public class Customer {
    private int customerId;
    private String name;
    private String email;
    private String phone;
    private String personalInfo;

    public Customer() {
    }

    public Customer(int customerId, String name, String email, String phone, String personalInfo) {
        this.customerId = customerId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.personalInfo = personalInfo;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPersonalInfo() {
        return personalInfo;
    }

    public void setPersonalInfo(String personalInfo) {
        this.personalInfo = personalInfo;
    }
}
