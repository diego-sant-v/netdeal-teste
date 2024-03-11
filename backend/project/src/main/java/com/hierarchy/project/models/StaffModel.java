package com.hierarchy.project.models;

import jakarta.persistence.*;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "staff_hierarchy")
public class StaffModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "password")
    private String password;
    @Column(name = "password_rate")
    private String passwordRate;
    @Column(name = "score_password")
    private BigDecimal scorePassword;

    @Column(name = "hierarchy")
    private String hierarchy;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "order_hierarchy")
    private int orderHierarchy;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordRate() {
        return passwordRate;
    }

    public void setPasswordRate(String passwordRate) {
        this.passwordRate = passwordRate;
    }

    public BigDecimal getScorePassword() {
        return scorePassword;
    }

    public void setScorePassword(BigDecimal scorePassword) {
        this.scorePassword = scorePassword;
    }

    public String getHierarchy() {
        return hierarchy;
    }

    public void setHierarchy(String hierarchy) {
        this.hierarchy = hierarchy;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int getOrderHierarchy() {
        return orderHierarchy;
    }

    public void setOrderHierarchy(int orderHierarchy) {
        this.orderHierarchy = orderHierarchy;
    }
}
