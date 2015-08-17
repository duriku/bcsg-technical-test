package exception;

/**
 * Created by duri on 8/16/2015.
 */
public class BankcardException extends RuntimeException{

    private static final String BANKCARD_EXCEPTION_PREFIX =  "BEX-";
    public static final String WRONG_PAN_LENGTH = BANKCARD_EXCEPTION_PREFIX + "101";
    public static final String WRONG_DATE_FORMAT = BANKCARD_EXCEPTION_PREFIX + "102";

    public static final String GENERAL_ERROR = BANKCARD_EXCEPTION_PREFIX + "501";


    private String errorMsg;

    private String errorCode;

    public BankcardException(String errorCode, String errorMsg){
        this.errorMsg = errorMsg;
        this.errorCode = errorCode;

    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
