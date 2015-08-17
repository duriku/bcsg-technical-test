package service;

import entity.Bankcard;

import java.util.List;

/**
 * Created by duri on 8/16/2015.
 */
public interface CsvToBankcardParserService {
    List<Bankcard> parseCsv(String csvFileUri);
}
