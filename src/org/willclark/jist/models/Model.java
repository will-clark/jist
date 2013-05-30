package org.willclark.jist.models;

import java.util.Date;

import org.bson.types.ObjectId;

import com.google.code.morphia.annotations.Id;

public abstract class Model {

	@Id
	protected ObjectId _id;

	protected Date createdOn;
	protected Date accessedOn;
	protected Date updatedOn;
	protected Date deletedOn;
	
	public Model() {}
	
	public ObjectId getId() {
		return _id;
	}

	public void setId(ObjectId _id) {
		this._id = _id;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Date getAccessedOn() {
		return accessedOn;
	}

	public void setAccessedOn(Date accessedOn) {
		this.accessedOn = accessedOn;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	public Date getDeletedOn() {
		return deletedOn;
	}

	public void setDeletedOn(Date deletedOn) {
		this.deletedOn = deletedOn;
	}

	public abstract String toString();
	
	protected String modelToString() {
		StringBuilder sb = new StringBuilder();
		sb.append("_id: ").append(_id).append(", ");
		sb.append("createdOn: ").append(createdOn).append(", ");
		sb.append("accessedOn: ").append(accessedOn).append(", ");
		sb.append("updatedOn: ").append(updatedOn).append(", ");
		sb.append("deletedOn: ").append(deletedOn).append(", ");
		return sb.toString();
	}
	
}
