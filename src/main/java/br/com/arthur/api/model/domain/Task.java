package br.com.arthur.api.model.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Task {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String description;
	private String owner;
	private String dueDate;
	private Double duration;
	private String status;
	private Boolean pending;
	private String comment;
	private Urgency urgency;
	
	public Task() {}

	public Task(String description, String owner, String dueDate, Double duration, String status,
			Boolean pending, String comment, Urgency urgency) {

		this.description = description;
		this.owner = owner;
		this.dueDate = dueDate;
		this.duration = duration;
		this.status = status;
		this.pending = pending;
		this.comment = comment;
		this.urgency = urgency;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String task) {
		this.description = task;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getDueDate() {
		return dueDate;
	}
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
	public Double getDuration() {
		return duration;
	}
	public void setDuration(Double duration) {
		this.duration = duration;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Boolean isPending() {
		return pending;
	}
	public void setPending(Boolean pending) {
		this.pending = pending;
	}
	public Integer getId() {
		return id;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Urgency getUrgency() {
		return urgency;
	}
	public void setUrgency(Urgency urgency) {
		this.urgency = urgency;
	}

	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return String.format("[Task] id = %d, Description = %s, Owner = %s, DueDate = %s, Duration = %.2f, Status = %s, Pending = %s, Comment = %s, Urgency = %s", 
				this.getId(),
				this.getDescription(), 
				this.getOwner(),
				this.getDueDate(),
				this.getDuration(),
				this.getStatus(),
				this.isPending(),
				this.getComment(),
				this.getUrgency()

			);
	}
	
	
	
	
}
