package com.kainzt.splatournament_client.models;

public class Tournament {
    int id;
    String name;
    String createdBy;
    int currentPlayerCount;

    public Tournament() {
    }
    public Tournament(int id, String name, String createdBy, int currentPlayerCount) {
        this.id = id;
        this.name = name;
        this.createdBy = createdBy;
        this.currentPlayerCount = currentPlayerCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public int getCurrentPlayerCount() {
        return currentPlayerCount;
    }

    public void setCurrentPlayerCount(int currentPlayerCount) {
        this.currentPlayerCount = currentPlayerCount;
    }
}
