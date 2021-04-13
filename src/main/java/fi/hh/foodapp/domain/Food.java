package fi.hh.foodapp.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Food {

	// attribuutit
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String name;
	private String description;
	private double price;
	private String link;
	
	@ManyToOne
	@JoinColumn(name = "categoryid")
	private Category category; 
	
	// parametriton konstruktori
	public Food() {

	}

	// parametrillinen konstruktori
	public Food(String name, String description, double price, String link, Category category) {
		super();
		this.name = name;
		this.description = description;
		this.price = price;
		this.link = link;
		this.category = category;
	}

	// getterit ja setterit
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	// toString-metodi
	@Override
	public String toString() {
		return "Food [id=" + id + ", name=" + name + ", description=" + description + ", price=" + price + ", link="
				+ link + ", category=" + category + "]";
	}

}
