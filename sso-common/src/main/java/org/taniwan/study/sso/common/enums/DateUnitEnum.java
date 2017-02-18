package org.taniwan.study.sso.common.enums;

import java.util.Calendar;

public enum DateUnitEnum {

	SECOND(Calendar.SECOND), MINUTE(Calendar.MINUTE), HOUR(Calendar.HOUR),
    DAY(Calendar.DAY_OF_YEAR), WEEK(Calendar.WEEK_OF_YEAR), MONTH(Calendar.MONTH),
	YEAR(Calendar.YEAR);

	private int unit;

	DateUnitEnum(int unit) {
		this.unit = unit;
	}

	public int getUnit() {
		return unit;
	}

}
