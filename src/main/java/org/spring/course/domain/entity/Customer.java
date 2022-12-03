package org.spring.course.domain.entity;

import javax.persistence.*;

@Entity
@Table(name = "TB_CUSTOMERS")
public class Customer {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name="name", length = 50, nullable = false)
    private String name;

    public Customer() {
    }

    public Customer(String name) {
        this.name = name;
    }


    public Customer(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
