package entity;

import exception.BankcardException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by duri on 8/16/2015.
 */
public class Bankcard {

    private String bank;
    private String maskedPan;
    private String pan;
    private Date expiryDate;

    public Bankcard(String bank, String pan, String expiryDate, String maskedPan) {
        this.bank = bank;
        this.pan = pan;
        this.expiryDate = convertExpiryDateStringToDate(expiryDate);
        this.maskedPan = maskedPan;
    }

    private Date convertExpiryDateStringToDate(String expiryDate){
        try {
            DateFormat format = new SimpleDateFormat("MMM-yyyy", Locale.ENGLISH);
            return format.parse(expiryDate);
        } catch (ParseException e) {
            throw new BankcardException(BankcardException.WRONG_DATE_FORMAT,"Wrong expiry date format!");
        }
    }

    public String getBank() {
        return bank;
    }

    public String getMaskedPan() {
        return maskedPan;
    }

    public String getPan() {
        return pan;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bankcard bankcard = (Bankcard) o;

        if (bank != null ? !bank.equals(bankcard.bank) : bankcard.bank != null) return false;
        if (maskedPan != null ? !maskedPan.equals(bankcard.maskedPan) : bankcard.maskedPan != null) return false;
        if (pan != null ? !pan.equals(bankcard.pan) : bankcard.pan != null) return false;
        return !(expiryDate != null ? !expiryDate.equals(bankcard.expiryDate) : bankcard.expiryDate != null);

    }

    @Override
    public int hashCode() {
        int result = bank != null ? bank.hashCode() : 0;
        result = 31 * result + (maskedPan != null ? maskedPan.hashCode() : 0);
        result = 31 * result + (pan != null ? pan.hashCode() : 0);
        result = 31 * result + (expiryDate != null ? expiryDate.hashCode() : 0);
        return result;
    }
}
