package utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeStampRandom_forFileName {
public TimeStampRandom_forFileName() {
	// TODO Auto-generated constructor stub
}

public static String getFileName() {
	// TODO Auto-generated method stub
	String fileName="";
	SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmssSSS");
	String timeStamp=sdf.format(new Date());
	fileName=timeStamp;
	return fileName;
}
}
