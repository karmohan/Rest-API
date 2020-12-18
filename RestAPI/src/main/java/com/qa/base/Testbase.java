package com.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Testbase {
 
  public Properties prop;
  FileInputStream file;
  public int RESPONSE_CODE_200 = 200;
  public int RESPONSE_CODE_201 = 201;
  public int RESPONSE_CODE_500 = 500;
  public int RESPONSE_CODE_400 = 400;
  public int RESPONSE_CODE_401 = 401;
  
  
  public Testbase() {
	  
	  try {
		  prop= new Properties();
		  file = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/com/qa/config/config.properties");
	     prop.load(file);
	  } catch (FileNotFoundException e) {
		  e.printStackTrace();
	  } catch (IOException e) {
		  e.printStackTrace();
	  }
  }

}
