package com.nationwide.nf.rp.service;

import com.nationwide.nf.rp.bean.*;
import com.nationwide.nf.rp.util.LoanWebServiceValidator;
import com.nationwide.nf.rp.util.WebServiceRPLoanEJBClient;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rploanscommon.KNCorrectionResponse;
import rploanscommon.KNPrincipalAdjustment;
import rploanscommon.KNPrincipalInterest;
import rploanscommon.KNPrincipalInterestResponse;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by John Jorgensen on 4/17/2017.
 */
@Service
public class LoanServiceImpl implements LoanService {

    private static final int HAPPY_PATH_TEST_LOAN_NUMBER = -1;
    private static final int FATAL_ERROR_TEST_LOAN_NUMBER = -2;

    @Autowired
    LoanWebServiceValidator validator;

    @Autowired
    WebServiceRPLoanEJBClient webServiceRPLoanEJBClient;

    private Logger log = Logger.getLogger(getClass().getName());

    /**
     * Get the principal and interest for this loan number.
     *
     * @param loanNumber  loan number
     * @param repayAmount repayment amount
     * @param repayDate   repayment date
     * @return LoanDetail loan detail consisting of interest and principal for this loan number
     */
    public LoanDetail getPrincipalAndInterest(int loanNumber, BigDecimal repayAmount, Date repayDate) {
        if (isTestLoanNumber(loanNumber)) {
            return getPrincipalAndInterestReturnTestData(loanNumber, repayAmount, repayDate);

        // Call EJB Server using EJB Client.
        } else {
            return getKNPrincipalInterestResponse(loanNumber, repayAmount, repayDate);
        }
    }

    /**
     * Process repayment(s) for loan.
     *
     * @param loanNumber      loan number
     * @param repaymentDetail contains batch number, effective date, ppe date, principle and interest information
     * @return LoanCorrectionDetail  loan correction detail after repayment processing
     */
    public LoanCorrectionDetail processRepayment(int loanNumber, RepaymentDetail repaymentDetail) {
        LoanCorrectionDetail loanCorrectionDetail;

        // Return test response
        if (isTestLoanNumber(loanNumber)) {
            return processRepaymentReturnTestData(loanNumber, repaymentDetail);
        } else {

            // Call EJB Server using EJB Client.
            try {
                KNCorrectionResponse knCorrectionResponse = webServiceRPLoanEJBClient.processRepayment(loanNumber,
                Integer.parseInt(repaymentDetail.getBatchNumber()), validator.getDate(repaymentDetail.getEffectiveDate()),
                validator.getDate(repaymentDetail.getPpeDate()), getPrincipalAndInterests(repaymentDetail.getPrincipalAndInterest()));
                loanCorrectionDetail = new LoanCorrectionDetail(knCorrectionResponse);
            } catch (Throwable e) {
                log.error("A Fatal Error occurred when processRepayment: ", e);
                loanCorrectionDetail = new LoanCorrectionDetail("FatalError", e.getMessage());
            }
            return loanCorrectionDetail;
        }
    }

    /**
     * This method gets the loan correction detail for the identified reverse repayment.
     *
     * @param loanNumber loan number
     * @return LoanCorrectionDetail  loan correction detail for this reverse repayment
     */
    public LoanCorrectionDetail reverseRepayment(int loanNumber, ReverseRepaymentDetail reverseRepaymentDetail) {
        LoanCorrectionDetail loanCorrectionDetail;

        // Return test response
        if (isTestLoanNumber(loanNumber)) {
            return processReverseRepaymentReturnTestData(loanNumber, reverseRepaymentDetail);
        } else {

            // Call EJB Server using EJB Client.
            try {
                KNCorrectionResponse knCorrectionResponse = webServiceRPLoanEJBClient.reverseRepayment(loanNumber,
                        validator.getNumber(reverseRepaymentDetail.getTransactionNumber()),
                        validator.getDate(reverseRepaymentDetail.getEffectiveDate()),
                        validator.getNumber(reverseRepaymentDetail.getRvTranNumber()));
                loanCorrectionDetail = new LoanCorrectionDetail(knCorrectionResponse);
            } catch (Throwable e) {
                log.error("An error occurred when calling rpClient1.reverseRepayment: ", e);
                loanCorrectionDetail = new LoanCorrectionDetail("FatalError", e.getMessage());
            }
        }
        return loanCorrectionDetail;
    }

    private ArrayList<KNPrincipalInterest> getPrincipalAndInterests(PrincipalAndInterest[] principalAndInterests) {
        KNPrincipalInterest[] knPrincipalInterests = new KNPrincipalInterest[principalAndInterests.length];
        for (int i = 0; i < principalAndInterests.length; i++) {
            KNPrincipalInterest knPrincipalInterest = new KNPrincipalInterest();
            knPrincipalInterest.setInterest(principalAndInterests[i].getInterest());
            knPrincipalInterest.setPrincipal(principalAndInterests[i].getPrincipal());
            knPrincipalInterest.setSource(principalAndInterests[i].getSource());
            knPrincipalInterests[i] = knPrincipalInterest;
        }
        return new ArrayList<KNPrincipalInterest>(Arrays.asList(knPrincipalInterests));
    }

    private LoanDetail getKNPrincipalInterestResponse(Integer loanNumber, BigDecimal repayAmount, Date repayDate) {
        LoanDetail loanDetail;
        try {
            KNPrincipalInterestResponse knPrincipalInterestResponse =
                    webServiceRPLoanEJBClient.getPrincipalAndInterest(loanNumber, repayAmount, repayDate);
            return getLoanDetail(knPrincipalInterestResponse);
        } catch (Throwable e) {
            log.error("A Fatal Error occurred when calling getPrincipalAndInterest: ", e);
            loanDetail = new LoanDetail("FatalError", e.getMessage());
        }
        return loanDetail;
    }

    private LoanDetail getLoanDetail(KNPrincipalInterestResponse knPrincipalInterestResponse) {
        LoanDetail loanDetail = new LoanDetail(knPrincipalInterestResponse.getReturnCode(),
                knPrincipalInterestResponse.getDetailMessage());

        PrincipalAndInterest[] principalAndInterests = new PrincipalAndInterest[knPrincipalInterestResponse.getSplits().size()];
        Collection<KNPrincipalInterest> knPrincipalInterests = knPrincipalInterestResponse.getSplits();
        Iterator<KNPrincipalInterest> iterator = knPrincipalInterests.iterator();

        for (int i = 0; i < knPrincipalInterestResponse.getSplits().size(); i++) {
            KNPrincipalInterest knPrincipalInterest = iterator.next();
            PrincipalAndInterest principalAndInterest = new PrincipalAndInterest(knPrincipalInterest.getSource(),
                    knPrincipalInterest.getPrincipal(),
                    knPrincipalInterest.getInterest());
            principalAndInterests[i] = principalAndInterest;
        }
        loanDetail.setPrincipalAndInterest(principalAndInterests);

        return loanDetail;
    }

    // Test Methods
    private LoanDetail getPrincipalInterestResponseTestData() {
        LoanDetail loanDetail = new LoanDetail("Success", "LR");
        loanDetail.setPrincipalAndInterest(getPrincipalAndInterests());
        return loanDetail;
    }

    private PrincipalAndInterest[] getPrincipalAndInterests() {
        PrincipalAndInterest[] principalAndInterests = new PrincipalAndInterest[2];
        PrincipalAndInterest principalAndInterest = new PrincipalAndInterest("2", "1000.00", "10.00");
        principalAndInterests[0] = principalAndInterest;
        principalAndInterest = new PrincipalAndInterest("4", "500.00", "5.00");
        principalAndInterests[1] = principalAndInterest;
        return principalAndInterests;
    }

    private KNCorrectionResponse getHappyPathTestKnCorrectionResponse() {
        KNCorrectionResponse knCorrectionResponse = new KNCorrectionResponse();
        knCorrectionResponse.setReturnCode("Success");
        knCorrectionResponse.setDetailMessage("LR");

        List<KNPrincipalAdjustment> knPrincipalAdjustmentList = new ArrayList<KNPrincipalAdjustment>();

        KNPrincipalAdjustment knPrincipalAdjustment = getFirstKnPrincipalAdjustment();
        knPrincipalAdjustmentList.add(knPrincipalAdjustment);

        knPrincipalAdjustment = getSecondKnPrincipalAdjustment();
        knPrincipalAdjustmentList.add(knPrincipalAdjustment);

        knCorrectionResponse.setAdjustments(knPrincipalAdjustmentList);
        return knCorrectionResponse;
    }

    private KNPrincipalAdjustment getFirstKnPrincipalAdjustment() {
        Map<String, String> sourcePrincipalAdjustment = new HashMap<String, String>();
        sourcePrincipalAdjustment.put("Source", "2");
        sourcePrincipalAdjustment.put("Principal", "500.0");

        KNPrincipalAdjustment knPrincipalAdjustment = new KNPrincipalAdjustment();
        knPrincipalAdjustment.setPpeDate("2012-12-03");
        knPrincipalAdjustment.setEffectiveDate("2017-12-03");
        knPrincipalAdjustment.setSourcePrincipalAdjustment(sourcePrincipalAdjustment);
        return knPrincipalAdjustment;
    }

    private KNPrincipalAdjustment getSecondKnPrincipalAdjustment() {
        Map<String, String> sourcePrincipalAdjustment;
        KNPrincipalAdjustment knPrincipalAdjustment;
        sourcePrincipalAdjustment = new HashMap<String, String>();
        sourcePrincipalAdjustment.put("Source", "4");
        sourcePrincipalAdjustment.put("Principal", "1000.0");

        knPrincipalAdjustment = new KNPrincipalAdjustment();
        knPrincipalAdjustment.setEffectiveDate("2017-12-03");
        knPrincipalAdjustment.setPpeDate("2012-12-03");
        knPrincipalAdjustment.setSourcePrincipalAdjustment(sourcePrincipalAdjustment);
        return knPrincipalAdjustment;
    }

    private boolean isTestLoanNumber(Integer loanNumber) {
        return (loanNumber < 0);
    }

    private LoanDetail getPrincipalAndInterestReturnTestData(Integer loanNumber, BigDecimal repayAmount, Date repayDate) {
        log.info("Generating test response for getPrincipalAndInterest with parameters: " + "Loan Number '" +
                loanNumber + "', Repay Amount '" + repayAmount + "', Repay Date '" + repayDate + "'");
        if (loanNumber == HAPPY_PATH_TEST_LOAN_NUMBER) {
            return getPrincipalInterestResponseTestData();
        } else if (loanNumber == FATAL_ERROR_TEST_LOAN_NUMBER) {
            return new LoanDetail("FatalError",
                    "This is a test message for FatalError when -2 is passed in for Loan Number, " +
                                  "returned from getPrincipalAndInterest()");
        } else {
            return new LoanDetail("FatalError",
                    "Unexpected test loan number, loanNumber: '" + loanNumber + "'. Valid test loan numbers are: " +
                    "'. Valid test loan numbers are: -1 - Happy Path, and -2 - FatalError. returned from getPrincipalAndInterest()");
        }
    }

    private LoanCorrectionDetail processRepaymentReturnTestData(int loanNumber, RepaymentDetail repaymentDetail) {
        log.info("Calling processRepayment with parameters: Loan Number '" + loanNumber +
                "', Repayment Detail '" + repaymentDetail);
        if (loanNumber == HAPPY_PATH_TEST_LOAN_NUMBER) {
            return new LoanCorrectionDetail(getHappyPathTestKnCorrectionResponse());

        } else if (loanNumber == FATAL_ERROR_TEST_LOAN_NUMBER) {
            return new LoanCorrectionDetail("FatalError",
            "This is a test message for FatalError when -2 is passed in for Loan Number, " +
                    "returned from processRepayment()");
        } else {
            return new LoanCorrectionDetail("FatalError",
                "Unexpected test loan number, loanNumber: '" + loanNumber + "'. Valid test loan numbers are: " +
                             "'. Valid test loan numbers are: -1 - Happy Path, and -2 - FatalError. returned from processRepayment()");
        }
    }

    private LoanCorrectionDetail processReverseRepaymentReturnTestData(int loanNumber, ReverseRepaymentDetail reverseRepaymentDetail) {
        log.info("Generating test response for reverseRepayment with parameters: " + "Loan Number '" +
                loanNumber + "', reverseRepaymentDetail '" + reverseRepaymentDetail);
        if (loanNumber == HAPPY_PATH_TEST_LOAN_NUMBER) {
            return new LoanCorrectionDetail(getHappyPathTestKnCorrectionResponse());
        } else if (loanNumber == FATAL_ERROR_TEST_LOAN_NUMBER) {
            return new LoanCorrectionDetail("FatalError",
                    "This is a test message for FatalError when -2 is passed in for Loan Number, " +
                            "returned from processReverseRepayment()");
        } else {
            return new LoanCorrectionDetail("FatalError",
                    "Unexpected test loan number, loanNumber: '" + loanNumber + "'. Valid test loan numbers are: " +
                            "'. Valid test loan numbers are: -1 - Happy Path, and -2 - FatalError. returned from processReverseRepayment()");
        }
    }
}
