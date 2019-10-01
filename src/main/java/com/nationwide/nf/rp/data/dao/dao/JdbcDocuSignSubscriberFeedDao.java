package com.nationwide.nf.rp.data.dao.dao;

import com.nationwide.nf.rp.bean.DocuSignConfiguration;
import com.nationwide.nf.rp.data.dao.base.BaseDao;
import com.nationwide.nf.rp.entity.FeedEntity;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.*;

/**
 * Data Access component for PPAK_DOCUSIGN_SUBSCR_FEED table.
 * <p>
 * Modification Log:
 * <p>
 * Date        Modifier             Description
 * ----------  -------------------  --------------------------------------------
 * 5/25/2017  J. Jorgensen         Initial version.
 */
@Repository
public class JdbcDocuSignSubscriberFeedDao extends BaseDao {

    private Logger log = Logger.getLogger(getClass().getName());

    public List<FeedEntity> getFeedDetailsForSubscriber(Integer subscriberSeqId) {
        String sql = "SELECT SEQ_ID, " +
                "SUBSCRIBER_NAME, " +
                "FILE_XFER_METHOD, " +
                "FILE_XFER_ID, " +
                "FILE_XFER_DIR, " +
                "SUBSCR_BEGIN_DATE, " +
                "SUBSCR_END_DATE, " +
                "SUBSCR_STATUS " +
                "FROM PPAK_DOCUSIGN_SUBSCR_FEED " +
                " WHERE SEQ_ID = ?  " +
                "ORDER BY SEQ_ID DESC";

        List<FeedEntity> list = getJdbcTemplate().query(sql,
                new Object[]{subscriberSeqId}, new PpakDocusignSubscrFeedRowMapper());
        return list == null ? new ArrayList<FeedEntity>() : list;
    }

    public List<FeedEntity> getAllFeedDetailsForSubscribers() {
        String sql = "SELECT SEQ_ID, " +
                "SUBSCRIBER_NAME, " +
                "FILE_XFER_METHOD, " +
                "FILE_XFER_ID, " +
                "FILE_XFER_DIR, " +
                "SUBSCR_BEGIN_DATE, " +
                "SUBSCR_END_DATE, " +
                "SUBSCR_STATUS " +
                "FROM PPAK_DOCUSIGN_SUBSCR_FEED " +
                "ORDER BY SUBSCRIBER_NAME";

        List<FeedEntity> list = getJdbcTemplate().query(sql,
                new Object[]{}, new PpakDocusignSubscrFeedRowMapper());
        return list == null ? new ArrayList<FeedEntity>() : list;
    }

    public int updateDocuSignConfiguration(DocuSignConfiguration docuSignConfiguration) {

        String sql = "UPDATE PPAK_DOCUSIGN_SUBSCR_FEED " +
                "SET SUBSCRIBER_NAME = ?, " +
                "FILE_XFER_METHOD = ?, " +
                "FILE_XFER_ID = ?, " +
                "FILE_XFER_DIR = ?, " +
                "SUBSCR_BEGIN_DATE = ?, " +
                "SUBSCR_END_DATE = ?, " +
                "SUBSCR_STATUS = ? " +
                "WHERE SEQ_ID = ? ";

        log.info("Before Update: " + sql + docuSignConfiguration);
        int numberOfRowsUpdated = getJdbcTemplate().update(sql,
                docuSignConfiguration.getSubscriptionName(),
                docuSignConfiguration.getFileTransferMethod(),
                docuSignConfiguration.getFileTransferId(),
                docuSignConfiguration.getFileTransferDirectory(),
                docuSignConfiguration.getSubscriptionBeginDate(),
                docuSignConfiguration.getSubscriptionEndDate(),
                docuSignConfiguration.getSubscriptionStatus(),
                docuSignConfiguration.getSeqId());
        return numberOfRowsUpdated;
    }

    public DocuSignConfiguration create(DocuSignConfiguration docuSignConfiguration) {
        int numberOfDocuSignConfigurations = getNextDocuSignConfigurationSeqId();
        System.out.println(numberOfDocuSignConfigurations);
        docuSignConfiguration.setSeqId(String.valueOf(numberOfDocuSignConfigurations));

        String sql = "INSERT INTO PPAK_DOCUSIGN_SUBSCR_FEED " +
                "(SEQ_ID,SUBSCRIBER_NAME,FILE_XFER_METHOD,FILE_XFER_ID,FILE_XFER_DIR," +
                "SUBSCR_BEGIN_DATE,SUBSCR_END_DATE,SUBSCR_STATUS) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        log.info("Before Create: " + sql + docuSignConfiguration);
        int rowCount = getJdbcTemplate().update(sql,
                docuSignConfiguration.getSeqId(),
                docuSignConfiguration.getSubscriptionName(),
                docuSignConfiguration.getFileTransferMethod(),
                docuSignConfiguration.getFileTransferId(),
                docuSignConfiguration.getFileTransferDirectory(),
                docuSignConfiguration.getSubscriptionBeginDate(),
                docuSignConfiguration.getSubscriptionEndDate(),
                docuSignConfiguration.getSubscriptionStatus());

        System.out.println("Number of docusign rows inserted: " + rowCount);
        return docuSignConfiguration;
    }

    private int getNextDocuSignConfigurationSeqId() {
        return getJdbcTemplate().queryForObject("SELECT MAX(SEQ_ID) + 1 FROM PPAK_DOCUSIGN_SUBSCR_FEED",
                new Object[]{}, Integer.class);
    }

    public void deleteDocuSignConfiguration(String feedSeqId) {
        log.info("Before Delete DocuSign Configuration with Id: " + feedSeqId);
        String sql = "DELETE FROM PPAK_DOCUSIGN_SUBSCR_FEED WHERE SEQ_ID = ?";
        Object[] params = {Integer.parseInt(feedSeqId)};
        int[] types = {Types.INTEGER};

        int rowCount = getJdbcTemplate().update(sql, params, types);
        log.info("Number of DocuSign Configurations deleted: '" + rowCount + "'");
    }

    private class PpakDocusignSubscrFeedRowMapper implements org.springframework.jdbc.core.RowMapper<FeedEntity> {

        public FeedEntity mapRow(ResultSet resultSet, int i) throws SQLException {
            FeedEntity entity = new FeedEntity();
            entity.setSeqId(resultSet.getInt("SEQ_ID"));
            entity.setSubscriberName(resultSet.getString("SUBSCRIBER_NAME"));
            entity.setFileXferMethod(resultSet.getString("FILE_XFER_METHOD"));
            entity.setFileXferId(resultSet.getString("FILE_XFER_ID"));
            entity.setFileXferDir(resultSet.getString("FILE_XFER_DIR"));
            entity.setSubscrBeginDate(resultSet.getDate("SUBSCR_BEGIN_DATE"));
            entity.setSubscrEndDate(resultSet.getDate("SUBSCR_END_DATE"));
            entity.setSubscrStatus(resultSet.getString("SUBSCR_STATUS"));
            return entity;
        }
    }
}
