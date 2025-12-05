package com.p0_arcade.Classes;

import java.io.Serializable;

public class Wager implements Serializable{
    private static final long serialVersionUID = 1L;

    private int id;
    private int playerId;
    private int gameId;
    private int pointsWagered;
    private int pointsChanged; // net change: the amount of points gained or lost

}
