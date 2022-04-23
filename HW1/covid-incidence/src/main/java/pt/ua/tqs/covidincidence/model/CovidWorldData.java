package pt.ua.tqs.covidincidence.model;

import java.util.Objects;

public class CovidWorldData {
    private int totalCases;
    private int newCases;
    private int activeCases;
    private int recovered;
    private int newRecovered;
    private double recoveredPercentage;
    private double casesPerOneMil;
    private int totalDeaths;
    private int newDeaths;
    private double deathsPerOneMil;

    public CovidWorldData() {}

    public CovidWorldData(int totalCases, int newCases, int activeCases, int recovered, int newRecovered, double recoveredPercentage, double casesPerOneMil, int totalDeaths, int newDeaths, double deathsPerOneMil) {
        this.totalCases = totalCases;
        this.newCases = newCases;
        this.activeCases = activeCases;
        this.recovered = recovered;
        this.newRecovered = newRecovered;
        this.recoveredPercentage = recoveredPercentage;
        this.casesPerOneMil = casesPerOneMil;
        this.totalDeaths = totalDeaths;
        this.newDeaths = newDeaths;
        this.deathsPerOneMil = deathsPerOneMil;
    }

    public int getTotalCases() {
        return totalCases;
    }

    public void setTotalCases(int totalCases) {
        this.totalCases = totalCases;
    }

    public int getNewCases() {
        return newCases;
    }

    public void setNewCases(int newCases) {
        this.newCases = newCases;
    }

    public int getActiveCases() {
        return activeCases;
    }

    public void setActiveCases(int activeCases) {
        this.activeCases = activeCases;
    }

    public int getRecovered() {
        return recovered;
    }

    public void setRecovered(int recovered) {
        this.recovered = recovered;
    }

    public int getNewRecovered() {
        return newRecovered;
    }

    public void setNewRecovered(int newRecovered) {
        this.newRecovered = newRecovered;
    }

    public double getRecoveredPercentage() {
        return recoveredPercentage;
    }

    public void setRecoveredPercentage(double recoveredPercentage) {
        this.recoveredPercentage = recoveredPercentage;
    }

    public double getCasesPerOneMil() {
        return casesPerOneMil;
    }

    public void setCasesPerOneMil(double casesPerOneMil) {
        this.casesPerOneMil = casesPerOneMil;
    }

    public int getTotalDeaths() {
        return totalDeaths;
    }

    public void setTotalDeaths(int totalDeaths) {
        this.totalDeaths = totalDeaths;
    }

    public int getNewDeaths() {
        return newDeaths;
    }

    public void setNewDeaths(int newDeaths) {
        this.newDeaths = newDeaths;
    }

    public double getDeathsPerOneMil() {
        return deathsPerOneMil;
    }

    public void setDeathsPerOneMil(double deathsPerOneMil) {
        this.deathsPerOneMil = deathsPerOneMil;
    }

    @Override
    public String toString() {
        return "CovidWorldData{" +
                "totalCases=" + totalCases +
                ", newCases=" + newCases +
                ", activeCases=" + activeCases +
                ", recovered=" + recovered +
                ", newRecovered=" + newRecovered +
                ", recoveredPercentage=" + recoveredPercentage +
                ", casesPerOneMil=" + casesPerOneMil +
                ", totalDeaths=" + totalDeaths +
                ", newDeaths=" + newDeaths +
                ", deathsPerOneMil=" + deathsPerOneMil +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CovidWorldData that = (CovidWorldData) o;
        return totalCases == that.totalCases && newCases == that.newCases && activeCases == that.activeCases && recovered == that.recovered && newRecovered == that.newRecovered && Double.compare(that.recoveredPercentage, recoveredPercentage) == 0 && Double.compare(that.casesPerOneMil, casesPerOneMil) == 0 && totalDeaths == that.totalDeaths && newDeaths == that.newDeaths && Double.compare(that.deathsPerOneMil, deathsPerOneMil) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(totalCases, newCases, activeCases, recovered, newRecovered, recoveredPercentage, casesPerOneMil, totalDeaths, newDeaths, deathsPerOneMil);
    }
}
