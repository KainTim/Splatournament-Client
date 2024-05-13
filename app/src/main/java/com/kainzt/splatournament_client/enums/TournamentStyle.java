package com.kainzt.splatournament_client.enums;

import androidx.annotation.NonNull;

//Temporary
public enum TournamentStyle {
    SWISS{
        @NonNull
        @Override
        public String toString() {
            return "Swiss";
        }
    },INVALID;

    public static TournamentStyle getEnum(String value) {
        for(TournamentStyle v : values())
            if(v.toString().equalsIgnoreCase(value)) return v;
        throw new IllegalArgumentException();
    }
}
