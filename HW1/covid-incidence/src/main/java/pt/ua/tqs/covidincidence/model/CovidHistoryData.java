package pt.ua.tqs.covidincidence.model;

import java.util.Objects;

public class CovidHistoryData {
    private String country;
    private String day;
    private String newCases;
    private int activeCases;
    private int criticalCases;
    private int recovered;
    private int casesPerOneMil;
    private int totalCases;
    private String newDeaths;
    private int deathsPerOneMil;
    private int totalDeaths;
    private int testsPerOneMil;
    private int totalTests;

    public CovidHistoryData() {}

    public CovidHistoryData(String country, String day) {
        this.country = country;
        this.day = day;
        this.newCases = "N/A";
        this.activeCases = -1;
        this.criticalCases = -1;
        this.recovered = -1;
        this.casesPerOneMil = -1;
        this.totalCases = -1;
        this.newDeaths = "N/A";
        this.deathsPerOneMil = -1;
        this.totalDeaths = -1;
        this.testsPerOneMil = -1;
        this.totalTests = -1;
    }

    public CovidHistoryData(String country, String day, String newCases, int activeCases, int criticalCases, int recovered, int casesPerOneMil, int totalCases, String newDeaths, int deathsPerOneMil, int totalDeaths, int testsPerOneMil, int totalTests) {
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

    public String getNewCases() {
        return newCases;
    }

    public void setNewCases(String newCases) {
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

    public String getNewDeaths() {
        return newDeaths;
    }

    public void setNewDeaths(String newDeaths) {
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
        CovidHistoryData that = (CovidHistoryData) o;
        return activeCases == that.activeCases && criticalCases == that.criticalCases && recovered == that.recovered && casesPerOneMil == that.casesPerOneMil && totalCases == that.totalCases && deathsPerOneMil == that.deathsPerOneMil && totalDeaths == that.totalDeaths && testsPerOneMil == that.testsPerOneMil && totalTests == that.totalTests && Objects.equals(country, that.country) && Objects.equals(day, that.day) && Objects.equals(newCases, that.newCases) && Objects.equals(newDeaths, that.newDeaths);
    }

    @Override
    public int hashCode() {
        return Objects.hash(country, day, newCases, activeCases, criticalCases, recovered, casesPerOneMil, totalCases, newDeaths, deathsPerOneMil, totalDeaths, testsPerOneMil, totalTests);
    }
}
