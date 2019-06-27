package com.nationwide.nf.rp.util;

import com.nationwide.nf.rp.bean.PrincipalAndInterest;
import com.nationwide.nf.rp.bean.RepaymentDetail;
import com.nationwide.nf.rp.bean.ReverseRepaymentDetail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by jorgej2 on 5/4/2017.
 */
@ContextConfiguration(locations = "classpath:rest-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class LoanWebServiceValidatorTest
{
    @Autowired
    private LoanWebServiceValidator loanWebServiceValidator;

    private String validLoanNumberAsString = "100";
    private String validRepayAmountAsString = "10,000,000.00";
    private String validRepayDateAsString = "2017-01-31";
    private String validTransactionNumberAsString = "1234";
    private String validEffectiveDateAsString = "2016-12-31";
    private String validRvTranNumberAsString = "4567";
    private String validBatchNumberAsString = "8910";
    private String ppeDateAsString = "2016-11-30";
    private RepaymentDetail validRepaymentDetail;
    private ReverseRepaymentDetail reverseRepaymentDetail;

    // getPrincipalAndInterest parameter validation tests.

    @Test
    public void validate_GetPrincipalAndInterest_input_parameters_success() throws Exception {
        assertTrue(loanWebServiceValidator.isValidGetPrincipalAndInterestInputParameters(validLoanNumberAsString,
                validRepayAmountAsString, validRepayDateAsString));
    }

    @Test
    public void validate_GetPrincipalAndInterest_input_parameters_invalid_loan_number() throws Exception {
        assertFalse(loanWebServiceValidator.isValidGetPrincipalAndInterestInputParameters("invalidLoanNumber",
                validRepayAmountAsString, validRepayDateAsString));
    }

    @Test
    public void validate_GetPrincipalAndInterest_input_parameters_invalid_repay_amount() throws Exception {
        assertFalse(loanWebServiceValidator.isValidGetPrincipalAndInterestInputParameters(validLoanNumberAsString,
                "100-00", validRepayDateAsString));
    }

    @Test
    public void validate_GetPrincipalAndInterest_input_parameters_invalid_repay_date_format() throws Exception {
        assertFalse(loanWebServiceValidator.isValidGetPrincipalAndInterestInputParameters(validLoanNumberAsString,
                validRepayAmountAsString, "12/01/2015"));
    }

    // updateRepayment parameter validation tests.

    @Test
    public void validate_updateRepayment_input_parameters_success() throws Exception {
        assertTrue(loanWebServiceValidator.isValidRepaymentInputParameters(validLoanNumberAsString,
                getRepaymentDetail(validBatchNumberAsString, validEffectiveDateAsString, ppeDateAsString)));
    }

    @Test
    public void validate_updateRepayment_input_parameters_invalid_loan_number() throws Exception {
        assertFalse(loanWebServiceValidator.isValidRepaymentInputParameters("10.00",
                getRepaymentDetail(validBatchNumberAsString, validEffectiveDateAsString, ppeDateAsString)));
    }

    @Test
    public void validate_updateRepayment_input_parameters_invalid_batch_number() throws Exception {
        assertFalse(loanWebServiceValidator.isValidRepaymentInputParameters(validLoanNumberAsString,
                getRepaymentDetail("invalid_batch_number", validEffectiveDateAsString, ppeDateAsString)));
    }

    @Test
    public void validate_updateRepayment_input_parameters_invalid_effective_date() throws Exception {
        assertFalse(loanWebServiceValidator.isValidRepaymentInputParameters(validLoanNumberAsString,
                getRepaymentDetail(validBatchNumberAsString, "invalid_effective_date", ppeDateAsString)));
    }

    @Test
    public void validate_updateRepayment_input_parameters_invalid_ppeDate_date() throws Exception {
        assertFalse(loanWebServiceValidator.isValidRepaymentInputParameters(validLoanNumberAsString,
                getRepaymentDetail(validBatchNumberAsString, validEffectiveDateAsString, "invalid_ppe_date")));
    }

    // updateReverseRepayment parameter validation tests.

    @Test
    public void validate_updateReverseRepayment_input_parameters_success() throws Exception {
        assertTrue(loanWebServiceValidator.isValidUpdateReverseRepaymentInputParameters(validLoanNumberAsString,
                getReverseRepaymentRequest(validTransactionNumberAsString, validRvTranNumberAsString, validEffectiveDateAsString)));
    }

    @Test
    public void validate_GetReverseRepayment_input_parameters_invalid_loan_number() throws Exception {
        assertFalse(loanWebServiceValidator.isValidUpdateReverseRepaymentInputParameters("invalid_loan_number",
                getReverseRepaymentRequest(validTransactionNumberAsString, validRvTranNumberAsString, validEffectiveDateAsString)));
    }

    @Test
    public void validate_GetReverseRepayment_input_parameters_invalid_transaction_number() throws Exception {
        assertFalse(loanWebServiceValidator.isValidUpdateReverseRepaymentInputParameters(validLoanNumberAsString,
                getReverseRepaymentRequest("invalid_transaction_number", validRvTranNumberAsString, validEffectiveDateAsString)));
    }

    @Test
    public void validate_GetReverseRepayment_input_parameters_invalid_rv_transaction_number() throws Exception {
        assertFalse(loanWebServiceValidator.isValidUpdateReverseRepaymentInputParameters(validLoanNumberAsString,
                getReverseRepaymentRequest(validTransactionNumberAsString, "invalid_rv_transaction_number", validEffectiveDateAsString)));
    }

    @Test
    public void validate_GetReverseRepayment_input_parameters_invalid_effective_date() throws Exception {
        assertFalse(loanWebServiceValidator.isValidUpdateReverseRepaymentInputParameters(validLoanNumberAsString,
                getReverseRepaymentRequest(validTransactionNumberAsString, validRvTranNumberAsString, "invalid_effective_date_string")));
    }

    private RepaymentDetail getRepaymentDetail(String batchNumber, String effectiveDate, String ppeDate) {
        PrincipalAndInterest[] principalAndInterests = getPrincipalAndInterests();
        RepaymentDetail validRepaymentDetail = new RepaymentDetail(batchNumber, effectiveDate, ppeDate, principalAndInterests);
        return validRepaymentDetail;
    }

    private PrincipalAndInterest[] getPrincipalAndInterests() {
        PrincipalAndInterest[] principalAndInterests = new PrincipalAndInterest[1];
        PrincipalAndInterest principalAndInterest = new PrincipalAndInterest("2", "1000.00", "10.00");
        principalAndInterests[0] = principalAndInterest;
        return principalAndInterests;
    }

    public ReverseRepaymentDetail getReverseRepaymentRequest(String transactionNumber, String rvTranNumber, String effectiveDate) {
        return new ReverseRepaymentDetail(transactionNumber, rvTranNumber, effectiveDate);
    }
}
