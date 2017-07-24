package cn.com.baomidou.testHibernate.entity;


import java.util.*;

/**
 * Created by zzx on 2017/7/23.
 */
public class Customer {
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String phone;
    private boolean married;
    private String address;
    private char sex;
    private String description;
    private byte[] image;
    private Date brithday;
    private Date registeredTime;
    private Set<Order> orders =new HashSet<Order>();
    private Double avgPrice;
    private Double totalPrice;

    public Customer() {
    }
    public Double getTotalPrice() {

        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    /**
     * 订单的平均价格
     */
    private void calculatePrice() {
        double avgPrice = 0.0;
        double totalPrice = 0.0;
        int count = 0;
        if (getOrders() != null) {
            Iterator iterator = getOrders().iterator();
            while (iterator.hasNext()) {
                double orderPrice = ((Order) iterator.next()).getPrice();
                totalPrice += orderPrice;
                count++;
            }
            avgPrice = totalPrice / count;
            setAvgPrice(new Double(avgPrice));
        }
    }

    public Double getAvgPrice() {
        return avgPrice;
    }

    private void setAvgPrice(Double avgPrice) {
        this.avgPrice = avgPrice;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return firstname + " " + lastname;
    }

    public void setName(String name) {
        StringTokenizer stringTokenizer = new StringTokenizer(name);
        firstname = stringTokenizer.nextToken();
        lastname = stringTokenizer.nextToken();
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

    public boolean isMarried() {
        return married;
    }

    public void setMarried(boolean married) {
        this.married = married;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        if (sex != 'F' && sex != 'M') {
            throw new IllegalArgumentException("Invalid Sex");
        }
        this.sex = sex;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Date getBrithday() {
        return brithday;
    }

    public void setBrithday(Date brithday) {
        this.brithday = brithday;
    }

    public Date getRegisteredTime() {
        return registeredTime;
    }

    public void setRegisteredTime(Date registeredTime) {
        this.registeredTime = registeredTime;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + firstname + lastname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phone=" + phone +
                ", married=" + married +
                ", address='" + address + '\'' +
                ", sex=" + sex +
                ", description='" + description + '\'' +
                ", image=" + Arrays.toString(image) +
                ", brithday=" + brithday +
                ", registeredTime=" + registeredTime +
                '}';
    }
}
