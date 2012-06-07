package org.peimari.iofjavahelper;

import java.io.File;

import org.peimari.rgdomain.Competition;

import com.google.gson.GsonBuilder;

public class RouteGadgetReaderTest {
	
	public static void main(String[] args) throws Exception {
		readOrigFormatTest();
	}

	private static void readOrigFormatTest() throws Exception {
		String id = "11";
		Competition c = RouteGadgetReader.readCompetition(new File("/Users/Shared/routegadget/reitti/kartat"), id  );
		
		String json = new GsonBuilder().setPrettyPrinting().create().toJson(c);
		System.out.print(json);
	}

}
