package KospiCrawler.scheduler;

import KospiCrawler.service.NasdaqCrawlingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class NadaqCrawlingJob {

    @Autowired
    NasdaqCrawlingService nasdaqCrawlingService;

    @Scheduled(cron = "10 * * * * ?")
    void nasdaqCrawlingJob(){
        nasdaqCrawlingService.fetchNasdaqPages();
    }


}
