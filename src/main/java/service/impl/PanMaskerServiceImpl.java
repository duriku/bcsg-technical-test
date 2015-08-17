package service.impl;

import exception.BankcardException;
import service.PanMaskerService;

/**
 * Created by duri on 8/16/2015.
 */
public class PanMaskerServiceImpl implements PanMaskerService {

    private final static String MASK ="xxxx";

    @Override
    public String maskPan(String pan) {
        Integer numberOfSeparators = 3;
        if(pan == null || (pan.length() - numberOfSeparators != 15  && pan.length() - numberOfSeparators != 16)){
            System.out.println(pan.length() - numberOfSeparators);
            System.out.println(pan);
            throw new BankcardException(BankcardException.WRONG_PAN_LENGTH, "Pan has to have 15 or 16 digits");
        }
        return pan.length() -numberOfSeparators == 15 ? mask15DigitPan(pan) : mask16DigitPan(pan);
    }

    private String mask15DigitPan(String pan) {
        String[] panChunks = pan.split("-");
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < 3; i++) {
            sb = sb.append(MASK).append("-");
        }
        sb = sb.append(panChunks[3]);
        return sb.toString();
    }

    private String mask16DigitPan(String pan) {
        String[] panChunks = pan.split("-");
        StringBuilder sb = new StringBuilder();
        sb = sb.append(panChunks[0]).append("-");
        for(int i = 0; i < 2; i++) {
            sb = sb.append(MASK).append("-");
        }
        sb = sb.append(MASK);
        return sb.toString();
    }
}
