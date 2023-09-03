package com.example.Limitoffer.Controllers;

import com.example.Limitoffer.dto.LimitOfferDTO;
import com.example.Limitoffer.Services.LimitOfferService;
import com.example.Limitoffer.entity.Account;
import com.example.Limitoffer.entity.LimitOfferDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/limit-offers")
public class LimitOfferController {

    private final LimitOfferService limitOfferService;

    @Autowired
    public LimitOfferController(LimitOfferService limitOfferService) {
        this.limitOfferService = limitOfferService;
    }


    @PostMapping("/create")
    public ResponseEntity<LimitOfferDetails> createLimitOffer(@RequestBody LimitOfferDTO limitOfferDTO) {
            LimitOfferDetails createdOffer = limitOfferService.createLimitOffer(limitOfferDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdOffer);

    }

    @GetMapping("/active-offers")
    public ResponseEntity<List<LimitOfferDetails>> listLimitOffers(@RequestParam(name = "accountId") Long accountId,
                                                                   @RequestParam(name = "activeDate", required = false) LocalDateTime activeDate){
        if(activeDate==null)
            activeDate = LocalDateTime.now();
        List<LimitOfferDetails> limitOffers = limitOfferService.getLimitOffersByAccountId(accountId,activeDate);
        if (!limitOffers.isEmpty()) {
            return ResponseEntity.ok(limitOffers);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/update/{offerId}")
    public ResponseEntity<LimitOfferDetails> updateLimit(@PathVariable Long offerId, @RequestParam(name = "status") String status){

        LimitOfferDetails limitOfferDetails = limitOfferService.updateLimit(offerId,status);
        return ResponseEntity.ok(limitOfferDetails);
    }
}
