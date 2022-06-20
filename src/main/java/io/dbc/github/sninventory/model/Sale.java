package io.dbc.github.sninventory.model;

import java.sql.Date;
import java.util.Objects;

public class Sale extends Product {

    private int saleID;

    private int quantitySold;

    private Date dateOfSale;

    private boolean billPaid;

    private double totalAmount;

    public Sale(int saleID, String productName, int quantitySold, double productPrice, Date dateOfSale, boolean billPaid) {
        super(productName, productPrice);
        this.saleID = saleID;
        this.quantitySold = quantitySold;
        this.dateOfSale = dateOfSale;
        this.billPaid = billPaid;
    }

    public Sale(int saleID, String productName, int quantitySold, double productPrice, Date dateOfSale, double totalAmount) {
        super(productName, productPrice);
        this.saleID = saleID;
        this.quantitySold = quantitySold;
        this.dateOfSale = dateOfSale;
        this.totalAmount = totalAmount;
    }

    public Sale(int saleID, String productName, int quantitySold, double productPrice, Date dateOfSale, boolean billPaid, double totalAmount) {
        super(productName, productPrice);
        this.saleID = saleID;
        this.quantitySold = quantitySold;
        this.dateOfSale = dateOfSale;
        this.totalAmount = totalAmount;
        this.billPaid = billPaid;
    }

    public int getSaleID() {
        return saleID;
    }

    public void setSaleID(int saleID) {
        this.saleID = saleID;
    }

    public int getQuantitySold() {
        return quantitySold;
    }

    public void setQuantitySold(int quantitySold) {
        this.quantitySold = quantitySold;
    }

    public Date getDateOfSale() {
        return dateOfSale;
    }

    public void setDateOfSale(Date dateOfSale) {
        this.dateOfSale = dateOfSale;
    }

    public boolean isBillPaid() {
        return billPaid;
    }

    public void setBillPaid(boolean billPaid) {
        this.billPaid = billPaid;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sale)) return false;
        if (!super.equals(o)) return false;
        Sale sale = (Sale) o;
        return getSaleID() == sale.getSaleID() && getQuantitySold() == sale.getQuantitySold() && isBillPaid() == sale.isBillPaid() && Double.compare(sale.getTotalAmount(), getTotalAmount()) == 0 && getDateOfSale().equals(sale.getDateOfSale());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getSaleID(), getQuantitySold(), getDateOfSale(), isBillPaid(), getTotalAmount());
    }

}

