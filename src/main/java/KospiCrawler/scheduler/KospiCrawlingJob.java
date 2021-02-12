package KospiCrawler.scheduler;

import KospiCrawler.service.KospiCrawlingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class KospiCrawlingJob {
    /*
    @Autowired
    KospiCrawlingService kospiCrawlingService;

    @Scheduled(cron = "")
    void kospiCrawlingJob(){
        List<String> codes = new ArrayList<>();
        codes.add("plug-power");
        kospiCrawlingService.fetchKospiPages(codes);
    }
    */

}
