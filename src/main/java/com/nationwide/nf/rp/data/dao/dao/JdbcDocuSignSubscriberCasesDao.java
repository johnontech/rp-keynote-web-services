package com.nationwide.nf.rp.data.dao.dao;

import com.nationwide.nf.rp.data.dao.base.BaseDao;
import com.nationwide.nf.rp.exception.DatabaseException;
import com.nationwide.nf.rp.util.FileTypeEnum;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;
import java.util.*;

/**
 * Data Access component for PPAK_DOCUSIGN_SUBSCR_CASES table.
 *
 * Modification Log:
 *
 * Date        Modifier             Description
 * ----------  -------------------  --------------------------------------------
 * 5/25/2017  J. Jorgensen         Initial version.
 */
@Repository
public class JdbcDocuSignSubscriberCasesDao extends BaseDao {

    private static final String DELETE_PPAK_DOCUSIGN_SUBSCR_CASES = "DELETE FROM PPAK_DOCUSIGN_SUBSCR_CASES";

    private static final String INSERT_PPAK_DOCUSIGN_SUBSCR_CASES =
            "INSERT INTO PPAK_DOCUSIGN_SUBSCR_CASES (DOCUSIGN_SUBSCR_FEED_SEQ_ID, CASE_SEQ_ID) " +
                    "VALUES(?, ?)";

    public void saveParticipant(int subscriberFeedSeqId, int caseSeqId, String ssn, String envelopeId, String flowDate, String status) {
        getJdbcTemplate().update(INSERT_PPAK_DOCUSIGN_SUBSCR_CASES, subscriberFeedSeqId, caseSeqId);
    }

    public Set<Integer> getSubscriberSeqIdForCaseSeqId(Integer caseSeqId) {
        Set<Integer> seqIdSet = new HashSet<Integer>();

        String sql = "SELECT CASES.DOCUSIGN_SUBSCR_FEED_SEQ_ID " +
                       "FROM PPAK_DOCUSIGN_SUBSCR_CASES CASES " +
                       "JOIN PPAK_DOCUSIGN_SUBSCR_FEED FEED ON FEED.SEQ_ID = CASES.DOCUSIGN_SUBSCR_FEED_SEQ_ID " +
                       "JOIN PPAK_DOCUSIGN_SUBSCR_FILES FILES ON FILES.DOCUSIGN_SUBSCR_FEED_SEQ_ID = CASES.DOCUSIGN_SUBSCR_FEED_SEQ_ID " +
                      "WHERE FEED.SUBSCR_STATUS = 'A' " +
                        "AND CASE_SEQ_ID = ? " +
                        "AND FILES.FILE_TYPE IN ('" + FileTypeEnum.CENSUS + "','" + FileTypeEnum.SALARY_REDUCTION.toString() + "','" +
                                                      FileTypeEnum.ONLINE_ENROLLMENT + "','" + FileTypeEnum.ENROLLMENT_PDF + "')";

        List<Map<String, Object>> maps = getJdbcTemplate().queryForList(sql, caseSeqId);
        for (Map<String, Object> map : maps) {
            Collection<Object> values = map.values();
            BigDecimal seqId = (BigDecimal) values.iterator().next();
            seqIdSet.add(seqId.intValue());
        }
        return seqIdSet;
    }

    public String getClientUniqueId(int subscriberFeedSeqId, int caseSeqId) {
        Set<String> clientUniqueIdSet = new HashSet<String>();

        String sql = "SELECT client_unique_id " +
                     "FROM ppak_docusign_subscr_cases " +
                     "WHERE docusign_subscr_feed_seq_id = ? " +
                     "AND case_seq_id = ?";

        List<Map<String, Object>> maps = getJdbcTemplate().queryForList(sql, subscriberFeedSeqId, caseSeqId);
        for (Map<String, Object> map : maps) {
            Collection<Object> values = map.values();
            String clientUniqueId = (String)values.iterator().next();
            if (clientUniqueId == null) {
                clientUniqueIdSet.add("");
            } else {
                clientUniqueIdSet.add(clientUniqueId);
            }
        }
        if (clientUniqueIdSet.size() > 1) {
            throw new DatabaseException("More than one row for given subscriberFeedSeqId '" + subscriberFeedSeqId +
                                        "'and caseSeqId '" + caseSeqId + "' found in ppak_docusign_subscr_cases");
        }
        return clientUniqueIdSet.iterator().next();
    }
}

