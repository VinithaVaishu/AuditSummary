package com.apll.cdcsummary.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;


public class AppUtils {
	public static String afterLsn;
	
	public static void writingGivenStringToFile(String string) 
			throws IOException {
			    
			BufferedWriter writer = new BufferedWriter(new FileWriter("log_sequence_number.txt"));
			 writer.write(string);
			 writer.close();
			
			}
	
	public static String readStringFromFile() 
			  throws IOException {
		   
		BufferedReader br= new BufferedReader(new FileReader("log_sequence_number.txt"));
			   afterLsn=br.readLine();
			  // System.out.println(afterLsn);
			   br.close();
			    return afterLsn;
			}
}
