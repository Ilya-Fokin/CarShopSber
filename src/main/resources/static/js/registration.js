function signUp() {
    $("#registration_error").css("display", "none");
    let firstname = $("#firstname").val();
    let secondname = $("#secondname").val();
    let username = $("#username").val();
    let mail = $("#mail").val();
    let password = $("#password").val();
    let repeat_password = $("#repeat_password").val();

    if (password != repeat_password) {
        $("#registration_error").text("Пароли должны совпадать");
        $("#registration_error").css("display", "block");
        return;
    }
    let number = $("#number").val();

    let user = JSON.stringify({
        'firstName' : firstname,
        'secondName' : secondname,
        'username' : username,
        'mail' : mail,
        'password' : password,
        'repeat_password' : repeat_password,
        'numberPhone' : number
    });

    console.log(user)

    $.ajax({
        url: "/sign_up",
        method: "POST",
        data: user,
        contentType: 'application/json',
        dataType: 'text',

        success: function (data) {
             $(location).attr("href", "/sign_in");
        },
        error: function (error) {
        var jsonError = JSON.parse(error.responseText);
        console.log(jsonError);
        var msg = "";
        if (typeof jsonError.violations === 'undefined') {
            msg = jsonError.message;
        } else {
            msg = jsonError.violations[0].message;
        }
        console.log(msg)
        $("#registration_error").text(msg);
        $("#registration_error").css("display", "block");
      
        }
    })
}

