package org.corella.AccesoDatos.utilsAcceso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.sql.Date;

public class Dates {
	
	public Date getFechaSQL(String fecha) {

		return Date.valueOf(LocalDate.parse(fecha));

	}

}
