package syksy24.kulutusseuranta.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Category {
 
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@NotNull
	private Long categoryid;

	@Size(min=5, max=30)
	private String name;

	@OneToMany(mappedBy = "category") // Tämä kertoo, että tämä on monelle-toiseen suhde Expense-luokassa
	@JsonIgnore
    private List<Expense> expenses; // Lista kuluista, jotka kuuluvat tähän kategoriaan
	
	public Category() {}
	
	public Category(String name) {
		super();
		this.name = name;
	}
	
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

	@Override
	public String toString() {
		return "Category [categoryid=" + categoryid + ", name=" + name + "]";
	}
}
