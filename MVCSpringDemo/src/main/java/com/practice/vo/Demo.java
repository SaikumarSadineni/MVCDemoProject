package com.practice.vo;

public class Demo {
	private int id;
	private String subcategory;
	private String notetype;
	private String notes;
	private String dateandtime;
	


	public String getSubcategory() {
		return subcategory;
	}
	public void setSubcategory(String subcategory) {
		this.subcategory = subcategory;
	}
	public String getNotetype() {
		return notetype;
	}
	public void setNotetype(String notetype) {
		this.notetype = notetype;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public String getDateandtime() {
		return dateandtime;
	}
	public void setDateandtime(String string) {
		this.dateandtime = string;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "Demo [subcategory=" + subcategory + ", notetype=" + notetype + ", notes=" + notes + ", dateandtime="
				+ dateandtime + "]";
	}


}
