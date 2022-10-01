
package com.inventory.csvservices;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.KeywordField;

import com.opencsv.bean.CsvBindByPosition;

@Entity
@Table( name = "tshirt")
@Indexed
public class TShirt implements Comparable<TShirt>{

	@Id
    @KeywordField
    @Column(name = "id", nullable = false)
    @CsvBindByPosition(position = 0, required = true)
    private String id;

	@FullTextField
    @CsvBindByPosition(position = 1, required = true)
    private String name;

	@KeywordField
    @CsvBindByPosition(position = 2, required = true)
    private String colour;

	@KeywordField
    @CsvBindByPosition(position = 3, required = true)
    private String genderRecommendation;

	@KeywordField
    @CsvBindByPosition(position = 4, required = true)
    private String size;

    @CsvBindByPosition(position = 5, required = true)
    private double price;

    @CsvBindByPosition(position = 6, required = true)
    private double rating;

    @KeywordField
    @CsvBindByPosition(position = 7, required = true)
    private String availability;

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

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String genderRecommendation() {
        return genderRecommendation;
    }

    public void setGenderRecommended(String genderRecommendation) {
        this.genderRecommendation = genderRecommendation;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public boolean getAvailability() {
        return availability.equalsIgnoreCase("y");
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

   

    @Override
    public int compareTo(TShirt tshirt) {
        return Double.compare(this.getPrice(), tshirt.getPrice());
    }
}
