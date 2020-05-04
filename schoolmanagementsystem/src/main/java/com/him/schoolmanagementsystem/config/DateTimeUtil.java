package com.him.schoolmanagementsystem.config;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUtil {
	
	public static final String TIME_ZONE_CENTRAL="America/Chicago";
	public static final String DATE_TIME_DEFAULT="MM-dd-yyyy HH:mm:ss";
	public static final String DATE_DEFAULT="MM-dd-yyyy";
	
	public enum FORMAT
	{
		DEFAULT_DATE_TIME(DATE_TIME_DEFAULT,"MM-DD-YYYY HH24:MI:SS"),
		DEFAULT_DATE(DATE_DEFAULT,"MM-DD-YYYY"),
		SLASH_SEPERATED_DATE_YYYYMMDD("yyyy/MM/dd","YYYY/MM/DD");
		private String format=null;
		private String sqlFormat=null;
		
		FORMAT(String format,String sqlFormat){
			this.format=format;
			this.sqlFormat=sqlFormat;
		}

		public String getFormat() {
			return format;
		}
		
		public String getSqlFormat() {
			return sqlFormat;
		}
	 
	}
  public static Date getDate(DateTimeUtil.FORMAT format,String dateTime) throws Exception
  {
	  SimpleDateFormat simpleDef=new SimpleDateFormat(format.getFormat());
	  simpleDef.setLenient(false);
	  return simpleDef.parse(dateTime);
  }
  public static Timestamp currentTimeStamp()
  {
	  return new Timestamp(new Date().getTime());
  }
}
