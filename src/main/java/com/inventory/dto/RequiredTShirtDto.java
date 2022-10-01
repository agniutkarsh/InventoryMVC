package com.inventory.dto;

public class RequiredTShirtDto {

	private String color;
	private String gender;
	private String size;
	private int sortPreference;

	

	
	
	public int getSortPreference() {
		return sortPreference;
	}

	public void setSortPreference(int sortPreference) {
		this.sortPreference = sortPreference;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	

	
}
