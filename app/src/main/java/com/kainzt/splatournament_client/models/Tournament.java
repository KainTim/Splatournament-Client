package com.kainzt.splatournament_client.models;

import com.kainzt.splatournament_client.enums.TournamentStyle;

import java.util.ArrayList;
import java.util.List;

public class Tournament {
    int id;
    String name;
    String createdBy;
    TournamentStyle style;
    int currentPlayerCount;
    Long bestOf;

    List<List<Long>> sets= new ArrayList<>();

    public Tournament() {
    }
    public Tournament(int id, String name, TournamentStyle style, int currentPlayerCount,String createdBy,Long bestOf) {
        this.id = id;
        this.name = name;
        this.style = style;
        this.currentPlayerCount = currentPlayerCount;
        this.createdBy = createdBy;
        this.bestOf = bestOf;
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

    public List<List<Long>> getSets() {
        return sets;
    }

    public void setSets(List<List<Long>> sets) {
        this.sets = sets;
    }

    public Long getBestOf() {
        return bestOf;
    }

    public void setBestOf(Long bestOf) {
        this.bestOf = bestOf;
    }
}
