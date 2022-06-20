package io.dbc.github.sninventory.model;

import java.sql.Date;
import java.util.Objects;

public class Purchase extends Product {

    private int purchaseID;

    private double purchasePrice;

    private Date purchaseDate;

    private double totalAmount;

    private int quantityPurchased;


    public Purchase(int purchaseID, String productName, int quantityPurchased, double purchasePrice, Date purchaseDate, Date expiryDate) {
        super(productName, expiryDate);
        this.quantityPurchased = quantityPurchased;
        this.purchaseID = purchaseID;
        this.purchasePrice = purchasePrice;
        this.purchaseDate = purchaseDate;
    }

    public Purchase(int purchaseID, String productName, int quantityPurchased, double purchasePrice, Date purchaseDate, Date expiryDate, double totalAmount) {
        super(productName, expiryDate);
        this.quantityPurchased = quantityPurchased;
        this.purchaseID = purchaseID;
        this.purchasePrice = purchasePrice;
        this.purchaseDate = purchaseDate;
        this.totalAmount = totalAmount;
    }

    public Purchase(int purchaseID, String productName, int quantityPurchased, double purchasePrice, Date purchaseDate, double totalAmount) {
        super(productName);
        this.quantityPurchased = quantityPurchased;
        this.purchaseID = purchaseID;
        this.purchasePrice = purchasePrice;
        this.purchaseDate = purchaseDate;
        this.totalAmount = totalAmount;
    }

    public Purchase(int purchaseID, String productName, int quantityPurchased, Date expiryDate) {
        super(productName, expiryDate);
        this.purchaseID = purchaseID;
        this.quantityPurchased = quantityPurchased;
    }

    public Purchase(int purchaseID, String productName, int quantityPurchased, Date expiryDate, int daysLeftInExpiry) {
        super(productName, expiryDate, daysLeftInExpiry);
        this.purchaseID = purchaseID;
        this.quantityPurchased = quantityPurchased;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getPurchaseID() {
        return purchaseID;
    }

    public void setPurchaseID(int purchaseID) {
        this.purchaseID = purchaseID;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public int getQuantityPurchased() {
        return quantityPurchased;
    }

    public void setQuantityPurchased(int quantityPurchased) {
        this.quantityPurchased = quantityPurchased;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Purchase)) return false;
        if (!super.equals(o)) return false;
        Purchase purchase = (Purchase) o;
        return getPurchaseID() == purchase.getPurchaseID() && Double.compare(purchase.getPurchasePrice(), getPurchasePrice()) == 0 && Double.compare(purchase.getTotalAmount(), getTotalAmount()) == 0 && getQuantityPurchased() == purchase.getQuantityPurchased() && Objects.equals(getPurchaseDate(), purchase.getPurchaseDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getPurchaseID(), getPurchasePrice(), getPurchaseDate(), getTotalAmount(), getQuantityPurchased());
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "purchaseID=" + purchaseID +
                ", purchasePrice=" + purchasePrice +
                ", purchaseDate=" + purchaseDate +
                ", totalAmount=" + totalAmount +
                ", quantityPurchased=" + quantityPurchased +
                '}';
    }
}



