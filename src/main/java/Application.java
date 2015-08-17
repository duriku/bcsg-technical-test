import entity.Bankcard;
import exception.BankcardException;
import service.BankcardService;
import service.CsvToBankcardParserService;
import service.impl.BankcardServiceImpl;
import service.impl.CsvToBankcardParserServiceImpl;

import java.util.List;

/**
 * Created by duri on 8/16/2015.
 */
public class Application {

    public static void main(String[] args) {

        String csvFileName = args[0];
        try {
            CsvToBankcardParserService csvToBankcardParserService = new CsvToBankcardParserServiceImpl();
            List<Bankcard> bankcards = csvToBankcardParserService.parseCsv(csvFileName);
            BankcardService bankcardService = new BankcardServiceImpl();
            bankcards.stream().forEach((b) -> bankcardService.addBankcard(b));
            bankcards = bankcardService.getBankcardsInSortedOrderByExipiryTime();
            bankcards.stream().forEach((b) ->
                            System.out.println(b.getBank() + "  " + b.getPan() + "  " + b.getMaskedPan())
            );
        }
        catch (BankcardException bex){
            System.err.println(bex.getErrorCode() + ": " + bex.getErrorMsg());
        }
    }

}
