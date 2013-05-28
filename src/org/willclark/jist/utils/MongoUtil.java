package org.willclark.jist.utils;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.willclark.jist.models.Model;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoException;

public class MongoUtil {

	public static final String DATABASE = "mydb";

	private static Mongo client;
	
	public static void open() {
		if (client != null) return;
				
		try {
			client = new Mongo("localhost", 27017);
		}
		catch (UnknownHostException ue) {			
			System.exit(0);
		}		
	}
	
	public static void close() {
		if (client != null) {
			client.close();
		}
	}
	
	public static void insert(String collection, Model model) {
		if (client == null) throw new MongoException("Mongo connection must be opened first");
		DBObject dbObj = model.convert();
		client.getDB(DATABASE).getCollection(collection).insert(dbObj);
		model.setId((ObjectId) dbObj.get("_id"));
	}
	
	public static DBObject find(String collection, ObjectId _id) {
		if (client == null) throw new MongoException("Mongo connection must be opened first");
		
		DBCursor cursor = client.getDB(DATABASE).getCollection(collection).find(new BasicDBObject("_id", _id));
				
		DBObject each = null;
		if (cursor.hasNext()) {
			each = cursor.next();
		}
		
		cursor.close();
		
		return each;
	}
	
	public static List<DBObject> findAll(String collection) {
		if (client == null) throw new MongoException("Mongo connection must be opened first");
		List<DBObject> list = new ArrayList<DBObject>(0);
		
		DBCursor cursor = client.getDB(DATABASE).getCollection(collection).find();
				
		DBObject each = null;
		while(cursor.hasNext()) {
			each = cursor.next();
			list.add(each);
		}
		
		cursor.close();
		
		return list;
	}
	
	public static void deleteAll(String collection) {
		if (client == null) throw new MongoException("Mongo connection must be opened first");
		client.getDB(DATABASE).getCollection(collection).drop();
	}
		
}
