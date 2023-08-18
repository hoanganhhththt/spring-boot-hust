package com.example.project.entity;

import javax.persistence.*;

@Entity
@Table(name = "user_transaction")
public class UserTransactionDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    // @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH})
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserDTO userDTO;

    // @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH})
    @ManyToOne
    @JoinColumn(name = "transaction_id")
    private TransactionDTO transactionDTO;

    public UserTransactionDTO() {
    }

    public UserTransactionDTO(UserDTO userDTO, TransactionDTO transactionDTO) {
        this.userDTO = userDTO;
        this.transactionDTO = transactionDTO;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserDTO getUser() {
        return userDTO;
    }

    public void setUser(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public TransactionDTO getTransaction() {
        return transactionDTO;
    }

    public void setTransaction(TransactionDTO transactionDTO) {
        this.transactionDTO = transactionDTO;
    }


    @Override
    public String toString() {
        return "UserTransaction{" +
                "id=" + id +
                ", user=" + userDTO +
                ", transaction=" + transactionDTO +
                '}';
    }
}
