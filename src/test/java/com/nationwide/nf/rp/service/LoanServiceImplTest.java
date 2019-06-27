package com.nationwide.nf.rp.service;

import com.nationwide.nf.rp.bean.LoanCorrectionDetail;
import com.nationwide.nf.rp.bean.LoanDetail;
import com.nationwide.nf.rp.bean.RepaymentDetail;
import com.nationwide.nf.rp.bean.ReverseRepaymentDetail;
import com.nationwide.nf.rp.util.LoanWebServiceValidator;
import junit.framework.Assert;
import org.apache.log4j.Logger;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertTrue;

/**
 * Created by John Jorgensen on 5/25/2017.
 */
@ContextConfiguration(locations = "classpath:rest-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class LoanServiceImplTest {

    private String loanNumberForHappyPath = "-1";
    private String loanNumberReturningFatalError = "-2";
    private String loanNumberReturningUnexpectedFatalError = "-99";
    private String repayAmount = "100.00";
    private String repayDate = "2017-01-31";

    private Logger log = Logger.getLogger(getClass().getName());

    @Autowired
    LoanServiceImpl loanService;

    @Autowired
    LoanWebServiceValidator loanWebServiceValidator;

    @Test
    public void getPrincipalAndInterest_test_happy_path() throws Exception {
        LoanDetail loanDetail = loanService.getPrincipalAndInterest(loanWebServiceValidator.getNumber(loanNumberForHappyPath),
                                                                    loanWebServiceValidator.getAmount(repayAmount),
                                                                    loanWebServiceValidator.getDate(repayDate));
        Assert.assertTrue(loanDetail.getReturnCode().equalsIgnoreCase("Success"));
        Assert.assertTrue(loanDetail.getReturnMessage().equalsIgnoreCase("LR"));
        log.info(loanDetail);
    }

    @Test
    public void getPrincipalAndInterest_test_returning_fatal_error() throws Exception {
        LoanDetail loanDetail = loanService.getPrincipalAndInterest(loanWebServiceValidator.getNumber(loanNumberReturningFatalError),
                                                                    loanWebServiceValidator.getAmount(repayAmount),
                                                                    loanWebServiceValidator.getDate(repayDate));
        Assert.assertTrue(loanDetail.getReturnCode().equalsIgnoreCase("FatalError"));
        Assert.assertTrue(loanDetail.getReturnMessage().contains("getPrincipalAndInterest()"));
        log.info(loanDetail);
    }

    @Test
    public void getPrincipalAndInterest_test_returning_unexpected_fatal_error() throws Exception {
        LoanDetail loanDetail = loanService.getPrincipalAndInterest(loanWebServiceValidator.getNumber(loanNumberReturningUnexpectedFatalError),
                                                                    loanWebServiceValidator.getAmount(repayAmount),
                                                                    loanWebServiceValidator.getDate(repayDate));
        Assert.assertTrue(loanDetail.getReturnCode().equalsIgnoreCase("FatalError"));
        Assert.assertTrue(loanDetail.getReturnMessage().contains("Unexpected test loan number"));
        log.info(loanDetail);
    }

    @Test
    public void processRepayment_test_happy_path() throws Exception {
        LoanCorrectionDetail loanCorrectionDetail = loanService.processRepayment(loanWebServiceValidator.getNumber(loanNumberForHappyPath),
                                                                                 new RepaymentDetail());
        Assert.assertTrue(loanCorrectionDetail.getReturnCode().equalsIgnoreCase("Success"));
        Assert.assertTrue(loanCorrectionDetail.getDetailMessage().equalsIgnoreCase("LR"));
        log.info(loanCorrectionDetail);
    }

    @Test
    public void processRepayment_test_returning_fatal_error() throws Exception {
        LoanCorrectionDetail loanCorrectionDetail = loanService.processRepayment(
                loanWebServiceValidator.getNumber(loanNumberReturningFatalError), new RepaymentDetail());
        Assert.assertTrue(loanCorrectionDetail.getReturnCode().equalsIgnoreCase("FatalError"));
        Assert.assertTrue(loanCorrectionDetail.getDetailMessage().contains("processRepayment()"));
    }
    @Test
    public void processRepayment_test_returning_unexpected_fatal_error() throws Exception {
        LoanCorrectionDetail loanCorrectionDetail = loanService.processRepayment(
                loanWebServiceValidator.getNumber(loanNumberReturningUnexpectedFatalError), new RepaymentDetail());
        Assert.assertTrue(loanCorrectionDetail.getReturnCode().equalsIgnoreCase("FatalError"));
        Assert.assertTrue(loanCorrectionDetail.getDetailMessage().contains("Unexpected test loan number"));
    }

    @Test
    public void reverseRepayment_test_happy_path() throws Exception {
        LoanCorrectionDetail loanCorrectionDetail = loanService.reverseRepayment(
                loanWebServiceValidator.getNumber(loanNumberForHappyPath), new ReverseRepaymentDetail());
        Assert.assertTrue(loanCorrectionDetail.getReturnCode().equalsIgnoreCase("Success"));
        Assert.assertTrue(loanCorrectionDetail.getDetailMessage().equalsIgnoreCase("LR"));
    }

    @Test
    public void reverseRepayment_test_returning_fatal_error() throws Exception {
        LoanCorrectionDetail loanCorrectionDetail = loanService.reverseRepayment(
                loanWebServiceValidator.getNumber(loanNumberReturningFatalError), new ReverseRepaymentDetail());
        Assert.assertTrue(loanCorrectionDetail.getReturnCode().equalsIgnoreCase("FatalError"));
        Assert.assertTrue(loanCorrectionDetail.getDetailMessage().contains("processReverseRepayment()"));
    }

    @Test
    public void reverseRepayment_test_returning_unexpected_fatal_error() throws Exception {
        LoanCorrectionDetail loanCorrectionDetail = loanService.reverseRepayment(
                loanWebServiceValidator.getNumber(loanNumberReturningUnexpectedFatalError), new ReverseRepaymentDetail());
        Assert.assertTrue(loanCorrectionDetail.getReturnCode().equalsIgnoreCase("FatalError"));
        Assert.assertTrue(loanCorrectionDetail.getDetailMessage().contains("Unexpected test loan number"));
    }
}