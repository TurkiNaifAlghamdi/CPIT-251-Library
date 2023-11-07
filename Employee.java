public class Employee {
    
    // Employee information
    private int id;
    private String name;
    private char gender;
    private String job_title;
    private Employee next;
    
    // Constructer with "next" field
    public Employee(int id, String name, char gender, String job_title, Employee next) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.job_title = job_title;
        this.next = next;
    }

    // Constructer without "next" field
    public Employee(int id, String name, char gender, String job_title) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.job_title = job_title;
    }
    
    // All methods below are setters and getters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public String getJob_title() {
        return job_title;
    }

    public void setJob_title(String job_title) {
        this.job_title = job_title;
    }

    public Employee getNext() {
        return next;
    }

    public void setNext(Employee next) {
        this.next = next;
    }
    
    
    

}
