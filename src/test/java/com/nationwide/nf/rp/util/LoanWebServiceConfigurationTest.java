package com.nationwide.nf.rp.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.junit.Assert.assertNotNull;
import org.apache.log4j.Logger;

/**
 * Created by John Jorgensen on 5/15/2017.
 */
@ContextConfiguration(locations = "classpath:rest-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class LoanWebServiceConfigurationTest {

    private Logger log = Logger.getLogger(getClass().getName());

    @Test
    public void getApplicationServer() throws Exception {
        assertNotNull(WebServiceRPLoanEJBClient.getApplicationServer());
        log.info("Application Server: " + WebServiceRPLoanEJBClient.getApplicationServer());
    }

    @Test
    public void getTomcatJndiLookupString() throws Exception {
        assertNotNull(WebServiceRPLoanEJBClient.getTomcatJndiLookupString());
        log.info("Tomcat Jndi Lookup String: " + WebServiceRPLoanEJBClient.getTomcatJndiLookupString());
    }

    @Test
    public void getWebSphereInitialContextFactory() throws Exception {
        assertNotNull(WebServiceRPLoanEJBClient.getWebSphereInitialContextFactory());
        log.info("WebSphere Initial Context Factory: " + WebServiceRPLoanEJBClient.getWebSphereInitialContextFactory());
    }

    @Test
    public void getWebSphereProviderUrl() throws Exception {
        assertNotNull(WebServiceRPLoanEJBClient.getWebSphereProviderUrl());
        log.info("WebSphere Provider Url: " + WebServiceRPLoanEJBClient.getWebSphereProviderUrl());
    }

    @Test
    public void getWebSphereInitialContextEjbHomeInterfaceLookup() throws Exception {
        assertNotNull(WebServiceRPLoanEJBClient.getWebSphereInitialContextEjbHomeInterfaceLookup());
        log.info("WebSphere Initial Context EjbHome Interface Lookup: " + WebServiceRPLoanEJBClient.getWebSphereInitialContextEjbHomeInterfaceLookup());
    }
}