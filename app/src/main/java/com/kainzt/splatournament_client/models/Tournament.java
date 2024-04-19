package com.kainzt.splatournament_client.models;

import com.kainzt.splatournament_client.enums.TournamentStyle;

public class Tournament {
    int id;
    String name;
    String createdBy;
    TournamentStyle style;
    int currentPlayerCount;

    public Tournament() {
    }
    public Tournament(int id, String name, TournamentStyle style, int currentPlayerCount,String createdBy) {
        this.id = id;
        this.name = name;
        this.style = style;
        this.currentPlayerCount = currentPlayerCount;
        this.createdBy = createdBy;
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

    public TournamentStyle getStyle() {
        return style;
    }

    public void setStyle(TournamentStyle style) {
        this.style = style;
    }
}
