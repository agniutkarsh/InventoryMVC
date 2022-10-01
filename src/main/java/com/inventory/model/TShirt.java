package com.inventory.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class TShirt {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int primarykey;
	private String id;
	private String name;
	private String color;
	private String gender;
	private String size;
	private float price;
	private float rating;
	private String availability;

	

	public TShirt() {
		super();
		// TODO Auto-generated constructor stub
	}

	public  TShirt(String id, String name, String color, String gender, String size, float price, float rating,
			String availability) {
		this.id = id;
		this.name = name;
		this.color = color;
		this.gender = gender;
		this.size = size;
		this.price = price;
		this.rating = rating;
		this.availability = availability;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public String getAvailability() {
		return availability;
	}

	public void setAvailability(String availability) {
		this.availability = availability;
	}

	public String toString() {
		return this.id + ":" + this.name + ":" + this.color + ":" + this.gender + ":" + this.size + ":" + this.price
				+ ":" + this.rating + ":" + this.availability;

	}
}
