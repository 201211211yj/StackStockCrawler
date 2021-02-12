package KospiCrawler.service;

import KospiCrawler.document.Ticker;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

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
            System.out.println(i);
            long beforeTime = System.currentTimeMillis();

            Document doc = Jsoup.parse(i);
            /*
            Elements elements = doc.getElementById("middle").
                    getElementsByClass("blind").
                    first().
                    getElementsByTag("dd");

            Kospi kospi = new Kospi();

            for(Element element : elements){
                String elementText = element.text();
                elementText = elementText.replace(",", "");
                StringTokenizer stringTokenizer = new StringTokenizer(elementText);
                String sName    = stringTokenizer.nextToken(" ");
                String value    = stringTokenizer.nextToken(" ");
                if(sName.equals("종목코드")){
                    kospi.setCode(value);
                }else if(sName.equals("현재가")){
                    kospi.setPrice(Long.parseLong(value));
                }else if(sName.equals("거래량")){
                    kospi.setTradeVolume(Long.parseLong(value));
                }
            }

            webClient.post().
                    uri("http://localhost:8080/kospi").
                    contentType(MediaType.APPLICATION_JSON).
                    bodyValue(kospi).
                    retrieve().
                    bodyToFlux(Kospi.class).
                    subscribe(requestResult -> log.info("Entity has been POST Request : {}", requestResult));


            long endTime = System.currentTimeMillis();
            log.info("End time : {}",endTime);

             */
        });
    }

}
