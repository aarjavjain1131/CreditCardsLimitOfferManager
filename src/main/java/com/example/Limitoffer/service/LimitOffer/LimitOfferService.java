package com.example.Limitoffer.service.LimitOffer;

import com.example.Limitoffer.dto.LimitOfferDTO;
import com.example.Limitoffer.entity.LimitOfferDetails;

import java.time.LocalDateTime;
import java.util.List;

public interface LimitOfferService {
    LimitOfferDetails createLimitOffer(LimitOfferDTO limitOfferDTO);

    List<LimitOfferDetails> getLimitOffersByAccountId(Long accountId, LocalDateTime activeDate);

    LimitOfferDetails updateLimit(Long offerId, String status);
}
