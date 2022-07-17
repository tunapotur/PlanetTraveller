package com;

public class UIPlanetTravel {
    private static final String PARAMETER_SEPARATOR = " ";
    private Planet[] planetList;
    private PlanetTravel planetTravel;
    private String[] inputLines;
    // Gezen Id'si ve bu gezegene bağlı gezegenlerin dizisini tutan dizi
    private int[][] exitPlanetInfoList;

    public UIPlanetTravel(String path) {
        this.inputLines = FileHelper.readAllLines(path).split("\n");
        // verilen sayıda gezegen için boş dizi hazırlanıyor
        this.planetList = new Planet[Integer.parseInt(this.inputLines[0])];
        this.exitPlanetInfoList = new int[this.planetList.length][];

        this.makeExitPlanetInfoList();
        this.setAllPlanets();
        this.planetTravel = new PlanetTravel(this.planetList);
        this.planetTravel.calculateAbsoluteVisitCount();
    }

    private void makeExitPlanetInfoList() {
        // exitPlanetInfoList dizisi oluşturuluyor
        for (int i = 0; i < this.planetList.length; i++) {
            String exitPlanetInfoText = this.inputLines[i + 1];
            String[] exitPlanetInfoList = exitPlanetInfoText.split(PARAMETER_SEPARATOR);
            int exitPlanetCount = Integer.parseInt(exitPlanetInfoList[0]);

            this.exitPlanetInfoList[i] = new int[exitPlanetCount];

            for (int j = 0; j < exitPlanetCount; j++)
                this.exitPlanetInfoList[i][j] = Integer.parseInt(exitPlanetInfoList[j + 1]);
        }
    }

    private void setAllPlanets() {
        for (int i = 0; i < this.planetList.length; i++)
            this.planetList[i] = new Planet(i, this.exitPlanetInfoList[i].length);


        for (int i = 0; i < this.planetList.length; i++)
            for (int j = 0; j < this.exitPlanetInfoList[i].length; j++)
                this.planetList[i].getExitPlanet()[j] = this.planetList[this.exitPlanetInfoList[i][j]];
    }

    public void printPlanetsExitInfo() {
        for (int i = 0; i < this.exitPlanetInfoList.length; i++) {
            System.out.print(i + "->");
            for (int j = 0; j < this.exitPlanetInfoList[i].length; j++)
                System.out.print(this.exitPlanetInfoList[i][j] + ",");
            System.out.println();
        }
    }

    public String planetListInfo() {
        return this.planetTravel.planetListInfo();
    }

    public int getAbsoluteVisitCount(){
        return this.planetTravel.getAbsoluteVisitCount();
    }
}
