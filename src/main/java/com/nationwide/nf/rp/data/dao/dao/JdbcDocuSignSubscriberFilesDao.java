package com.nationwide.nf.rp.data.dao.dao;

import com.nationwide.nf.rp.data.dao.base.BaseDao;
import com.nationwide.nf.rp.entity.BatchEntity;
import com.nationwide.nf.rp.entity.FileEntity;
import com.nationwide.nf.rp.exception.SalaryReductionFileCreateException;
import com.nationwide.nf.rp.exception.SubscriberFileCreateException;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Data Access component for PPAK_DOCUSIGN_SUBSCR_FILES table.
 *
 * Modification Log:
 *
 * Date        Modifier             Description
 * ----------  -------------------  --------------------------------------------
 * 5/25/2017  J. Jorgensen         Initial version.
 */
@Repository
public class JdbcDocuSignSubscriberFilesDao extends BaseDao {

    private static final String CREATE_FILE_WHEN_EMPTY =
            "SELECT CREATE_FILE_WHEN_EMPTY FROM PPAK_DOCUSIGN_SUBSCR_FILES WHERE DOCUSIGN_SUBSCR_FEED_SEQ_ID = ?";

    private static final String IS_ELIGIBLE_SUBSCRIBER =
            "SELECT COUNT (1)" +
                    "  FROM PPAK_DOCUSIGN_SUBSCR_FEED , PPAK_DATE_CARDS_KN " +
                    " WHERE  DAILY_CYCLE_FROM_DATE >= SUBSCR_BEGIN_DATE " +
                    "       AND SUBSCR_STATUS = 'A' " +
                    "       AND SUBSCR_END_DATE IS NOT NULL AND SEQ_ID = ?";

    private Logger log = Logger.getLogger(getClass().getName());

    public static final String PARTICIPANT_BELONGS_TO_SUBSCRIBER_OPTED_IN_FOR_FILE_SQL =
            "SELECT count(1) " +
              "FROM ppak_docusign_subscr_batch batch " +
              "JOIN ppak_docusign_subscr_cases cases ON cases.case_seq_id = batch.case_seq_id " +
              "JOIN ppak_docusign_subscr_files files ON files.docusign_subscr_feed_seq_id = cases.docusign_subscr_feed_seq_id " +
              "JOIN ppak_docusign_subscr_feed  feed  ON feed.seq_id = cases.docusign_subscr_feed_seq_id " +
             "WHERE files.file_type = ? " +
               "AND feed.subscr_status = 'A' " +
               "AND feed.subscr_end_date IS NULL " +
               "AND batch.status = 'P' " +
               "AND batch.flow_date = TO_DATE( ? ,'DD-MON-YYYY')  " +
               "AND files.docusign_subscr_feed_seq_id  = ? " +
               "AND batch.envelope_id = ? ";

    public String getCensusFilePrefix(String envelopeID) {

        String sql = " SELECT distinct pdsf.file_name_prefix  FileNamePrefix " +
                     "   FROM ppak_docusign_subscr_batch pdsb " +
                     "        JOIN    " +
                     "        ppak_docusign_subscr_files pdsf   " +
                     "           ON pdsb.docusign_subscr_feed_seq_id =  " +
                     "                 pdsf.docusign_subscr_feed_seq_id " +
                     "  WHERE pdsb.envelope_id = ? ";

        String FileNamePrefix = (String) getJdbcTemplate().queryForObject(
                sql, new Object[]{envelopeID}, String.class);

        return FileNamePrefix;
    }

    public String getParticipantSSN(String envelopeID) {
        String sql = "select ssn from ppak_docusign_subscr_batch where envelope_id = ?";
        String FileNamePrefix = (String) getJdbcTemplate().queryForObject(
                sql, new Object[]{envelopeID}, String.class);

        return FileNamePrefix;
    }

  public Set<Integer> getSubscriberSeqIdsofRequestedSubscribers() {
        Set<Integer> subscriberIdSet = new HashSet<Integer>();

         String sql =  
        	 
        	 "SELECT DISTINCT(PSB.DOCUSIGN_SUBSCR_FEED_SEQ_ID) FROM " +
        	 "PPAK_DOCUSIGN_SUBSCR_BATCH PSB, PPAK_DOCUSIGN_SUBSCR_FEED PSFE" +
        	 " ,PPAK_DOCUSIGN_SUBSCR_FILE_TFER PSFT " +
        	"WHERE PSFE.SEQ_ID = PSB.DOCUSIGN_SUBSCR_FEED_SEQ_ID " +
        	"AND PSFE.SEQ_ID = PSFT.DOCUSIGN_SUBSCR_FEED_SEQ_ID " +
        	 " 	AND  PSB.STATUS = 'C' " + 
        	"AND PSFE.SUBSCR_END_DATE IS NULL " +  
        	"AND PSFE.SUBSCR_STATUS = 'A' " +
        	" AND PSFT.FLOW_DATE =   (SELECT DAILY_CYCLE_TO_DATE FROM PPAK_DATE_CARDS_KN) " +
        	"AND PSFT.STATUS='P' "; 
        List<Map<String, Object>> maps = getJdbcTemplate().queryForList(sql);
        for (Map<String, Object> map : maps) {
            Collection<Object> values = map.values();
            BigDecimal subscriberId =   (BigDecimal) values.iterator().next();
            subscriberIdSet.add(subscriberId.intValue());
        }
        return subscriberIdSet;
    }
    public Set<String> getEnvelopeIdsOfSubscriber(Integer subscriberId)
    {
    	Set<String> envelopedIdSet = new HashSet<String>();
    	
    	String sql =  "SELECT ENVELOPE_ID " +
    				"FROM PPAK_DOCUSIGN_SUBSCR_BATCH  " +
    				"WHERE   DOCUSIGN_SUBSCR_FEED_SEQ_ID  = ? AND FLOW_DATE = " +
        	        "       (SELECT DAILY_CYCLE_TO_DATE FROM PPAK_DATE_CARDS_KN) AND STATUS ='C'";
    	
    	List<Map<String,Object>> maps = getJdbcTemplate().queryForList(sql,subscriberId);
    	for (Map<String,Object> map : maps) {
    		Collection<Object> values = map.values();
    		String envelopeId = (String) values.iterator().next();
    		envelopedIdSet.add(envelopeId);
    	}
    	return envelopedIdSet;
    }

    public static final String GET_SUBSCRIBER_ENTITY_SQL =
            "SELECT distinct feed.seq_id, " +
                    "files.docusign_subscr_feed_seq_id, " +
                    "files.file_type, " +
                    "files.file_name_prefix, " +
                    "files.file_extension, " +
                    "files.create_file_when_empty, " +
                    "files.file_begin_date, " +
                    "files.file_end_date," +
                    "internal_email_notif_addr " +
               "FROM ppak_docusign_subscr_batch batch " +
               "JOIN ppak_docusign_subscr_feed  feed  ON feed.seq_id = batch.docusign_subscr_feed_seq_id " +
               "JOIN ppak_docusign_subscr_files files ON files.docusign_subscr_feed_seq_id = feed.seq_id " +
               "JOIN ppak_docusign_subscr_cases cases ON cases.docusign_subscr_feed_seq_id = feed.seq_id " +
              "WHERE files.file_type = ? " +
                    "AND feed.subscr_status = 'A' " +
                    "AND batch.status = 'P' " +
                    "AND batch.flow_date = TO_DATE(?,'DD-MON-YYYY') " +
                    "AND files.docusign_subscr_feed_seq_id  = ? " +
                    "AND batch.envelope_id = ?";

    public FileEntity getSubscriberFileEntity(String fileType, Integer subscriberFeedSeqId, String envelopeId, Date flowDate) {
        log.info(GET_SUBSCRIBER_ENTITY_SQL + "' with File Type '" + fileType +
                "', Flow Date '" + flowDate + "', Subscriber Seq Id '" + subscriberFeedSeqId + "', and Envelope Id '" + envelopeId + "'");

        String flowDateAsString = (new SimpleDateFormat("dd-MMM-yyyy").format(flowDate)).toUpperCase();
        Object[] params = {fileType, flowDateAsString, subscriberFeedSeqId, envelopeId};
        int[] types = {Types.VARCHAR, Types.VARCHAR, Types.INTEGER, Types.VARCHAR};

        List<FileEntity> subscriberFileEntities = getJdbcTemplate().query(GET_SUBSCRIBER_ENTITY_SQL,
                params, types, new SubscriberFilesRowMapper());

        if (subscriberFileEntities.size() > 1) {
            throw new SubscriberFileCreateException("More than one Subscriber File Entry found. Expected one row returned, for File Type '" + fileType +
                    "', Flow Date '" + flowDate + "', Subscriber Seq Id" + subscriberFeedSeqId + "', and Envelope Id '" + envelopeId + "'");
        } else if (subscriberFileEntities.size() == 1){
            log.info("The following subscriber file entries were found for File Type '" + fileType +
                    "' for Envelope Id '" + envelopeId + "' " + "and Flow Date '" + flowDate);
            for (FileEntity subscriberFileEntity : subscriberFileEntities) {
                log.info(subscriberFileEntity);
            }
            return subscriberFileEntities.get(0);
        } else {
            return null;
        }
    }

    private static final String GET_SUBSCRIBER_FILE_ENTITY =
            "SELECT docusign_subscr_feed_seq_id, " +
                    "file_type, " +
                    "file_name_prefix, " +
                    "file_extension, " +
                    "create_file_when_empty, " +
                    "file_begin_date, " +
                    "file_end_date," +
                    "internal_email_notif_addr " +
               "FROM ppak_docusign_subscr_files " +
              "WHERE docusign_subscr_feed_seq_id = ? and file_type = ?";

    public FileEntity getSubscriberFileEntity(int subscriberFeedSeqId, String fileType) {
        log.info(GET_SUBSCRIBER_FILE_ENTITY + "' with Subscriber Feed Seq Id '" + subscriberFeedSeqId +
                "', Flow Date '" + fileType + "'");

        Object[] params = {subscriberFeedSeqId, fileType};
        int[] types = {Types.INTEGER, Types.VARCHAR};

        List<FileEntity> subscriberFileEntities = getJdbcTemplate().query(GET_SUBSCRIBER_FILE_ENTITY,
                params, types, new SubscriberFilesRowMapper());

        if (subscriberFileEntities.size() > 1) {
            throw new SubscriberFileCreateException("More than one Subscriber File Entry found for Subscriber Feed Seq Id '" +
                    subscriberFeedSeqId + "' and File Type '"+ fileType + "'");
        } else if (subscriberFileEntities.size() == 1){
            log.info("The following Subscriber File Entry found for Subscriber Feed Seq Id '" + fileType +
                    "' and File Type '"+ fileType + "'");
            return subscriberFileEntities.get(0);
        } else {
            return null;
        }
    }

    private static final String GET_SUBSCRIPTION_FILES_FOR_NOTIFICATIONS =
            "SELECT docusign_subscr_feed_seq_id, file_type, file_name_prefix, file_extension, " +
                    "create_file_when_empty, file_begin_date, file_end_date, internal_email_notif_addr " +
                    "FROM ppak_docusign_subscr_files " +
                    "WHERE UPPER(file_type) != 'SPARK' " +
                    "AND UPPER(file_extension) != 'PDF'" ;

    public List<FileEntity> getSubscriptionFilesAndEmailsToSend() {
        Object[] params = {};
        int[] types = {};
        log.info(GET_SUBSCRIPTION_FILES_FOR_NOTIFICATIONS);
        List<FileEntity> subscriberFileEntities = getJdbcTemplate().query(GET_SUBSCRIPTION_FILES_FOR_NOTIFICATIONS,
                params, types, new SubscriberFilesRowMapper());
        return subscriberFileEntities;
    }

    private class SubscriberFilesRowMapper implements org.springframework.jdbc.core.RowMapper<FileEntity> {

        public FileEntity mapRow(ResultSet resultSet, int i) throws SQLException {
            FileEntity entity = new FileEntity();
            entity.setSubscriberFeedSeqId(resultSet.getInt("docusign_subscr_feed_seq_id"));
            entity.setFileType(resultSet.getString("file_type").trim());
            entity.setFileNamePrefix(resultSet.getString("file_name_prefix").trim());
            entity.setFileExtension(resultSet.getString("file_extension").trim());
            entity.setCreateFileWhenEmpty(resultSet.getString("create_file_when_empty").trim());
            entity.setFile_begin_date(resultSet.getDate("file_begin_date"));
            entity.setFile_end_date(resultSet.getDate("file_end_date"));

            String internalEmailNotificationAddress = resultSet.getString("internal_email_notif_addr");
            if (internalEmailNotificationAddress != null) {
                entity.setInternalEmailNotifAddr(resultSet.getString("internal_email_notif_addr").trim());
            }
            return entity;
        }
    }

    public List<FileEntity> getSubscriberEntries(String fileType) {
        String sql = "SELECT docusign_subscr_feed_seq_id, file_type, file_name_prefix, file_extension, " +
                "create_file_when_empty, file_begin_date, file_end_date, internal_email_notif_addr " +
                "FROM PPAK_DOCUSIGN_SUBSCR_FILES FILES " +
                "JOIN PPAK_DOCUSIGN_SUBSCR_FEED FEED ON FEED.SEQ_ID = FILES.DOCUSIGN_SUBSCR_FEED_SEQ_ID " +
                "WHERE FILE_TYPE = ? " +
                "AND FEED.SUBSCR_STATUS = 'A'";

        Object[] params = {fileType};
        int[] types = {Types.VARCHAR};

        List<FileEntity> list = getJdbcTemplate().query(sql, params, types, new SubscriberFilesRowMapper());
        return list == null ? new ArrayList<FileEntity>() : list;
    }


}
