$(document).ready(function() {
    let check1 = false;
    let check2 = false;
    let check3 = false;
    $('#login').blur(function() {
        var login = $("#login").val();
        var data = {login};
        $.ajax({
            url: "rest/reg/loginValidation",
            type:"POST",
            data: JSON.stringify(data),
            contentType:"application/json; charset=utf-8",
            dataType:"text",
            success: function(result){
                check1 = true;
                $("#errorlogin").css('color', 'green');
                $("#errorlogin").text(result)
                check();
            },
            error: function(request){
                check1 = false;
                $("#errorlogin").css('color', 'red');
                $("#errorlogin").text(request.responseText)
                check();
            }
        })
    });
    $('#newPassword').blur(function() {
        var password = $("#newPassword").val();
        var data = {password};
        $.ajax({
            url: "rest/reg/passwordValidation",
            type:"POST",
            data: JSON.stringify(data),
            contentType:"application/json; charset=utf-8",
            dataType:"text",
            success: function(result){
                check2 = true;
                $("#errorpassword").css('color', 'green');
                $("#errorpassword").text(result)
                equalsPassword();
                check();
            },
            error: function(request){
                check2 = false;
                $("#errorpassword").css('color', 'red');
                $("#errorpassword").text(request.responseText)
                equalsPassword();
                check();
            }
        })
    });
    $('#confirmPassword').blur(function() {
        equalsPassword();
        check();
    });
    function equalsPassword() {
        var newPassword = document.getElementById("newPassword");
        if (newPassword.value != document.getElementById("confirmPassword").value) {
            check3 = false;
            $("#errorConfirmPassword").css('color', 'red');
            $("#errorConfirmPassword").text("Passwords do not match!")
        } else {
            check3 = true;
            $("#errorConfirmPassword").css('color', 'green');
            $("#errorConfirmPassword").text("Passwords ok! ^_^")
        }
    }
    function check() {
        if (check1 == true && check2 == true && check3 == true) {
            $("#reg").attr("disabled", false);
        } else {
            $("#reg").attr("disabled", true);
        }
    }
});