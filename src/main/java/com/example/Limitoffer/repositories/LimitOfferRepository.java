package com.example.Limitoffer.repositories;

import com.example.Limitoffer.entity.LimitOfferDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface LimitOfferRepository extends JpaRepository<LimitOfferDetails, Long> {


    Optional<LimitOfferDetails> findByOfferIdAndStatusAndOfferExpiryTimeGreaterThanAndOfferActivationTimeLessThan(Long offerId, String status, LocalDateTime offerExpiryTime, LocalDateTime offerActivationTime );

    List<LimitOfferDetails> findByAccountIdAndStatusAndOfferExpiryTimeGreaterThanAndOfferActivationTimeLessThan(Long accountId, String pending, LocalDateTime offerExpiryTime, LocalDateTime offerActivationTime);
}
