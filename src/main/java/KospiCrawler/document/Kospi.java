package KospiCrawler.document;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@Setter
@ToString
@Document
public class Kospi {
    @Id
    private String id;
    private String code;
    private Long price;
    private Long tradeVolume;
    private Date getDate = new Date();

}
