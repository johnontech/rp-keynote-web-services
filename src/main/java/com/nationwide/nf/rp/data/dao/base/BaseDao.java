package com.nationwide.nf.rp.data.dao.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class initializes data base connectivity configuration.
 *
 * Modification Log:
 *
 * Date        Modifier             Description
 * ----------  -------------------  --------------------------------------------
 * 1/24/2016  J. Jorgensen         Initial version.
 */
public class BaseDao extends JdbcDaoSupport {

    @Autowired
    private DataSource dataSource;

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
    }

    protected static String getFlowDateAsString(Date flowDate) {
        return  (new SimpleDateFormat("dd-MMM-yyyy").format(flowDate)).toUpperCase();
    }
}
