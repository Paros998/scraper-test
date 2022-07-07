package com.example.scrapertest.service;

import com.example.scrapertest.dto.CardDto;

public interface ScrapperService {
    CardDto getCardDto(String store, String cardCode);
}
