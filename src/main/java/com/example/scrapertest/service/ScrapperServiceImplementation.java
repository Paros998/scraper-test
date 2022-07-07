package com.example.scrapertest.service;

import com.example.scrapertest.dto.CardDto;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import org.jsoup.nodes.Element;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.Objects;

@Service
public class ScrapperServiceImplementation implements ScrapperService {

    public CardDto getCardDto(String store, String cardCode) {

        CardDto cardDto = null;
        float cardPrice;
        String cardOfferUrl;

        try {

            switch (store) {

                case "xkom" -> {

                    Document document = Jsoup.connect("https://www.x-kom.pl/szukaj?q=" + cardCode).get();

                    cardOfferUrl = "https://www.x-kom.pl";

                    Element element = document.getElementById("listing-container");

                    if (element != null) {
                        Element cardElement = element.getAllElements().get(2);

                        if (cardElement != null) {
                            cardOfferUrl += cardElement.getElementsByTag("a").attr("href");

                            String cardPriceInString = Objects.requireNonNull(cardElement.getElementsByClass("sc-6n68ef-0 sc-6n68ef-3 iepkXv").first())
                                    .childNode(0)
                                    .outerHtml();

                            cardPrice = Float.parseFloat(cardPriceInString.split("[A-Za-z]")[0].replace(',', '.').replace(" ", ""));

                            cardDto = new CardDto(cardPrice, cardOfferUrl);
                        }
                    }

                }

                case "euro" -> {

                    Document document = Jsoup.connect("https://www.euro.com.pl/search.bhtml?keyword=" + cardCode).get();

                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        if (cardDto == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Card with code %s not found on site %s", cardCode, store));

        return cardDto;

    }
}
