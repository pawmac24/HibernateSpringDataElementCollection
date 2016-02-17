package com.pm.hibernate.model;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pmackiewicz on 2016-02-03.
 * JPA 2.0
 */
@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue
    @Column(name="employee_id")
    private Long id;

    @Column(name="first_name",length = 50, nullable = false)
    private String firstName;

    @Column(name="last_name", length = 50, nullable = false)
    private String lastName;

    @Column(name="salary", scale=10, precision=2, nullable = false)
    private BigDecimal salary;

    @ElementCollection
    @CollectionTable(
            name="phone",
            joinColumns=@JoinColumn(name="owner_id")
    )
    private List<Phone> phones;

    protected Employee() {
    }

    public Employee(String firstName, String lastName, BigDecimal salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }

    public void addPhone(Phone phone){
        if(phones == null || phones.isEmpty()){
            phones = new ArrayList<>();
        }
        phones.add(phone);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", salary=" + salary +
                '}';
    }
}
