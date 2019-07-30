package com.nationwide.nf.rp.service;

import com.nationwide.nf.rp.bean.DocuSignConfiguration;
import com.nationwide.nf.rp.util.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import com.nationwide.nf.rp.data.dao.dao.JdbcDocuSignSubscriberFeedDao;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

@ContextConfiguration(locations = "classpath:rest-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class DocuSignSubscriptionServiceImplTest {

    public static final String UPDATED_DIRECTORY_NAME = "updated directory";
    private Logger log = Logger.getLogger(getClass().getName());

    @Autowired
    private JdbcDocuSignSubscriberFeedDao jdbcDocuSignSubscriberFeedDao;

    @Autowired
    DocuSignSubscriptionService docuSignSubscriptionService;

    @Autowired
    DateUtil dateUtil;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getDocuSignSubscription() {
        DocuSignConfiguration docuSignConfiguration = docuSignSubscriptionService.getDocuSignSubscription("1");
        log.info("DocuSign Configuration: " + docuSignConfiguration);
        assertTrue("A docusign configurations was returned", StringUtils.isNotBlank(docuSignConfiguration.getSubscriptionName()));
    }

    @Test
    public void getAllDocuSignSubscriptions() {
        DocuSignConfiguration[] docuSignConfigurations = docuSignSubscriptionService.getAllDocuSignSubscriptions();
        assertTrue("Docusign configurations were returned", docuSignConfigurations.length > 0);

        for (DocuSignConfiguration docuSignConfiguration : docuSignConfigurations) {
            log.info(docuSignConfiguration);
        }
    }

    @Test
    public void updateDocuSignConfiguration() {
        DocuSignConfiguration docuSignConfiguration = docuSignSubscriptionService.getDocuSignSubscriptionForId("1");
        docuSignConfiguration.setFileTransferDirectory(UPDATED_DIRECTORY_NAME);
        if (docuSignConfiguration.getSubscriptionStatus().equalsIgnoreCase("Active")) {
            docuSignConfiguration.setSubscriptionStatus("A");
        } else if (docuSignConfiguration.getSubscriptionStatus().equalsIgnoreCase("Terminated")) {
            docuSignConfiguration.setSubscriptionStatus("T");
        } else if (docuSignConfiguration.getSubscriptionStatus().equalsIgnoreCase("Hold")) {
            docuSignConfiguration.setSubscriptionStatus("H");
        }

        int numberOfRowsUpdated = docuSignSubscriptionService.updateDocuSignConfiguration(docuSignConfiguration);
        assertTrue("Number of rows updated should be one", numberOfRowsUpdated == 1);

        DocuSignConfiguration returnedDocuSignConfiguration = docuSignSubscriptionService.getDocuSignSubscription("1");
        log.info(returnedDocuSignConfiguration);
    }

    @Test
    public void createDocuSignConfiguration() {
        DocuSignConfiguration docuSignConfiguration = new DocuSignConfiguration();
        docuSignConfiguration.setSubscriptionName("New subscription Name");
        docuSignConfiguration.setSubscriptionBeginDate("2017-01-01");
        docuSignConfiguration.setSubscriptionEndDate("2021-01-01");
        docuSignConfiguration.setSubscriptionStatus("A");
        docuSignConfiguration.setFileTransferMethod("EB2B");
        docuSignConfiguration.setFileTransferId("/devl/rptest1/eb2b");
        docuSignConfiguration.setFileTransferDirectory("/transport/in/PENSIONS/KEYNOTE_OUT");
        docuSignSubscriptionService.createDocuSignConfiguration(docuSignConfiguration);
    }

    @Test
    public void deleteDocuSignSubscription() {
        docuSignSubscriptionService.deleteDocuSignSubscription("6");
    }
}