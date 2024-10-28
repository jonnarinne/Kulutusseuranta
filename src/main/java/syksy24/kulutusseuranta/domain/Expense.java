package syksy24.kulutusseuranta.domain;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;


@Entity
public class Expense {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Size(min=1, max=30)
    private String name;

    @Min(value = 1, message = "Summan täytyy olla vähintään 1 euro")
    private double amount;

    private LocalDate date;

    @Size(min=1, max=30)
    private String shop;
    
    @ManyToOne
    private Category category;


    public Expense() {
		super();
	}

    public Expense(String name, double amount, LocalDate date, String shop, Category category) {
        this.name = name;
        this.amount = amount;
        this.date = date;
        this.shop = shop;
        this.category = category;
    }

    public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

    public String getShop() {
        return shop;
    }

    public void setShop(String shop) {
        this.shop = shop;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Expense [name=" + name + ", amount=" + amount + ", date=" + date + ", shop=" + shop
                + ", category=" + category + "]";
    } 
    
}