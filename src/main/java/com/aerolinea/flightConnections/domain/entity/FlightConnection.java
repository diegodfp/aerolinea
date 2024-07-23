package com.aerolinea.flightConnections.domain.entity;
import java.time.LocalDateTime;

public class FlightConnection {
    private int id;
    private String numConnection;
    private int idTrip;
    private int idPlane;
    private String departureAirport;
    private String arrivalAirport;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private int orderNumber;
    private ConnectionType connectionType;

    // Enum para representar el tipo de conexión
    public enum ConnectionType {
        DIRECT, LAYOVER
    }

    // Constructor
    public FlightConnection(int id, String numConnection, int idTrip, int idPlane,
                            String departureAirport, String arrivalAirport,
                            LocalDateTime departureTime, LocalDateTime arrivalTime,
                            int orderNumber, ConnectionType connectionType) {
        this.id = id;
        this.numConnection = numConnection;
        this.idTrip = idTrip;
        this.idPlane = idPlane;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.orderNumber = orderNumber;
        this.connectionType = connectionType;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumConnection() {
        return numConnection;
    }

    public void setNumConnection(String numConnection) {
        this.numConnection = numConnection;
    }

    public int getIdTrip() {
        return idTrip;
    }

    public void setIdTrip(int idTrip) {
        this.idTrip = idTrip;
    }

    public int getIdPlane() {
        return idPlane;
    }

    public void setIdPlane(int idPlane) {
        this.idPlane = idPlane;
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

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public ConnectionType getConnectionType() {
        return connectionType;
    }

    public void setConnectionType(ConnectionType connectionType) {
        this.connectionType = connectionType;
    }

    @Override
    public String toString() {
        return "FlightConnection{" +
                "id=" + id +
                ", numConnection='" + numConnection + '\'' +
                ", idTrip=" + idTrip +
                ", idPlane=" + idPlane +
                ", departureAirport='" + departureAirport + '\'' +
                ", arrivalAirport='" + arrivalAirport + '\'' +
                ", departureTime=" + departureTime +
                ", arrivalTime=" + arrivalTime +
                ", orderNumber=" + orderNumber +
                ", connectionType=" + connectionType +
                '}';
    }
}