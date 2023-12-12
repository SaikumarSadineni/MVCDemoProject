package com.practice.vo;

import java.util.*;

public class FIELDSVO {
	
	private String category;
	private String notesType;
	private String notes;
	private Date dateandtime;
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getNotesType() {
		return notesType;
	}
	public void setNotesType(String notesType) {
		this.notesType = notesType;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public Date getDateandtime() {
		return dateandtime;
	}
	public void setDateandtime(Date dateandtime) {
		this.dateandtime = dateandtime;
	}
	@Override
	public String toString() {
		return "FIELDSVO [category=" + category + ", notesType=" + notesType + ", notes=" + notes + ", dateandtime="
				+ dateandtime + "]";
	}

	
}
