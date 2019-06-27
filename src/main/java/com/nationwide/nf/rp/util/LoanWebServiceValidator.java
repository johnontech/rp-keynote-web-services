package com.nationwide.nf.rp.util;

import com.nationwide.nf.rp.bean.RepaymentDetail;
import com.nationwide.nf.rp.bean.ReverseRepaymentDetail;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.lang.Integer.parseInt;

/**
 * Created by John Jorgensen on 5/4/2017.
 */
@Component
public class LoanWebServiceValidator {

    private Logger log = Logger.getLogger(getClass().getName());

    public boolean isValidUpdateReverseRepaymentInputParameters(String loanNumberAsString, ReverseRepaymentDetail reverseRepaymentDetail) {
        if (isValidNumber(loanNumberAsString)) {
            return isValid(reverseRepaymentDetail);
        } else {
            log.error("Invalid Loan Number: '" + loanNumberAsString + "'");
            return false;
        }
    }

    public boolean isValidRepaymentInputParameters(String loanNumberAsString, RepaymentDetail repaymentDetail) {
        if (isValidNumber(loanNumberAsString)) {
            return isValid(repaymentDetail);
        } else {
            log.error("Invalid Loan Number: '" + loanNumberAsString + "'");
            return false;
        }
    }

    public boolean isValidGetPrincipalAndInterestInputParameters(String loanNumberAsString, String repayAmountAsString,
                                                                 String repayDateAsString) {
        if (isValidLoanNumber(loanNumberAsString) && isValidRepayAmount(repayAmountAsString) && isValidRepayDate(repayDateAsString)) {
            return true;
        }
        return false;
    }

    private boolean isValid(ReverseRepaymentDetail reverseRepaymentDetail) {
        if (!isValidTransactionNumber(reverseRepaymentDetail.getTransactionNumber())) {
            return false;
        }
        if (!isValidRvTransactionNumber(reverseRepaymentDetail.getRvTranNumber())) {
            return false;
        }
        if (!isValidEffectiveDate(reverseRepaymentDetail.getEffectiveDate())) {
            return false;
        }
        return true;
    }

    private boolean isValid(RepaymentDetail repaymentDetail) {
        if (!isValidNumber(repaymentDetail.getBatchNumber())) {
            log.error("Invalid Batch Number: '" + repaymentDetail.getBatchNumber() + "'");
            return false;
        }
        if (!isValidDate(repaymentDetail.getEffectiveDate())) {
            log.error("Invalid Effective Date: '" + repaymentDetail.getEffectiveDate() + "'");
            return false;
        }
        if (!isValidDate(repaymentDetail.getPpeDate())) {
            log.error("Invalid PPE Date: '" + repaymentDetail.getPpeDate() + "'");
            return false;
        }
        return true;
    }

    private boolean isValidEffectiveDate(String effectiveDateAsString) {
        if (!isValidDate(effectiveDateAsString)) {
            log.error("Invalid Effective Date: '" + effectiveDateAsString + "'");
            return false;
        }
        return true;
    }

    private boolean isValidTransactionNumber(String transactionNumberAsString) {
        if (isValidNumber(transactionNumberAsString)) {
            return true;
        } else {
            log.error("Invalid Transaction Number: '" + transactionNumberAsString + "'");
            return false;
        }
    }

    private boolean isValidRvTransactionNumber(String rvTranNumberAsString) {
        if (isValidNumber(rvTranNumberAsString)) {
            return true;
        } else {
            log.error("Invalid RV Transaction Number: '" + rvTranNumberAsString + "'");
            return false;
        }
    }

    private boolean isValidRepayDate(String repayDateAsString) {
        if (isValidDate(repayDateAsString)) {
            return true;
        } else {
            log.error("Invalid Repay Date Format: '" + repayDateAsString + "'");
            return false;
        }
    }

    private boolean isValidDate(String dateAsString) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        ;
        try {
            simpleDateFormat.parse(dateAsString);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    private boolean isValidRepayAmount(String repayAmountAsString) {
        if (isValidAmount(repayAmountAsString)) {
            return true;
        } else {
            log.error("Invalid Repay Amount Format: '" + repayAmountAsString + "'");
            return false;
        }
    }

    private boolean isValidAmount(String repayAmountAsString) {
        String numberAmount = repayAmountAsString.replaceAll("\\.", "").replaceAll("\\,", "");
        return isValidNumber(numberAmount);
    }

    private boolean isValidLoanNumber(String loanNumberAsString) {
        if (isValidNumber(loanNumberAsString)) {
            return true;
        } else {
            log.error("Invalid Loan Number: '" + loanNumberAsString + "'");
            return false;
        }
    }

    private boolean isValidNumber(String numberAsString) {
        try {
            Integer.parseInt(numberAsString);
        } catch (NumberFormatException numberFormatException) {
            return false;
        }
        return true;
    }

    public int getNumber(String loanNumberString) {
        return parseInt(loanNumberString);
    }

    public BigDecimal getAmount(String repayAmountString) {
        return new BigDecimal(repayAmountString);
    }

    public Date getDate(String dateString) {
        Date date = null;
        String dateFormat = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        ;
        try {
            date = simpleDateFormat.parse(dateString);
        } catch (ParseException e) {
            log.error("An error occurred when attempting to parse date string '" + dateString + "', expected format was '" + dateFormat + "'", e);
            e.printStackTrace();
        }
        return date;
    }

}
