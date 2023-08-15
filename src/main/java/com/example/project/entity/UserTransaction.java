package com.example.project.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "user_transaction")
public class UserTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    // @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH})
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH})
    @ManyToOne
    @JoinColumn(name = "transaction_id")
    private Transaction transaction;

    public UserTransaction() {
    }

    public UserTransaction(User user, Transaction transaction) {
        this.user = user;
        this.transaction = transaction;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }


    @Override
    public String toString() {
        return "UserTransaction{" +
                "id=" + id +
                ", user=" + user +
                ", transaction=" + transaction +
                '}';
    }
}
