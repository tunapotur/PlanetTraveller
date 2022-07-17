package com;

// https://www.geeksforgeeks.org/inorder-traversal-of-an-n-ary-tree/
// https://ondrej-kvasnovsky-2.gitbook.io/algorithms/data-structures/n-ary-tree#1.-preorder-traversal
// https://ondrej-kvasnovsky-2.gitbook.io/algorithms/data-structures/n-ary-tree/n-ary-tree-preorder-traversal
// https://towardsdatascience.com/4-types-of-tree-traversal-algorithms-d56328450846

public class PlanetTravel {
    private Planet[] planetList;
    private Planet[] visitedPlanetList;

    private int absoluteVisitCount;

    public PlanetTravel(Planet[] planetList) {
        this.planetList = planetList;
        // planetList ile aynı boyutta elemanları null olan visitedPlanetList olusturuluyor
        this.visitedPlanetList = new Planet[this.planetList.length];
        this.absoluteVisitCount = 0;
    }

    private String listInfo(Planet[] planetList) {
        String text = "(";
        for (int i = 0; i < planetList.length; i++) {
            text += "{";

            if (planetList[i] == null)
                text += "Null";
            else
                text += planetList[i].info();

            if (i < planetList.length - 1)
                text += "}, ";
            else
                text += "}";
        }
        text += ")";
        return text;
    }

    public String planetListInfo() {
        return this.listInfo(this.planetList);
    }

    public int getAbsoluteVisitCount() {
        return absoluteVisitCount;
    }

    private boolean haveVisitedPlanetList(Planet planet) {
        for (int i = 0; i < this.visitedPlanetList.length; i++)
            if (this.visitedPlanetList[i] == planet)
                return true;
        return false;
    }

    // 0 empty -1 full visitedPlanetList
    private int getEmptyPlace() {
        for (int i = 0; i < this.visitedPlanetList.length; i++)
            if (this.visitedPlanetList[i] == null)
                return i;
        return -1;
    }

    private int locateVisitedPlanetList(Planet planet) {
        int locatedRowNumber = -1;

        // if visitedPlanetList is not full
        if (this.getEmptyPlace() != -1) {
            // check if visitedPlanetList have not same Planet
            if (!this.haveVisitedPlanetList(planet)) {
                locatedRowNumber = this.getEmptyPlace();
                // locate planet into visitidPlanetList first empty row
                this.visitedPlanetList[locatedRowNumber] = planet;
            }
        }

        return locatedRowNumber;
    }

    public void makePlanetRing(Planet planet) {
        if (planet.getExitPlanet() == null) {
            this.locateVisitedPlanetList(planet);
            return;
        }

        this.locateVisitedPlanetList(planet);

        int total = planet.getExitPlanet().length;

        for (int i = 0; i < total; i++)
            makePlanetRing(planet.getExitPlanet()[i]);
    }

    public void printPlanetRing(Planet planet) {
        if (planet.getExitPlanet() == null) {
            System.out.print("" + planet.getId() + " ");
            return;
        }

        System.out.print("" + planet.getId() + " ");

        int total = planet.getExitPlanet().length;

        for (int i = 0; i < total; i++)
            printPlanetRing(planet.getExitPlanet()[i]);
    }

    public void calculateAbsoluteVisitCount() {
        if (this.absoluteVisitCount == 0) {
            for (Planet planet : this.planetList)
                if (!this.haveVisitedPlanetList(planet)) {
                    this.makePlanetRing(planet);
                    ++this.absoluteVisitCount;
                }
        }
    }
}

class TestPlanetTravel {
    public static void main(String[] args) {
        Planet planet10 = new Planet(0, 1);
        Planet planet11 = new Planet(1, 1);
        Planet planet12 = new Planet(2, 0);
        Planet planet13 = new Planet(3, 1);

        planet10.getExitPlanet()[0] = planet11;
        planet11.getExitPlanet()[0] = planet12;
        planet13.getExitPlanet()[0] = planet11;

        PlanetTravel planetTravel1 = new PlanetTravel(new Planet[]{planet10, planet11, planet12, planet13});

        Planet planet20 = new Planet(0, 0);
        Planet planet21 = new Planet(1, 1);
        Planet planet22 = new Planet(2, 2);
        Planet planet23 = new Planet(3, 1);
        Planet planet24 = new Planet(4, 0);
        Planet planet25 = new Planet(5, 0);

        planet21.getExitPlanet()[0] = planet22;

        planet22.getExitPlanet()[0] = planet24;
        planet22.getExitPlanet()[1] = planet25;

        planet23.getExitPlanet()[0] = planet22;

        PlanetTravel planetTravel2 = new PlanetTravel(new Planet[]{planet20, planet21, planet22, planet23, planet24, planet25});

        System.out.println("***************************** Planet Info *************************");
        System.out.println("1 " + planetTravel1.planetListInfo());
        System.out.println("2 " + planetTravel2.planetListInfo());
        System.out.println("*******************************************************************");

        System.out.println("******************** Planet 2 Planet Rıng Sample *******************");
        planetTravel2.printPlanetRing(planet20);
        System.out.println();

        planetTravel2.printPlanetRing(planet21);
        System.out.println();

        planetTravel2.printPlanetRing(planet22);
        System.out.println();
        planetTravel2.printPlanetRing(planet23);
        System.out.println();
        planetTravel2.printPlanetRing(planet24);
        System.out.println();
        planetTravel2.printPlanetRing(planet25);
        System.out.println();
        System.out.println("*******************************************************************");

        planetTravel1.calculateAbsoluteVisitCount();
        planetTravel2.calculateAbsoluteVisitCount();

        System.out.println("1 Count:" + planetTravel1.getAbsoluteVisitCount());
        System.out.println("2 Count:" + planetTravel2.getAbsoluteVisitCount());

        planetTravel1.calculateAbsoluteVisitCount();
        planetTravel2.calculateAbsoluteVisitCount();

        System.out.println("1 Count:" + planetTravel1.getAbsoluteVisitCount());
        System.out.println("2 Count:" + planetTravel2.getAbsoluteVisitCount());
        System.out.println("*******************************************************************");
    }
}

