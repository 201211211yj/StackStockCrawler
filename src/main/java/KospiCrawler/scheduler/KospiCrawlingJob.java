package KospiCrawler.scheduler;

import KospiCrawler.service.KospiCrawlingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class KospiCrawlingJob {

    @Autowired
    KospiCrawlingService kospiCrawlingService;

    @Scheduled(cron = "0 10 * * * ?")
    void kospiCrawlingJob(){
        List<String> codes = new ArrayList<>();
        codes.add("000660");
        kospiCrawlingService.fetchKospiPages(codes);
    }


}
