package pt.ua.tqs.covidincidence.model;

import java.util.Objects;

public class CovidStats {
    private String country;
    private String day;
    private int newCases;
    private int activeCases;
    private int criticalCases;
    private int recovered;
    private int casesPerOneMil;
    private int totalCases;
    private int newDeaths;
    private int deathsPerOneMil;
    private int totalDeaths;
    private int testsPerOneMil;
    private int totalTests;

    public CovidStats(String country, String day, int newCases, int activeCases, int criticalCases, int recovered, int casesPerOneMil, int totalCases, int newDeaths, int deathsPerOneMil, int totalDeaths, int testsPerOneMil, int totalTests) {
        this.country = country;
        this.day = day;
        this.newCases = newCases;
        this.activeCases = activeCases;
        this.criticalCases = criticalCases;
        this.recovered = recovered;
        this.casesPerOneMil = casesPerOneMil;
        this.totalCases = totalCases;
        this.newDeaths = newDeaths;
        this.deathsPerOneMil = deathsPerOneMil;
        this.totalDeaths = totalDeaths;
        this.testsPerOneMil = testsPerOneMil;
        this.totalTests = totalTests;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
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

    public int getCriticalCases() {
        return criticalCases;
    }

    public void setCriticalCases(int criticalCases) {
        this.criticalCases = criticalCases;
    }

    public int getRecovered() {
        return recovered;
    }

    public void setRecovered(int recovered) {
        this.recovered = recovered;
    }

    public int getCasesPerOneMil() {
        return casesPerOneMil;
    }

    public void setCasesPerOneMil(int casesPerOneMil) {
        this.casesPerOneMil = casesPerOneMil;
    }

    public int getTotalCases() {
        return totalCases;
    }

    public void setTotalCases(int totalCases) {
        this.totalCases = totalCases;
    }

    public int getNewDeaths() {
        return newDeaths;
    }

    public void setNewDeaths(int newDeaths) {
        this.newDeaths = newDeaths;
    }

    public int getDeathsPerOneMil() {
        return deathsPerOneMil;
    }

    public void setDeathsPerOneMil(int deathsPerOneMil) {
        this.deathsPerOneMil = deathsPerOneMil;
    }

    public int getTotalDeaths() {
        return totalDeaths;
    }

    public void setTotalDeaths(int totalDeaths) {
        this.totalDeaths = totalDeaths;
    }

    public int getTestsPerOneMil() {
        return testsPerOneMil;
    }

    public void setTestsPerOneMil(int testsPerOneMil) {
        this.testsPerOneMil = testsPerOneMil;
    }

    public int getTotalTests() {
        return totalTests;
    }

    public void setTotalTests(int totalTests) {
        this.totalTests = totalTests;
    }

    @Override
    public String toString() {
        return "CovidStats{" +
                "country='" + country + '\'' +
                ", day='" + day + '\'' +
                ", newCases=" + newCases +
                ", activeCases=" + activeCases +
                ", criticalCases=" + criticalCases +
                ", recovered=" + recovered +
                ", casesPerOneMil=" + casesPerOneMil +
                ", totalCases=" + totalCases +
                ", newDeaths=" + newDeaths +
                ", deathsPerOneMil=" + deathsPerOneMil +
                ", totalDeaths=" + totalDeaths +
                ", testsPerOneMil=" + testsPerOneMil +
                ", totalTests=" + totalTests +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CovidStats that = (CovidStats) o;
        return newCases == that.newCases && activeCases == that.activeCases && criticalCases == that.criticalCases && recovered == that.recovered && casesPerOneMil == that.casesPerOneMil && totalCases == that.totalCases && newDeaths == that.newDeaths && deathsPerOneMil == that.deathsPerOneMil && totalDeaths == that.totalDeaths && testsPerOneMil == that.testsPerOneMil && totalTests == that.totalTests && Objects.equals(country, that.country) && Objects.equals(day, that.day);
    }

    @Override
    public int hashCode() {
        return Objects.hash(country, day, newCases, activeCases, criticalCases, recovered, casesPerOneMil, totalCases, newDeaths, deathsPerOneMil, totalDeaths, testsPerOneMil, totalTests);
    }
}
