package com.kainzt.splatournament_client.dtos;

import org.json.JSONObject;

public class TournamentDTO {

    private final String name;
    private final int maxTeams;
    private final int bestOf;
    private final String style;
    private final String createdBy;

    public TournamentDTO(String name, int maxTeams, int bestOf, String style, String createdBy) {

        this.name = name;
        this.maxTeams = maxTeams;
        this.bestOf = bestOf;
        this.style = style;
        this.createdBy = createdBy;
    }
}
