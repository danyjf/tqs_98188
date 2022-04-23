$(document).ready(function() {
    var url = `http://localhost:8080/covid/world`;
    
    $.ajax({
        url: url,
        type: "GET",
        dataType: "json",
        success: function(data, textStatus, xhr) {
            $("#totalCases").text(data["totalCases"]);
            $("#newCases").text(data["newCases"] > 0 ? "+" + data["newCases"] : "-" + data["newCases"]); 
            $("#activeCases").text(data["activeCases"]);
            $("#recovered").text(data["recovered"]);
            $("#newRecovered").text(data["newRecovered"] > 0 ? "+" + data["newRecovered"] : "-" + data["newRecovered"]);
            $("#recoveredPercentage").text(data["recoveredPercentage"] + "%");
            $("#casesPer1Mil").text(data["casesPerOneMil"]);
            
            $("#totalDeaths").text(data["totalDeaths"]);
            $("#newDeaths").text(data["newDeaths"] > 0 ? "+" + data["newDeaths"] : "-" + data["newDeaths"]);
            $("#deathsPer1Mil").text(data["deathsPerOneMil"]);
        },
    });
});
