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
                    console.log(data);
                },
                error: function(xhr, textStatus, errorThrown) {

                }
            });
        }

        return false;
    });

    $.fn.datepicker.defaults.format = "yyyy-mm-dd";
});
