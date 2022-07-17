package com;

public class Main {
    public static void main(String[] args) {
        System.out.println("************************** PlanetTravel 1 ****************************");
        UIPlanetTravel uiPlanetTravel1 = new UIPlanetTravel("src/com/public_file_1");
        uiPlanetTravel1.printPlanetsExitInfo();
        System.out.println("1 " + uiPlanetTravel1.planetListInfo());
        System.out.println("1 Count:" + uiPlanetTravel1.getAbsoluteVisitCount());


        System.out.println("************************** PlanetTravel 2 ****************************");
        UIPlanetTravel uiPlanetTravel2 = new UIPlanetTravel("src/com/public_file_2");
        uiPlanetTravel2.printPlanetsExitInfo();
        System.out.println("1 " + uiPlanetTravel2.planetListInfo());
        System.out.println("2 Count:" + uiPlanetTravel2.getAbsoluteVisitCount());
    }
}
