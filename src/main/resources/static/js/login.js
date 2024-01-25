function signIn() {
    let form = $("#sign_in_form");
    let user = form.serialize();

    console.log(user);

    $.ajax({
        url: "/login",
        method: "POST",
        data: user,

        success: function (url) {
            $(location).attr("href", url);
        },

        error: function (error) {
        console.log(error)
        $("#registration_error").text(error);
        $("#registration_error").css("display", "block");
      
        }
    })
}