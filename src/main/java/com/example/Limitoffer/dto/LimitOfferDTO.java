package com.example.Limitoffer.dto;

import com.example.Limitoffer.util.ValidateLimitOfferDTO;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

@ValidateLimitOfferDTO
public class LimitOfferDTO {

    @NotNull(message = "AccountId is required")
    private Long accountId;

    @Pattern(regexp = "^(ACCOUNT_LIMIT|PER_TRANSACTION_LIMIT)$")
    private String limitType;
    @NotNull
    private Long newLimit;

    private LocalDateTime offerActivationTime;

    @NotNull
    private LocalDateTime offerExpiryTime;

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
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
}
