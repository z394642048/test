package com.weakReference;

public class Car
{
    private double     price;
    private String    color;
    
    public Car(double price, String color)
    {
        this.price = price;
        this.color = color;
    }
    
    public double getPrice()
    {
        return price;
    }

    public String getColor()
    {
        return color;
    }

    public String toString()
    {
        return "This car is a " + this.color + " car, costs $" + price;
    }
}