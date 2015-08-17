import entity.Bankcard;
import exception.BankcardException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import service.BankcardService;
import service.impl.BankcardServiceImpl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by duri on 8/16/2015.
 */

@RunWith(MockitoJUnitRunner.class)
public class BankcardServiceTest {

    BankcardService bankcardService;

    @Before
    public void setUp(){
        bankcardService = new BankcardServiceImpl();
    }

    @Test
    public void addBankcard() throws Exception {
        Bankcard bankcard= new Bankcard("HSBC Canada", "5601-2345-3446-5678","Nov-2017", "5601-xxxx-xxxx-xxxx");
        bankcard = bankcardService.addBankcard(bankcard);

        assertEquals("HSBC Canada", bankcard.getBank());
        assertEquals("5601-2345-3446-5678", bankcard.getPan());
        assertEquals("5601-xxxx-xxxx-xxxx", bankcard.getMaskedPan());

        Calendar cal = Calendar.getInstance();
        cal.setTime(bankcard.getExpiryDate());
        assertEquals(2017, cal.get(Calendar.YEAR));
        assertEquals(10, cal.get(Calendar.MONTH));

    }

    @Test
    public void addBankcardWithWrongExpiryFormat() throws Exception {
        try {
            List<Bankcard> bankcards = new ArrayList<>();
            Bankcard bankcard= new Bankcard("HSBC Canada", "5601-2345-3446-5678","18-Nov-2017", "5601-xxxx-xxxx-xxxx");
            bankcardService.addBankcard(bankcard);
            assertTrue(false);
        }
        catch (BankcardException bex){
            assertEquals("BEX-102", bex.getErrorCode());
            assertEquals("Wrong expiry date format!", bex.getErrorMsg());
        }
    }

    @Test
    public void getBankcardsInSortedOrderByExipiryTime() throws Exception {
        bankcardService.addBankcard(new Bankcard("American Express", "3786-7334-8965-345", "Dec-2018", "xxxx-xxxx-xxxx-345"));
        bankcardService.addBankcard(new Bankcard("HSBC Canada", "5601-2345-3446-5678","Nov-2017", "5601-xxxx-xxxx-xxxx"));
        bankcardService.addBankcard(new Bankcard("Royal Bank of Canada","4519-4532-4524-2456","Oct-2017", "4519-xxxx-xxxx-xxxx"));

        List<Bankcard> bankcards = bankcardService.getBankcardsInSortedOrderByExipiryTime();

        assertEquals("American Express", bankcards.get(0).getBank());
        assertEquals("HSBC Canada", bankcards.get(1).getBank());
        assertEquals("Royal Bank of Canada", bankcards.get(2).getBank());
    }


}
