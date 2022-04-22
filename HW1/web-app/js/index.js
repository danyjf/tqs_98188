$(document).ready(function() {
    $("#target").submit(function(event) {
        var country = $("#country").val();
        var date = $("#date").val();
        
        if(country != "" && date != "") {
            var url = `http://localhost:8080/covid/${country}/${date}`;
            
            $.ajax({
                url: url,
                type: "GET",
                dataType: "json",
                success: function(data, textStatus, xhr) {
                    $("#totalCases").text(data["totalCases"] != -1 ? data["totalCases"] : "N/A")
                    $("#newCases").text(data["newCases"])
                    $("#activeCases").text(data["activeCases"] != -1 ? data["activeCases"] : "N/A")
                    $("#critical").text(data["criticalCases"] != -1 ? data["criticalCases"] : "N/A")
                    $("#recovered").text(data["recovered"] != -1 ? data["recovered"] : "N/A")
                    $("#casesPer1Mil").text(data["casesPerOneMil"] != -1 ? data["casesPerOneMil"] : "N/A")
                    
                    $("#totalDeaths").text(data["totalDeaths"] != -1 ? data["totalDeaths"] : "N/A")
                    $("#newDeaths").text(data["newDeaths"])
                    $("#deathsPer1Mil").text(data["deathsPerOneMil"] != -1 ? data["deathsPerOneMil"] : "N/A")

                    $("#totalTests").text(data["totalTests"] != -1 ? data["totalTests"] : "N/A")
                    $("#testsPer1Mil").text(data["testsPerOneMil"] != -1 ? data["testsPerOneMil"] : "N/A")
                },
            });
        }

        return false;
    });

    $.fn.datepicker.defaults.format = "yyyy-mm-dd";
});
