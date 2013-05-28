package org.willclark.jist.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.willclark.jist.models.User;
import org.willclark.jist.utils.MongoUtil;

import com.mongodb.DBObject;

public class UserService {
	
	public static final String COLLECTION = "USER";
	
	public void create(User user) {
		user.setCreatedOn(new Date());				
		MongoUtil.insert(COLLECTION, user);
	}
	
	public User find(ObjectId _id) {
		DBObject each = MongoUtil.find(COLLECTION, _id);
		
		if (each != null) {			
			return new User(each);
		}
		return null;
	}
		
	public List<User> findAll() {
		List<User> list = new ArrayList<User>(0);		
		for(DBObject each : MongoUtil.findAll(COLLECTION)) list.add(new User(each));
		return list;
	}
		
	public void deleteAll() {
		MongoUtil.deleteAll(COLLECTION);
	}
		
	public static void main(String... args) {
		MongoUtil.open();
		
		UserService svc = new UserService();
		svc.deleteAll();
		
		svc.create(new User.Builder().setUsername("admin").setEmail("admin@willclark.org").build());
		svc.create(new User.Builder().setUsername("will").setEmail("email@willclark.org").build());
		
		System.out.println(svc.findAll());
		
		MongoUtil.close();
	}
	
}
