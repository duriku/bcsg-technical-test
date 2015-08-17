package service.impl;

import entity.Bankcard;
import exception.BankcardException;
import service.CsvToBankcardParserService;
import service.PanMaskerService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by duri on 8/16/2015.
 */
public class CsvToBankcardParserServiceImpl implements CsvToBankcardParserService {

    private static final String CVS_SPLIT_BY = ",";

    private PanMaskerService panMaskerService;

    public CsvToBankcardParserServiceImpl(){
        panMaskerService = new PanMaskerServiceImpl();
    }

    @Override
    public List<Bankcard> parseCsv(String csvFileUri) {
        String line;
        List<Bankcard> bankcards = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(csvFileUri))) {
            while((line = br.readLine()) != null) {
                List<String> bankcardValues = Arrays.stream(line.split(CVS_SPLIT_BY)).collect(Collectors.toList());
                String bank = bankcardValues.get(0);
                String pan = bankcardValues.get(1);
                String expiryDate = bankcardValues.get(2);
                bankcards.add(new Bankcard(bank, pan, expiryDate,
                        panMaskerService.maskPan(pan)));
            }
            return bankcards;
        } catch (IOException e) {
            System.out.println(e);
            throw new BankcardException(BankcardException.GENERAL_ERROR,e.getMessage());
        }
    }

}
