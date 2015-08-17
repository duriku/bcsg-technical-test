package service.impl;

import entity.Bankcard;
import service.BankcardService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by duri on 8/16/2015.
 */
public class BankcardServiceImpl implements BankcardService {

    List<Bankcard> bankcards;

    public BankcardServiceImpl(){
        bankcards = new ArrayList<>();
    }

    @Override
    public Bankcard addBankcard(Bankcard bankcard){
        bankcards.add(bankcard);
        return bankcard;
    }

    @Override
    public List<Bankcard> getBankcardsInSortedOrderByExipiryTime() {
        return bankcards.stream().sorted((b1, b2) -> b2.getExpiryDate().compareTo(b1.getExpiryDate())).
                collect(Collectors.toList());
    }
}
