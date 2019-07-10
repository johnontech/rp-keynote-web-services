package com.nationwide.nf.rp.data.dao.dao;

import com.nationwide.nf.rp.data.dao.base.BaseDao;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by jorgej2 on 6/5/2017.
 */
@Repository
public class JdbcCasesDao extends BaseDao {

    private Logger log = Logger.getLogger(getClass().getName());

    public static final String GET_CASE_SEQ_ID_FOR_PPA_NBR = "SELECT seq_id " +
                                                               "FROM ppak_cases " +
                                                              "WHERE ppa_nbr = ? AND nbr = ?";

    public Set<Integer> getCaseSeqIdForPpaNbrCaseNumber(String ppaNbrAsString, String nbrAsString) {
        Set<Integer> seqIdSet = new HashSet<Integer>();
        log.info("Executing SQL: '" + GET_CASE_SEQ_ID_FOR_PPA_NBR + "', with ppa_nbr '" + ppaNbrAsString + "', and nbr '" + nbrAsString + "'");

        List<Map<String, Object>> maps = getJdbcTemplate().queryForList(GET_CASE_SEQ_ID_FOR_PPA_NBR, ppaNbrAsString, nbrAsString);
        for (Map<String, Object> map : maps) {
            Collection<Object> values = map.values();
            BigDecimal seqId = (BigDecimal) values.iterator().next();
            seqIdSet.add(seqId.intValue());
        }
        return seqIdSet;
    }
}
