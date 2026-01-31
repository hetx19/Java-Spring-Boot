package com.spring.core;

public class Client {
    public static void main(String[] args) {
        Vehicle vehicle = new Bike();
        Traveller traveller = new Traveller(vehicle);
        traveller.startJourney();
    }
}
