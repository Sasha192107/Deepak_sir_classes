package org.autocrm.genericutils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * 
 * @author Sasha
 *
 */

public class PropLib 
{
	/**
	 * The method is used to get data from property file based on the key passed
	 * @param key
	 * @return
	 * @throws IOException
	 */
	public String getPropertyKeyValue(String key) throws IOException
	{
		FileInputStream fis = new FileInputStream("./TestData/CommonData.properties");
		Properties pobj = new Properties();
		pobj.load(fis);
		String value= pobj.getProperty(key);
		return value;
	}

}
