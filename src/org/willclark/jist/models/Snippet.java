package org.willclark.jist.models;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.willclark.jist.StringUtil;
import org.willclark.jist.services.UserService;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class Snippet extends Model {
	
	public enum Language {
		JAVA,C,PHP,PYTHON,RUBY,JAVASCRIPT,HTML;
	}
	
	private User user;
	private Language language;
	private String code;
	private long views;
	private List<User> favorites;
	
	public Snippet(User user) {
		this.user = user;
	}
	
	public Snippet(DBObject each) {
		super(each);
		UserService userSvc = new UserService();
				
		this.user = userSvc.find((ObjectId) each.get("user_id"));
		this.language = Language.valueOf(StringUtil.toString(each.get("language")));
		this.code = StringUtil.toString(each.get("code"));
		this.views = Long.parseLong(StringUtil.toString(each.get("views")));
		this.favorites = new ArrayList<User>(0);
		
		String favorites = StringUtil.toString(each.get("favorites"));
		if (StringUtil.isNotEmpty(favorites)) {
			for(String user_id : favorites.split(",")) {
				this.favorites.add(userSvc.find(new ObjectId(user_id)));
			}
		}				
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Snippet={");
		sb.append(super.modelToString());
		sb.append("user: ").append(user).append(", ");
		sb.append("language: ").append(language).append(", ");
		sb.append("code: ").append(code).append(", ");
		sb.append("views: ").append(views).append(", ");
		sb.append("favorites: ").append(favorites);
		sb.append("}");
		return sb.toString();
	}

	@Override
	public BasicDBObject convert() {
		BasicDBObject basicDBObject = super.modelConvert();
		basicDBObject.append("user_id", user._id);
		basicDBObject.append("language", language.name());
		basicDBObject.append("code", code);
		basicDBObject.append("views", views);
		
		if (favorites != null) {
			StringBuilder sb = new StringBuilder();
			for(User each : favorites) sb.append(each._id).append(",");			
			basicDBObject.append("favorites", sb.toString());
		}
		
		return basicDBObject;
	}

	public static class Builder {
		
		private Snippet snippet;
		
		public Builder(User user) {
			this.snippet = new Snippet(user);
		}
			
		public Builder setLanguage(Language language) {
			this.snippet.language = language;
			return this;
		}
		
		public Builder setCode(String code) {
			this.snippet.code = code;
			return this;
		}
		
		public Snippet build() {
			return snippet;
		}
		
	}
	
}
