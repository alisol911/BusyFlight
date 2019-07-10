package com.travix.medusa.toughjet.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    private String carrier;
    private double basePrice;
    private double tax;
    private double discount;
    private String departureAirportName;
    private String arrivalAirportName;
    private Date outboundDateTime;
    private Date inboundDateTime;

    public Flight(String carrier, double basePrice, double tax, double discount,
                  String departureAirportName, String arrivalAirportName,
                  Date outboundDateTime, Date inboundDateTime) {
        this.carrier = carrier;
        this.basePrice = basePrice;
        this.tax = tax;
        this.discount = discount;
        this.departureAirportName = departureAirportName;
        this.arrivalAirportName = arrivalAirportName;
        this.outboundDateTime = outboundDateTime;
        this.inboundDateTime = inboundDateTime;
    }

    public Flight() {
    
    }
    
    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(final String carrier) {
        this.carrier = carrier;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(final double basePrice) {
        this.basePrice = basePrice;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(final double tax) {
        this.tax = tax;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(final double discount) {
        this.discount = discount;
    }

    public String getDepartureAirportName() {
        return departureAirportName;
    }

    public void setDepartureAirportName(final String departureAirportName) {
        this.departureAirportName = departureAirportName;
    }

    public String getArrivalAirportName() {
        return arrivalAirportName;
    }

    public void setArrivalAirportName(final String arrivalAirportName) {
        this.arrivalAirportName = arrivalAirportName;
    }

    public Date getOutboundDateTime() {
        return outboundDateTime;
    }

    public void setOutboundDateTime(final Date outboundDateTime) {
        this.outboundDateTime = outboundDateTime;
    }

    public Date getInboundDateTime() {
        return inboundDateTime;
    }

    public void setInboundDateTime(final Date inboundDateTime) {
        this.inboundDateTime = inboundDateTime;
    }
}
