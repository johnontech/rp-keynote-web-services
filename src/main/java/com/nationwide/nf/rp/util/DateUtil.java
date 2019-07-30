package com.nationwide.nf.rp.util;

import com.nationwide.nf.rp.data.dao.base.BaseDao;
import com.nationwide.nf.rp.exception.SubscriberFileCreateException;
import com.nationwide.nf.rp.exception.SubscriptionConfigurationException;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

	public String getDateAsString(Date date) {
		DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
		return  dateFormat.format(date);
	}

	public String reformatDate(String dateFormat, String dateValue) {
		Date date = null;
		SimpleDateFormat format = new SimpleDateFormat(dateFormat);
		try {
			date       = format.parse ( dateValue);
		} catch (ParseException e) {
			throw new SubscriptionConfigurationException("An error occurred when reformatting date using date format '" +
					dateFormat + "', with date value '" + dateValue + "'.");
		}
		return getDateAsString(date);

	}
}
