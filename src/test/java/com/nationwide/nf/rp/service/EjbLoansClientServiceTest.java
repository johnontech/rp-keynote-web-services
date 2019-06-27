package com.nationwide.nf.rp.service;

import com.nationwide.nf.rp.bean.LoanDetail;
import com.nationwide.nf.rp.bean.PrincipalAndInterest;
import org.junit.Ignore;
import org.junit.Test;
import com.nationwide.nf.rp.util.WebServiceRPLoanEJBClient;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import rploanscommon.KNCorrectionResponse;
import rploanscommon.KNPrincipalAdjustment;
import rploanscommon.KNPrincipalInterest;
import rploanscommon.KNPrincipalInterestResponse;

import javax.ejb.CreateException;
import javax.naming.NamingException;
import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by John Jorgensen on 4/17/2017.
 */
@ContextConfiguration(locations = "classpath:rest-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class EjbLoansClientServiceTest {

    private Logger log = Logger.getLogger(getClass().getName());

    @Ignore
    @Test
    public void get_principal_and_interest_success() {
        int loanNumber = 100173259;
        BigDecimal repayAmount = new BigDecimal("50.00");
        Date repayDate = new Date();
        KNPrincipalInterestResponse knPrincipalInterestResponse = null;
        try {
            knPrincipalInterestResponse = WebServiceRPLoanEJBClient.getPrincipalAndInterest(loanNumber, repayAmount, repayDate);
        } catch (CreateException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }

        log.info("*** Raw output:      " + knPrincipalInterestResponse.toString());
        log.info("*** Return code:     " + knPrincipalInterestResponse.getReturnCode());
        log.info("*** Detail message:  " + knPrincipalInterestResponse.getDetailMessage());

        PrincipalAndInterest[] principalAndInterests = new PrincipalAndInterest[knPrincipalInterestResponse.getSplits().size()];
        Collection<KNPrincipalInterest> knPrincipalInterests = knPrincipalInterestResponse.getSplits();
        Iterator<KNPrincipalInterest> iterator = knPrincipalInterests.iterator();

        for (int i = 0; i < knPrincipalInterestResponse.getSplits().size(); i++) {
            KNPrincipalInterest knPrincipalInterest = iterator.next();
            log.info("*** Source:     " + knPrincipalInterest.getSource());
            log.info("*** Principal:  " + knPrincipalInterest.getPrincipal());
            log.info("*** Interest:   " + knPrincipalInterest.getInterest());
            PrincipalAndInterest principalAndInterest = new PrincipalAndInterest(knPrincipalInterest.getSource(),
                                                                                 knPrincipalInterest.getPrincipal(),
                                                                                 knPrincipalInterest.getInterest());
            principalAndInterests[i] = principalAndInterest;
        }
        LoanDetail loanDetail = new LoanDetail(knPrincipalInterestResponse.getReturnCode(),
                                               knPrincipalInterestResponse.getDetailMessage());
        loanDetail.setPrincipalAndInterest(principalAndInterests);
    }

    @Ignore
    @Test
    public void get_reverse_repayment_success() throws Exception {
        int rvTranNumber = 773124535;
        int loanNumber = 100009439;
        int tranNumber = 773124535;
        Date effectiveDate = getDate("2012-12-03");

        KNCorrectionResponse knCorrectionResponse = WebServiceRPLoanEJBClient.reverseRepayment(loanNumber, tranNumber, effectiveDate, rvTranNumber);
        log.info("Detail Message: " + knCorrectionResponse.getDetailMessage());
        log.info("Return Code:    " + knCorrectionResponse.getReturnCode());

        Collection<KNPrincipalAdjustment> adjustments = knCorrectionResponse.getAdjustments();
        for (KNPrincipalAdjustment adjustment : adjustments) {
            Map<String, String> sourcePrincipalAdjustmentMap = adjustment.getSourcePrincipalAdjustment();
            log.info("sourcePrincipalAdjustmentMap: " + sourcePrincipalAdjustmentMap);
            log.info("PpeDate: " + adjustment.getPpeDate());
            log.info("EffectiveDate: " + adjustment.getEffectiveDate());
        }
    }

    @Ignore
    @Test
    public void inputParameterValidation() throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String repayDateString = "2017-04-17";
        Date repayDate = null;
        try {
            repayDate = format.parse(repayDateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Integer loanNumber = Integer.parseInt("1");
        BigDecimal repayAmount = new BigDecimal("50.00");
    }

    public Date getDate(String dateString) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}