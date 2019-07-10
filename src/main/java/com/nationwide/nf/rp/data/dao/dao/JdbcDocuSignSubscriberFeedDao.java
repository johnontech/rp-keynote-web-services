package com.nationwide.nf.rp.data.dao.dao;

import com.nationwide.nf.rp.data.dao.base.BaseDao;
import com.nationwide.nf.rp.entity.FeedEntity;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Data Access component for PPAK_DOCUSIGN_SUBSCR_FEED table.
 *
 * Modification Log:
 *
 * Date        Modifier             Description
 * ----------  -------------------  --------------------------------------------
 * 5/25/2017  J. Jorgensen         Initial version.
 */
@Repository
public class JdbcDocuSignSubscriberFeedDao extends BaseDao {

	  public List<FeedEntity> getFeedDetailsForSubscriber(Integer subscriberSeqId)
	  {
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
	    				 "ORDER BY SEQ_ID DESC" ;
	    	
	    	  List<FeedEntity> list = getJdbcTemplate().query(sql,
	                  new Object[] {subscriberSeqId}, new PpakDocusignSubscrFeedRowMapper());
	          return list == null ? new ArrayList<FeedEntity>() : list;
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

	public Set<Integer> getSubscriberSeqIdsForTransferingFiles()
	{
		String sql =  "SELECT SEQ_ID " +
		 " FROM  PPAK_DOCUSIGN_SUBSCR_FEED " +
		 " AND PDSFD.SUBSCR_STATUS = 'A' AND PDSFD.SUBSCR_END_DATE IS NULL ";
		Set<Integer> subscriberSeqIdSet = new HashSet<Integer>(); 
		
		List<Map<String, Object>> maps = getJdbcTemplate().queryForList(sql);
		for ( Map<String,Object> map : maps)
		{
			Collection<Object> values = map.values();
			BigDecimal subscriberSeqId =(BigDecimal) values.iterator().next();
			subscriberSeqIdSet.add(subscriberSeqId.intValue());
		}
		return subscriberSeqIdSet;
		
	}
}
