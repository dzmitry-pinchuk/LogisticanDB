package by.pinchuk.db.runner;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class JaxBDataAdapter extends XmlAdapter<String, Date>{

	public static final DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
	
	@Override
	public Date unmarshal(String v) throws Exception {
		synchronized (dateFormat) {
			return dateFormat.parse(v);
		}
	}

	@Override
	public String marshal(Date v) throws Exception {
		synchronized (dateFormat) {
			return dateFormat.format(v);
		}
		
	}
	

}
