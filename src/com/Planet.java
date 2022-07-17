package com;

public class Planet {
    private int id;
    private Planet[] exitPlanet;

    public Planet(int id, int exitPlanetCount) {
        this.id = id;
        this.exitPlanet = exitPlanetCount <= 0 ? null : new Planet[exitPlanetCount];
    }

    public int getId() {
        return id;
    }

    public Planet[] getExitPlanet() {
        return exitPlanet;
    }

    public String info() {
        String exitPlanetArrayText = "";

        if (this.exitPlanet == null)
            exitPlanetArrayText += this.id + ", null";
        else {
            exitPlanetArrayText += this.id + ", [";
            for (int i = 0; i < this.exitPlanet.length; i++) {
                exitPlanetArrayText += this.exitPlanet[i].getId();
                if (i < this.exitPlanet.length - 1)
                    exitPlanetArrayText += ", ";
                else
                    exitPlanetArrayText += "]";
            }
        }
        return exitPlanetArrayText;
    }
}
