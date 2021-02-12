package KospiCrawler.document;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString
@Document
public class Ticker {
    @Id
    private String id;
    private String code;
    private String url;

}
