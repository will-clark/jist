package org.willclark.jist.utils;

import java.net.UnknownHostException;
import com.google.code.morphia.Datastore;
import com.google.code.morphia.Morphia;
import com.mongodb.Mongo;

public class ServiceUtil {

	private static boolean open;	
	private static Mongo mongo;
	private static Morphia morphia;
	
	public static Datastore getDatastore() {
		open();
		return morphia.createDatastore(mongo, "mydb");
	}
	
	private synchronized static void open() {
		if (open) return;
		
		try {
			mongo = new Mongo("localhost", 27017);			
			morphia = new Morphia();
			morphia.mapPackage("org.willclark.jist.models");
		}
		catch (UnknownHostException ue) {			
			System.exit(0);
		}
		
		open = true;
	}
	
}
