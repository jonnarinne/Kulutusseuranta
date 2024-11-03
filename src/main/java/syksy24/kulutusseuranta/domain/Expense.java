package syksy24.kulutusseuranta.domain;

import java.time.LocalDate;

import jakarta.persistence.*;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;


@Entity
@Table(name="expense")
public class Expense {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

    @Column(name="name")
	@Size(min=1, max=30)
    private String name;

    @Column(name="amount")
    @Min(value = 1, message = "Summan täytyy olla vähintään 1 euro")
    private double amount;

    @Column(name="date")
    private LocalDate date;

    @Column(name="shop")
    @Size(min=1, max=30)
    private String shop;
    
    @ManyToOne
    @JoinColumn(name = "categoryid")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "userid")
    private AppUser appuser;


    public Expense() {
		super();
	}

    public Expense(String name, double amount, LocalDate date, String shop, Category category, AppUser appuser) {
        this.name = name;
        this.amount = amount;
        this.date = date;
        this.shop = shop;
        this.category = category;
        this.appuser = appuser;
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

    public AppUser getAppuser() {
        return appuser;
    }

    public void setAppuser(AppUser appuser) {
        this.appuser = appuser;
    }

    @Override
    public String toString() {
        return "Expense [id=" + id + ", name=" + name + ", amount=" + amount + ", date=" + date + ", shop=" + shop
                + ", category=" + category + ", appuser=" + appuser + "]";
    }
    
}