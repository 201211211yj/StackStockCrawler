package KospiCrawler;

import KospiCrawler.service.NasdaqCrawlingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import KospiCrawler.service.KospiCrawlingService;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableScheduling
public class StackstockApplication {

    public static void main(String[] args) {
        SpringApplication.run(StackstockApplication.class, args);
    }

}
