package com.nationwide.nf.rp.util;

import com.nationwide.nf.rp.data.dao.base.BaseDao;
import org.springframework.stereotype.Component;

/**
 * This utility class returns Daily flow date.
 *
 * Modification Log:
 *
 * Date        Modifier             Description
 * ----------  ----------------  ----------------
 * 5/25/2017  J. Jorgensen       Initial version.
 */
@Component
public class DateUtil extends BaseDao {

	public String getFlowDate() {
		String sql = "SELECT TO_CHAR(daily_cycle_to_date, 'RRRR_MMDD') "+
		             " FROM ppak_date_cards";
		String currentDate = getJdbcTemplate().queryForObject(sql, String.class);	
		return currentDate;
	}

	public String getSysdate() {
		String sql = "SELECT TO_CHAR(sysdate, 'RRRR_MMDD') "+
				" FROM dual";
		String sysdate = getJdbcTemplate().queryForObject(sql, String.class);
		return sysdate;
	}
}
