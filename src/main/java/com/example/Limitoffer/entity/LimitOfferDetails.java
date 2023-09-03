package com.example.Limitoffer.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class LimitOfferDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long offerId;
    private Long accountId;

    private String limitType;

    private Long newLimit;
    @Column(columnDefinition = "timestamp without time zone")
    private LocalDateTime offerActivationTime;

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    @Column(columnDefinition = "timestamp without time zone")
    private LocalDateTime offerExpiryTime;
    private String status;

    public Long getOfferId() {
        return offerId;
    }

    public void setOfferId(Long offerId) {
        this.offerId = offerId;
    }

    public String getLimitType() {
        return limitType;
    }

    public void setLimitType(String limitType) {
        this.limitType = limitType;
    }

    public Long getNewLimit() {
        return newLimit;
    }

    public void setNewLimit(Long newLimit) {
        this.newLimit = newLimit;
    }

    public LocalDateTime getOfferActivationTime() {
        return offerActivationTime;
    }

    public void setOfferActivationTime(LocalDateTime offerActivationTime) {
        this.offerActivationTime = offerActivationTime;
    }

    public LocalDateTime getOfferExpiryTime() {
        return offerExpiryTime;
    }

    public void setOfferExpiryTime(LocalDateTime offerExpiryTime) {
        this.offerExpiryTime = offerExpiryTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
