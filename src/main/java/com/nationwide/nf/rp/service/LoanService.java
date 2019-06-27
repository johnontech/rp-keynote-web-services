package com.nationwide.nf.rp.service;

import com.nationwide.nf.rp.bean.*;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by John Jorgensen on 4/17/2017.
 */
public interface LoanService {

    LoanDetail getPrincipalAndInterest(int loanNumber, BigDecimal repayAmount, Date repayDate);

    LoanCorrectionDetail processRepayment(int loanNumberAsString, RepaymentDetail repaymentDetail);

    LoanCorrectionDetail reverseRepayment(int number, ReverseRepaymentDetail reverseRepaymentDetail);
}
