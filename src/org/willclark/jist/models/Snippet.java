package org.willclark.jist.models;

import java.util.List;

import com.google.code.morphia.annotations.Embedded;
import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Reference;

@Entity
public class Snippet extends Model {
	
	public enum Language {
		JAVA,C,PHP,PYTHON,RUBY,JAVASCRIPT,HTML;
	}
	
	@Embedded
	private User user;
	
	private Language language;
	private String code;
	private long views;
	
	@Reference 
	private List<User> favorites;
	
	protected Snippet() {}
	
	public Snippet(User user) {
		this.user = user;
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
