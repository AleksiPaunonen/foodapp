package fi.hh.foodapp.domain;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Category {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long categoryid;
	
	private String name;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
	private List<Food> foods;
	
	// parametriton konstruktori
	public Category() {
		
	}
	
	// parametrillinen konstruktori
	public Category(String name) {
		super();
		this.name = name;
	}

	// getterit ja setterit
	public Long getCategoryid() {
		return categoryid;
	}

	public void setCategoryid(Long categoryid) {
		this.categoryid = categoryid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Food> getFoods() {
		return foods;
	}

	public void setFoods(List<Food> foods) {
		this.foods = foods;
	}

	@Override
	public String toString() {
		return "Category [categoryid=" + categoryid + ", name=" + name + ", foods=" + foods + "]";
	}	
	
}
