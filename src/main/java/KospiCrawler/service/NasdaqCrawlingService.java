package KospiCrawler.service;

import KospiCrawler.document.Nasdaq;
import KospiCrawler.document.Ticker;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Service
public class NasdaqCrawlingService {
    @Autowired
    WebClient webClient;

    Mono <String> getNasdaqPageByCode(String url){
        log.info("Start time : {}",System.currentTimeMillis());
        return webClient.get().
                uri("https://m.kr.investing.com/equities/{url}", url).
                retrieve().
                bodyToMono(String.class);
    }

    public void fetchNasdaqPages() {
        Flux <Ticker> tickers = webClient.get().
                uri("http://54.180.93.176:8080/nasdaqTickers").
                retrieve().
                bodyToFlux(Ticker.class);

        Flux <String> result = tickers.flatMap(
                i -> {
                    return this.getNasdaqPageByCode(i.getUrl());
                });

        result.subscribe(i->{
            //System.out.println(i);
            long beforeTime = System.currentTimeMillis();

            Document doc = Jsoup.parse(i);
            Nasdaq nasdaq = new Nasdaq();

            String title = doc.getElementById("titleInstrument").getElementsByClass("h1").text();
            String code = "";
            Pattern nonValidPattern = Pattern.compile("[A-Z]");
            Matcher matcher = nonValidPattern.matcher(title);
            while(matcher.find()){
                code += matcher.group();
            }
            nasdaq.setCode(code);
            System.out.println("코드 : " + code);

            String curCost = doc.getElementsByClass("quotesBoxTop").
                    first().
                    getElementsByTag("span").first().text();
            System.out.println("현재가 : " + curCost);
            nasdaq.setPrice(Double.parseDouble(curCost));

            Elements pairDatas = doc.getElementsByClass("pairData").
                    first().
                    getElementsByClass("pairDataContent");

            for(Element pairData : pairDatas){
                Elements datas = pairData.getElementsByTag("span");

                if(datas.get(0).text().equals("거래량")){
                    Long tradeVolume = Long.parseLong(datas.get(1).text().replace(",", ""));
                    nasdaq.setTradeVolume(tradeVolume);
                    System.out.println("거래량 : " + tradeVolume);
                }
            }


            webClient.post().
                    uri("http://54.180.93.176:8080/nasdaq").
                    contentType(MediaType.APPLICATION_JSON).
                    bodyValue(nasdaq).
                    retrieve().
                    bodyToFlux(Nasdaq.class).
                    subscribe(requestResult -> log.info("Entity has been POST Request : {}", requestResult));


            long endTime = System.currentTimeMillis();
            log.info("End time : {}",endTime);

        });
    }

}
