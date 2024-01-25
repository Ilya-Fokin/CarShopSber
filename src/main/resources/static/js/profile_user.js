$(document).ready(function () {
    $.get("/profile/get_current_user", function (data) {
        $("#user_firstname").text(data.firstName);

    })
})