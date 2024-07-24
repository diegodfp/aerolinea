package com.aerolinea.tripCrew.domain.entity;

public class TripConnectionInfo {
    private int connectionId;
    private String numConnection;
    private int tripId;
    private String tripDate;
    private double tripPrice;
    private String originAirport;
    private String destinationAirport;
    private int planeId;
    private String departureAirport;
    private String arrivalAirport;
    private String departureTime;
    private String arrivalTime;
    private int orderNumber;
    private String connectionType;

    public TripConnectionInfo() {
    }

    public TripConnectionInfo(int connectionId, String numConnection, int tripId, String tripDate, double tripPrice, String originAirport, String destinationAirport, int planeId, String departureAirport, String arrivalAirport, String departureTime, String arrivalTime, int orderNumber, String connectionType) {
        this.connectionId = connectionId;
        this.numConnection = numConnection;
        this.tripId = tripId;
        this.tripDate = tripDate;
        this.tripPrice = tripPrice;
        this.originAirport = originAirport;
        this.destinationAirport = destinationAirport;
        this.planeId = planeId;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.orderNumber = orderNumber;
        this.connectionType = connectionType;
    }

    public int getConnectionId() {
        return connectionId;
    }

    public void setConnectionId(int connectionId) {
        this.connectionId = connectionId;
    }

    public String getNumConnection() {
        return numConnection;
    }

    public void setNumConnection(String numConnection) {
        this.numConnection = numConnection;
    }

    public int getTripId() {
        return tripId;
    }

    public void setTripId(int tripId) {
        this.tripId = tripId;
    }

    public String getTripDate() {
        return tripDate;
    }

    public void setTripDate(String tripDate) {
        this.tripDate = tripDate;
    }

    public double getTripPrice() {
        return tripPrice;
    }

    public void setTripPrice(double tripPrice) {
        this.tripPrice = tripPrice;
    }

    public String getOriginAirport() {
        return originAirport;
    }

    public void setOriginAirport(String originAirport) {
        this.originAirport = originAirport;
    }

    public String getDestinationAirport() {
        return destinationAirport;
    }

    public void setDestinationAirport(String destinationAirport) {
        this.destinationAirport = destinationAirport;
    }

    public int getPlaneId() {
        return planeId;
    }

    public void setPlaneId(int planeId) {
        this.planeId = planeId;
    }

    public String getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(String departureAirport) {
        this.departureAirport = departureAirport;
    }

    public String getArrivalAirport() {
        return arrivalAirport;
    }

    public void setArrivalAirport(String arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getConnectionType() {
        return connectionType;
    }

    public void setConnectionType(String connectionType) {
        this.connectionType = connectionType;
    }
}
