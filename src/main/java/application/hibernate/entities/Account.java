package application.hibernate.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "balance")
	private double balance = 0;

	@Column(name = "overdraft")
	private double overdraft = 0;

	@Column(name = "max_overdraft")
	private double maxOverdraft = 800.000;

	@Column(name = "max_withdrawal")
	private double maxWithdrawal = 1000.000;

	@ManyToOne
	private Person person;

	public Account() {
	}

	public Account(double balance, double maxOverdraft, double maxWithdrawal) {
		super();
		this.balance = balance;
		this.maxOverdraft = maxOverdraft;
		this.maxWithdrawal = maxWithdrawal;
	}

	public Account(double balance) {
		super();
		this.balance = balance;
		if (balance < -this.maxOverdraft)
			this.setMaxOverdraft(-balance);
	}

	public Account(Long id) {
		this.id = id;
	}

	public Long getId() {
		return this.id;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		// TODO: Update overdraft here maybe
		this.balance = balance;
	}

	public double getOverdraft() {
		return overdraft;
	}

	public void setOverdraft(double overdraft) {
		this.overdraft = overdraft;
	}

	public double getMaxOverdraft() {
		return maxOverdraft;
	}

	public void setMaxOverdraft(double maxOverdraft) {
		this.maxOverdraft = maxOverdraft;
	}

	public double getMaxWithdrawal() {
		return maxWithdrawal;
	}

	public void setMaxWithdrawal(double maxWithdrawal) {
		this.maxWithdrawal = maxWithdrawal;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", balance=" + balance + ", overdraft=" + overdraft + ", maxOverdraft="
				+ maxOverdraft + ", maxWithdrawal=" + maxWithdrawal + ", person=" + person + "]";
	}

	public void updateOverdraft() {
		System.out.println("Updating overdraft: " + this.balance);
		if (this.balance < 0)
			this.setOverdraft(-this.balance);
		else
			this.setOverdraft(0);
	}

	public void deposit(Double amount) {
		this.setBalance(this.balance + amount);
		this.updateOverdraft();
	}

	public boolean withdraw(Double amount, boolean withLimit) {
		if (withLimit && amount > this.maxWithdrawal)
			return false;

		Double newAmount = this.balance - amount;
		if (newAmount < -this.maxOverdraft)
			return false;

		this.setBalance(newAmount);
		this.updateOverdraft();
		return true;
	}
}
