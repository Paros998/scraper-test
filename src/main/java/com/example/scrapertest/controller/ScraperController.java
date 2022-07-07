package com.example.scrapertest.controller;

import com.example.scrapertest.service.ScrapperService;
import com.example.scrapertest.dto.CardDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/scrap-card")
@AllArgsConstructor
public class ScraperController {

    private final ScrapperService scrapperService;

    @GetMapping
    public CardDto getCardFromXkom(@RequestParam String store, @RequestParam String cardCode) {
        return scrapperService.getCardDto(store, cardCode);
    }

}
