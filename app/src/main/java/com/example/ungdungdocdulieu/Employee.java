package com.example.ungdungdocdulieu;

public class Employee {
    private String id;
    private String title;
    private String name;
    private String phone;

    public Employee() {}

    public Employee(String id, String title, String name, String phone) {
        this.id = id;
        this.title = title;
        this.name = name;
        this.phone = phone;
    }

    // Getter và Setter
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
}



