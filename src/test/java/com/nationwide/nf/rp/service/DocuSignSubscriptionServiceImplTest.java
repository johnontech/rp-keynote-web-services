package com.nationwide.nf.rp.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import com.nationwide.nf.rp.data.dao.dao.JdbcDocuSignSubscriberFeedDao;

import static org.junit.Assert.*;

@ContextConfiguration(locations = "classpath:rest-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class DocuSignSubscriptionServiceImplTest {

    @Autowired
    private JdbcDocuSignSubscriberFeedDao jdbcDocuSignSubscriberFeedDao;

    @Autowired
    DocuSignSubscriptionService docuSignSubscriptionService;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getDocuSignSubscription() {
        docuSignSubscriptionService.getDocuSignSubscription("1");
    }

    @Test
    public void getAllDocuSignSubscriptions() {
    }

    @Test
    public void updateDocuSignConfiguration() {
    }

    @Test
    public void createDocuSignConfiguration() {
    }

    @Test
    public void deleteDocuSignSubscription() {
    }
}