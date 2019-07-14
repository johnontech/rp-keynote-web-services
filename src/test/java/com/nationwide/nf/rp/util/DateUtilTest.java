package com.nationwide.nf.rp.util;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotNull;

import java.util.Date;

@ContextConfiguration(locations = "classpath:rest-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class DateUtilTest {

    @Autowired
    private DateUtil dateUtil;

    private Logger log = Logger.getLogger(getClass().getName());

    @Test
    public void getDateAsString() {
        Date date = new Date();
        String dateAsString = dateUtil.getDateAsString(date);
        assertNotNull("Should return date on string format", dateAsString);
        log.info("dateAsString: " + dateAsString);
    }
}
