$(document).ready(function() {
    $('#login').blur(function() {
        var val1 = $("#login").val();
        var data = {login: val1};
        $.ajax({
            url: "rest/reg/checklogin",
            type:"POST",
            data: JSON.stringify(data),
            contentType:"application/json; charset=utf-8",
            dataType:"json",
            success: function(result){
                if ($.trim(result) == "true") {
                    $("#reg").attr("disabled", true);
                    $("#somediv").css('color', 'red');
                    $("#somediv").text("Login is busy, please choose another one!")
                } else {
                    $("#reg").attr("disabled", false);
                    $("#somediv").css('color', 'green');
                    $("#somediv").text("Login is ok!")
                }
            }
        })
    });
});