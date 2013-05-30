package org.willclark.jist.services;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.willclark.jist.models.User;
import org.willclark.jist.utils.ServiceUtil;

import com.google.code.morphia.Datastore;

public class UserService {
	
	private Datastore ds;
	
	public UserService(Datastore ds) {
		this.ds = ds;
	}
	
	public void create(User user) {
		user.setCreatedOn(new Date());
		ds.save(user);
	}
	
	public User find(String _id) {
		return ds.find(User.class, "_id", new ObjectId(_id)).get();
	}
		
	public List<User> findAll() {
		return ds.find(User.class).asList();
	}
		
	public void deleteAll() {
		ds.delete(ds.createQuery(User.class));
	}
		
	public static void main(String... args) {
		Datastore ds = ServiceUtil.getDatastore();
		
		UserService svc = new UserService(ds);
		svc.deleteAll();
		
		svc.create(new User.Builder().setUsername("admin").setEmail("admin@willclark.org").build());
		svc.create(new User.Builder().setUsername("will").setEmail("email@willclark.org").build());
		
		System.out.println(svc.findAll());		
	}
	
}
