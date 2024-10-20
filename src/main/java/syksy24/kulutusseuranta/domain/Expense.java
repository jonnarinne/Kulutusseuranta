package syksy24.kulutusseuranta.domain;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

@Entity
public class Expense {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull
	private Long id;

    private String name;
    private double amount;
    private LocalDate date;
    private String location;
    
    @ManyToOne
    private Category category;


    public Expense() {
		super();
	}

    public Expense(String name, double amount, LocalDate date, String location, Category category) {
        this.name = name;
        this.amount = amount;
        this.date = date;
        this.location = location;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Expense [name=" + name + ", amount=" + amount + ", date=" + date + ", location=" + location
                + ", category=" + category + "]";
    } 
    
}