package org.willclark.jist.services;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.willclark.jist.models.Snippet;
import org.willclark.jist.models.User;
import org.willclark.jist.models.Snippet.Language;
import org.willclark.jist.utils.ServiceUtil;

import com.google.code.morphia.Datastore;

public class SnippetService {
		
	private Datastore ds;
	
	public SnippetService(Datastore ds) {
		this.ds = ds;
	}
	
	public void create(Snippet snippet) {
		snippet.setCreatedOn(new Date());
		ds.save(snippet);
	}
	
	public Snippet find(String _id) {
		return ds.find(Snippet.class, "_id", new ObjectId(_id)).get();
	}
		
	public List<Snippet> findAll() {
		return ds.find(Snippet.class).asList();
	}
		
	public void deleteAll() {
		ds.delete(ds.createQuery(Snippet.class));
	}
		
	public static void main(String... args) {
		Datastore ds = ServiceUtil.getDatastore();
		
		UserService userSvc = new UserService(ds);
		userSvc.deleteAll();
		
		User user = new User.Builder().setUsername("admin").setEmail("admin@willclark.org").build();		
		userSvc.create(user);
		
		SnippetService snippetSvc = new SnippetService(ds);
		snippetSvc.deleteAll();
		
		Snippet snippet = new Snippet.Builder(user).setLanguage(Language.JAVA).setCode("...").build();
		snippetSvc.create(snippet);
		
		System.out.println(snippetSvc.findAll());
	}
	
}
