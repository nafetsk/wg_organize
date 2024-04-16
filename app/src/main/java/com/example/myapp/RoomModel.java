package com.example.myapp;

public class RoomModel {
    private String roomName;
    private String dateLastCleaned;

    public RoomModel(String roomName, String dateLastCleaned){
        this.roomName = roomName;
        this.dateLastCleaned = dateLastCleaned;
    }

    public String getDateLastCleaned() {
        return dateLastCleaned;
    }

    public String getRoomName() {
        return roomName;
    }
}
