$(document).ready(function () {

    $("#search-form").submit(function (event) {
        event.preventDefault();
        fire_ajax_submit();
    });

});

function fire_ajax_submit() {

    var search = {}
    search["application"] = $("#application").val();
    search["service"]     = $("#service").val();
    search["requestor"]   = $("#requestor").val();
    search["domain"]      = $("#domain").val();
    search["remedy"]    = $("#remedy").val();
    search["comments"]    = $("#comments").val();
    
    
    //search["email"] = $("#email").val();

    $("#btn-search").prop("disabled", true);

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "${home}service/addService",
        data: JSON.stringify(search),
        /*dataType: 'json',*/
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {
            var json =  "<pre>"+JSON.stringify(data, null, 4) + "</pre>";
            $('#feedback').html(json);
            console.log("SUCCESS : ", data);
            $("#btn-search").prop("disabled", false);

        },
        error: function (e) {
            var json = "<pre>"+ e.responseText + "</pre>";            
            $('#feedback').html(json);
            console.log("ERROR : ", e);
            $("#btn-search").prop("disabled", false);

        }
    });

}