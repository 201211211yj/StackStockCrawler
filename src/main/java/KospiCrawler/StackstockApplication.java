package KospiCrawler;

import KospiCrawler.service.NasdaqCrawlingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import KospiCrawler.service.KospiCrawlingService;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class StackstockApplication implements CommandLineRunner {
    @Autowired
    KospiCrawlingService kospiCrawlingService;
    @Autowired
    NasdaqCrawlingService nasdaqCrawlingService;

    public static void main(String[] args) {
        SpringApplication.run(StackstockApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        nasdaqCrawlingService.fetchNasdaqPages();

    }

}
