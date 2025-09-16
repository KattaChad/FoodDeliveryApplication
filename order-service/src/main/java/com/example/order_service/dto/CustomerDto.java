package com.example.order_service.dto;

public class CustomerDto {
    private int id;
    private String name;
    private String address;
    private Integer balance;

    public CustomerDto() {}

    public CustomerDto(int id, String name, String address, Integer balance) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.balance = balance;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public Integer getBalance() { return balance; }
    public void setBalance(Integer balance) { this.balance = balance; }
}
