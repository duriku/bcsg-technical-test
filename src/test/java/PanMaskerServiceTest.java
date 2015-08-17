import exception.BankcardException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import service.PanMaskerService;
import service.impl.PanMaskerServiceImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by duri on 8/16/2015.
 */

@RunWith(MockitoJUnitRunner.class)
public class PanMaskerServiceTest {

    PanMaskerService panMaskerService;

    @Before
    public void setUp(){
        panMaskerService = new PanMaskerServiceImpl();
    }

    @Test
    public void mask15DigitPan() throws Exception {
        String maskedPan = panMaskerService.maskPan("3786-7334-8965-345");
        assertEquals("xxxx-xxxx-xxxx-345", maskedPan);

        maskedPan = panMaskerService.maskPan("1668-6597-1554-912");
        assertEquals("xxxx-xxxx-xxxx-912", maskedPan);
    }

    @Test
    public void mask16DigitPan() throws Exception {
        String maskedPan = panMaskerService.maskPan("5601-2345-3446-5678");
        assertEquals("5601-xxxx-xxxx-xxxx", maskedPan);
    }

    @Test
    public void maskPanWithWrongDigitsCount(){
        try {
            panMaskerService.maskPan("5601-2345-3446-5");
            assertTrue(false);
        }
        catch (BankcardException bex){
            assertEquals("BEX-101", bex.getErrorCode());
            assertEquals("Pan has to have 15 or 16 digits", bex.getErrorMsg());
        }
    }

}
