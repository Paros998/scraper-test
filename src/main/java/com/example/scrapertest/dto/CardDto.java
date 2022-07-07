package com.example.scrapertest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CardDto {

    private final Float cardPrice;

    private final String cardBuyUrl;

}
