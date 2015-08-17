package service;

import entity.Bankcard;

import java.util.List;

/**
 * Created by duri on 8/16/2015.
 */
public interface BankcardService {
    Bankcard addBankcard(Bankcard bankcard);

    List<Bankcard> getBankcardsInSortedOrderByExipiryTime();
}
