package org.willclark.jist.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.willclark.jist.models.Snippet;
import org.willclark.jist.models.User;
import org.willclark.jist.models.Snippet.Language;
import org.willclark.jist.utils.MongoUtil;

import com.mongodb.DBObject;

public class SnippetService {
	
	public static final String COLLECTION = "SNIPPET";
	
	public void create(Snippet snippet) {
		snippet.setCreatedOn(new Date());			
		MongoUtil.insert(COLLECTION, snippet);
	}
	
	public Snippet find(String _id) {
		DBObject each = MongoUtil.find(COLLECTION, new ObjectId(_id));
		if (each != null) {
			return new Snippet(each);
		}
		return null;
	}
		
	public List<Snippet> findAll() {
		List<Snippet> list = new ArrayList<Snippet>(0);		
		for(DBObject each : MongoUtil.findAll(COLLECTION)) list.add(new Snippet(each));
		return list;
	}
		
	public void deleteAll() {
		MongoUtil.deleteAll(COLLECTION);
	}
		
	public static void main(String... args) {
		MongoUtil.open();
		
		UserService userSvc = new UserService();
		userSvc.deleteAll();
		
		User user = new User.Builder().setUsername("admin").setEmail("admin@willclark.org").build();		
		userSvc.create(user);
		
		SnippetService snippetSvc = new SnippetService();
		snippetSvc.deleteAll();
		
		Snippet snippet = new Snippet.Builder(user).setLanguage(Language.JAVA).setCode("...").build();
		snippetSvc.create(snippet);
		
		System.out.println(snippetSvc.findAll());
		
		MongoUtil.close();
	}
	
}
