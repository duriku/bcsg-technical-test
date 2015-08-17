import entity.Bankcard;
import org.junit.Before;
import org.junit.Test;
import service.CsvToBankcardParserService;
import service.impl.CsvToBankcardParserServiceImpl;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


/**
 * Created by duri on 8/16/2015.
 */

public class CsvToBankcardParserServiceTest {

    private static final String CSV_FILE_URI = "/mid-test.csv";

    CsvToBankcardParserService csvToBankcardParserService;

    @Before
    public void setUp(){
        csvToBankcardParserService = new CsvToBankcardParserServiceImpl();
    }

    @Test
    public void parseCsv() throws URISyntaxException {
        URL resourceUrl = getClass().getResource(CSV_FILE_URI);
        Path resourcePath = Paths.get(resourceUrl.toURI());

        List<Bankcard> bankcards = csvToBankcardParserService.parseCsv(resourcePath.getFileName().toString());

        bankcards.contains(new Bankcard("American Express", "3786-7334-8965-345", "Dec-2018", "xxxx-xxxx-xxxx-345"));
        bankcards.contains(new Bankcard("HSBC Canada", "5601-2345-3446-5678","Nov-2017", "5601-xxxx-xxxx-xxxx"));
        bankcards.contains(new Bankcard("Royal Bank of Canada", "4519-4532-4524-2456", "Oct-2017", "4519-xxxx-xxxx-xxxx"));
    }


}
