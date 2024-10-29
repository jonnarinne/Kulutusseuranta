package syksy24.kulutusseuranta.domain;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;
import jakarta.persistence.*;


import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="category")
public class Category {
 
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@NotNull
	private Long id;

	@Column(name="name")
	@Size(min=5, max=30)
	private String name;

	@OneToMany(mappedBy = "category")
	@JsonIgnore
    private List<Expense> expenses;
	
	public Category() {}
	
	public Category(String name, List<Expense> expenses) {
		super();
		this.name = name;
	}
	
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

	public List<Expense> getExpenses() {
		return expenses;
	}

	public void setCars(List<Expense> expenses) {
		this.expenses = expenses;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + "]";
	}


}
