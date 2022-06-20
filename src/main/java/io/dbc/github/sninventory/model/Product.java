package io.dbc.github.sninventory.model;

import java.sql.Date;
import java.util.Objects;

public class Product {

    private String productName;

    private Date expiryDate;

    private double productPrice;

    private String description;

    private int quantity;

    private int daysLeftInExpiry;

    public Product(String productName) {
        this.productName = productName;
    }

    public Product(String productName, double productPrice) {
        this.productName = productName;
        this.productPrice = productPrice;
    }

    public Product(String productName, Date expiryDate) {
        this.productName = productName;
        this.expiryDate = expiryDate;
    }

    public Product(String productName, double productPrice, int quantity, String description) {
        this.productName = productName;
        this.quantity = quantity;
        this.productPrice = productPrice;
        this.description = description;

    }

    public Product(String productName, int quantity, double productPrice) {
        this.productName = productName;
        this.quantity = quantity;
        this.productPrice = productPrice;
    }

    public Product(String productName, double productPrice, String description) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.description = description;
    }

    public Product(String productName, Date expiryDate, double productPrice, String description) {
        this.productName = productName;
        this.expiryDate = expiryDate;
        this.productPrice = productPrice;
        this.description = description;
    }

    public Product(String productName, Date expiryDate, int daysLeftInExpiry) {
        this.productName = productName;
        this.expiryDate = expiryDate;
        this.daysLeftInExpiry = daysLeftInExpiry;
    }

    public int getDaysLeftInExpiry() {
        return daysLeftInExpiry;
    }

    public void setDaysLeftInExpiry(int daysLeftInExpiry) {
        this.daysLeftInExpiry = daysLeftInExpiry;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return Double.compare(product.getProductPrice(), getProductPrice()) == 0 && getQuantity() == product.getQuantity() && Objects.equals(getProductName(), product.getProductName()) && Objects.equals(getExpiryDate(), product.getExpiryDate()) && Objects.equals(getDescription(), product.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProductName(), getExpiryDate(), getProductPrice(), getDescription(), getQuantity());
    }

    @Override
    public String toString() {
        return "Product{" + "productName='" + productName + '\'' + ", productPrice=" + productPrice + ", Description='" + description + '\'' + ", quantity=" + quantity + '}';
    }
}
