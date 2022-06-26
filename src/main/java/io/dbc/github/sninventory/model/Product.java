package io.dbc.github.sninventory.model;

import java.sql.Date;
import java.util.Objects;

public class Product {

    private String productName;

    private double productPrice;

    private Date expiryDate;

    private String description;

    private int quantity;

    private int   daysLeftInExpiry;

    public Product(String productName, double productPrice, int quantity, String description) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.quantity = quantity;
        this.description = description;
    }

    public Product(String productName, double productPrice, Date expiryDate, String description) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.expiryDate = expiryDate;
        this.description = description;
    }

    public Product(String productName, double productPrice, int quantity) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.quantity = quantity;
    }

    public Product(String productName, Date expiryDate) {
        this.productName = productName;
        this.expiryDate = expiryDate;

    }

    public Product(String productName, Date expiryDate, int  daysLeftInExpiry) {
        this.productName = productName;
        this.expiryDate = expiryDate;
        this.daysLeftInExpiry = daysLeftInExpiry;
    }

    public Product(String productName) {
        this.productName = productName;
    }

    public Product(String productName, double productPrice) {
        this.productName = productName;
        this.productPrice = productPrice;
    }

    public int getDaysLeftInExpiry() {
        return daysLeftInExpiry;
    }

    public void setDaysLeftInExpiry(int  daysLeftInExpiry) {
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

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(product.productPrice, productPrice) == 0 && Objects.equals(productName, product.productName) && Objects.equals(expiryDate, product.expiryDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productName, productPrice, expiryDate);
    }

    @Override
    public String toString() {
        return "product{" + "productName='" + productName + '\'' + ", productPrice=" + productPrice + '}';
    }
}
