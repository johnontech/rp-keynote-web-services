package com.nationwide.nf.rp.util;

import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.springframework.stereotype.Component;
import rploans.RPLoanServiceRemote;
import rploans.RPLoanServiceRemoteHome;
import rploanscommon.DateUtil;
import rploanscommon.KNCorrectionResponse;
import rploanscommon.KNPrincipalInterest;
import rploanscommon.KNPrincipalInterestResponse;

import javax.ejb.CreateException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Hashtable;

/**
 * This class is used to look up the Retirement Planning EJB Home Interface
 * and to call methods on the Retirement Planning Loan Web Service APIs.
 */
@Component
public class WebServiceRPLoanEJBClient {

    private static final String WEBSPHERE_APPLICATION_SERVER = "websphere";

    private static RPLoanServiceRemoteHome rpEJBHome = null;
    private static Logger log = Logger.getLogger(WebServiceRPLoanEJBClient.class);

    private static String applicationServer;
    private static String tomcatJndiLookupString;
    private static String webSphereInitialContextFactory;
    private static String webSphereProviderUrl;
    private static String webSphereInitialContextEjbHomeInterfaceLookup;

    public static KNPrincipalInterestResponse getPrincipalAndInterest(int loanNumber,
                                                                      BigDecimal repaymentAmount, Date effectiveDate)
            throws SQLException, CreateException, RemoteException, NamingException {
        MDC.put("loanNumber", loanNumber);
        MDC.put("remoteCall", "getPrincipalAndInterest");
        return getSplitsResponse(Integer.toString(loanNumber), repaymentAmount, effectiveDate);
    }

    public static KNCorrectionResponse reverseRepayment(int loanNumber, int tranNumber, Date effectiveDate,
                                                        int rvTranNumber)
            throws SQLException, CreateException, RemoteException, NamingException {
        MDC.put("loanNumber", loanNumber);
        MDC.put("remoteCall", "reverseRepayment");
        return getReverseRepaymentResponse(Integer.toString(loanNumber), Integer
                .toString(tranNumber), effectiveDate, Integer.toString(rvTranNumber));
    }

    public static KNCorrectionResponse processRepayment(int loanNumber, int batchNumber, Date effectiveDate,
                                                        Date ppeDate, ArrayList<KNPrincipalInterest> sourcePISplit)
            throws SQLException, CreateException, RemoteException, NamingException {
        MDC.put("loanNumber", loanNumber);
        MDC.put("remoteCall", "processRepayment");
        return getProcessRepaymentResponse(Integer.toString(batchNumber),
                Integer.toString(loanNumber), ppeDate, effectiveDate,
                sourcePISplit);
    }

    private static KNPrincipalInterestResponse getSplitsResponse(String loanNumber, BigDecimal repaymentAmount, Date effectiveDate)
            throws CreateException, RemoteException, NamingException {
        RPLoanServiceRemote rpr = getRpLoanServiceRemoteInterface();
        return rpr.getPrincipalInterest(loanNumber, repaymentAmount.toString(), DateUtil.convertDateToString(effectiveDate), log);
    }

    private static KNCorrectionResponse getProcessRepaymentResponse(String batchNumber, String loanNumber, Date ppeDate,
                                                                    Date effectiveDate, Collection<KNPrincipalInterest> sourcePISplit)
            throws CreateException, RemoteException, SQLException, NamingException {
        RPLoanServiceRemote rpr = getRpLoanServiceRemoteInterface();
        return rpr.processRepayment(batchNumber, loanNumber, DateUtil.convertDateToString(ppeDate),
                DateUtil.convertDateToString(effectiveDate), sourcePISplit);
    }

    private static KNCorrectionResponse getReverseRepaymentResponse(
            String loanNumber, String tranNumber, Date effectiveDate,
            String rvTranNumber) throws CreateException, RemoteException, NamingException {
        RPLoanServiceRemote rpr = getRpLoanServiceRemoteInterface();
        return rpr.reverseRepayment(loanNumber, tranNumber, null,
                DateUtil.convertDateToString(effectiveDate), rvTranNumber);
    }

    /**
     * This method returns the Retirement Planning Loan Service Remote Interface which is used when
     * calling APIs on the EJB Remote Service Bean.
     *
     * @return the EJB Remote Service Bean
     */
    public static RPLoanServiceRemote getRpLoanServiceRemoteInterface() throws CreateException, RemoteException, NamingException {
        if (rpEJBHome == null) {
            Object initialContext;
            if (isRunningInWebSphereApplicationServer()) {
                initialContext = getWebSphereRpLoanServiceInitialContextNamedObject();
            } else {
                initialContext = getDefaultRpLoanServiceInitialContextNamedObject();
            }
            rpEJBHome = (RPLoanServiceRemoteHome) PortableRemoteObject.narrow(initialContext, RPLoanServiceRemoteHome.class);
        }
        return rpEJBHome.create();
    }

    /**
     * This method is used when looking up the named object associated with the Initial Context created
     * when connecting to the Ejb Server from Tomcat Application Server or standalone Java Application
     * This method is used solely for testing purposes. The getWebSphereRpLoanServiceInitialContextNamedObject
     * method is used when connecting to the EJB Server in the production environment.
     *
     * @return the named object associated with the Initial Context
     */
    public synchronized static Object getDefaultRpLoanServiceInitialContextNamedObject() throws NamingException {
        InitialContext initialContext = new InitialContext();
        return initialContext.lookup(getTomcatJndiLookupString());
    }

    /**
     * This method is used when looking up the named object associated with the Initial Context created
     * when connecting to the Ejb Server from WebSphere Application Server. The
     * getWebSphereRpLoanServiceInitialContextNamedObject method is used when connecting to the EJB Server
     * in the production environment.
     *
     * @return the Retirement Planning EJB Home Interface
     */
    public static synchronized Object getWebSphereRpLoanServiceInitialContextNamedObject() throws NamingException {
        Hashtable pdEnv = new Hashtable();
        pdEnv.put(Context.INITIAL_CONTEXT_FACTORY, getWebSphereInitialContextFactory());
        pdEnv.put(Context.PROVIDER_URL, getWebSphereProviderUrl());
        InitialContext initialContext = new InitialContext(pdEnv);
        return initialContext.lookup(getWebSphereInitialContextEjbHomeInterfaceLookup());
    }

    public static boolean isRunningInWebSphereApplicationServer() {
        return (WEBSPHERE_APPLICATION_SERVER.equalsIgnoreCase(getApplicationServer())) ? true : false;
    }

    public void setApplicationServer(String applicationServer) {
        this.applicationServer = applicationServer;
    }

    public static String getApplicationServer() {
        return applicationServer;
    }

    public void setTomcatJndiLookupString(String tomcatJndiLookupString) {
        this.tomcatJndiLookupString = tomcatJndiLookupString;
    }

    public static String getTomcatJndiLookupString() {
        return tomcatJndiLookupString;
    }

    public void setWebSphereInitialContextFactory(String webSphereInitialContextFactory) {
        this.webSphereInitialContextFactory = webSphereInitialContextFactory;
    }

    public static String getWebSphereInitialContextFactory() {
        return webSphereInitialContextFactory;
    }

    public void setWebSphereProviderUrl(String webSphereProviderUrl) {
        this.webSphereProviderUrl = webSphereProviderUrl;
    }

    public static String getWebSphereProviderUrl() {
        return webSphereProviderUrl;
    }

    public void setWebSphereInitialContextEjbHomeInterfaceLookup(String webSphereInitialContextEjbHomeInterfaceLookup) {
        this.webSphereInitialContextEjbHomeInterfaceLookup = webSphereInitialContextEjbHomeInterfaceLookup;
    }

    public static String getWebSphereInitialContextEjbHomeInterfaceLookup() {
        return webSphereInitialContextEjbHomeInterfaceLookup;
    }
}
