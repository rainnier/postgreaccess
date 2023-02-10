package com.postgresaccess.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "transaction")
public class Txn {
    private long id;
    private Date txnDate;
    private String txnType;
    private String comments;



    private double txnAmount;

    public Txn() {

    }

    public Txn(Date txnDate, String txnType, String comments, double txnAmount) {
        this.txnDate = txnDate;
        this.txnType = txnType;
        this.comments = comments;
        this.txnAmount = txnAmount;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "date_time", nullable = false)
    public Date getTxnDate() {
        return txnDate;
    }
    public void setTxnDate(Date txnDate) {
        this.txnDate = txnDate;
    }

    @Column(name = "txn_type", nullable = false)
    public String getTxnType() {
        return txnType;
    }
    public void setTxnType(String txnType) {
        this.txnType = txnType;
    }

    @Column(name = "comments", nullable = false)
    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
    @Column(name = "txn_amount")
    public double getTxnAmount() {
        return txnAmount;
    }

    public void setTxnAmount(double txnAmount) {
        this.txnAmount = txnAmount;
    }

    @Override
    public String toString() {
        return "Txn [id=" + id + ", tnxDate=" + txnDate + ", txnType=" + txnType + ", comments=" + comments
                + "]";
    }
}
