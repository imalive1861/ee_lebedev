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
                    $("#errorlogin").css('color', 'red');
                    $("#errorlogin").text("Login is busy, please choose another one!")
                } else {
                    $("#reg").attr("disabled", false);
                    $("#errorlogin").css('color', 'green');
                    $("#errorlogin").text("Login is ok!")
                }
            }
        })
    });
});