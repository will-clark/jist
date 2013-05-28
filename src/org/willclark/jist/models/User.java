package org.willclark.jist.models;

import org.willclark.jist.StringUtil;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class User extends Model {
	
	private String username;
	private String email;
	
	public User() {}
	
	public User(DBObject each) {
		super(each);
		this.username = StringUtil.toString(each.get("username"));
		this.email = StringUtil.toString(each.get("email"));
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("User={");
		sb.append(super.modelToString());
		sb.append("username: ").append(username).append(", ");
		sb.append("email: ").append(email);
		sb.append("}");
		return sb.toString();
	}
		
	public BasicDBObject convert() {
		BasicDBObject basicDBObject = super.modelConvert();
		basicDBObject.append("username", username);
		basicDBObject.append("email", email);
		return basicDBObject;
	}
	
	public static class Builder {
		
		private User user;
		
		public Builder() {
			this.user = new User();
		}
			
		public Builder setUsername(String username) {
			this.user.setUsername(username);
			return this;
		}
		
		public Builder setEmail(String email) {
			this.user.setEmail(email);
			return this;
		}
		
		public User build() {
			return user;
		}
		
	}
	
}
