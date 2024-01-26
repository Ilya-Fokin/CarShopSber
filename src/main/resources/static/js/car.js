$(document).ready(function () {
    $.get("/add_car/get_types", function (data) {
        console.log(data)
        var select = document.getElementById("type_select")
        for (let elem of data) {
            set_option(elem.type, select)
        }
    })

    $.get("/add_car/get_transmissions", function (data) {
        console.log(data)
        var select = document.getElementById("type_transmission")
        for (let elem of data) {
            set_option(elem.transmission, select)
        }
    })

    $.get("/add_car/get_wheel_drive", function (data) {
        console.log(data)
        var select = document.getElementById("type_wheelDrive")
        for (let elem of data) {
            set_option(elem.wheelDrive, select)
        }
    })
})

function set_option(type, select) {
    var option = document.createElement('option');
    option.text = type;
    option.value = type;
    select.appendChild(option);
}

function addCar() {
    var title = document.getElementById("title").value;
    var description = document.getElementById("description").value;
    var transmission = document.getElementById("type_transmission").value;
    var wheelDrive = document.getElementById("type_wheelDrive").value;
    var type = document.getElementById("type_select").value;
    var brand = document.getElementById("brand").value;
    var model = document.getElementById("model").value;
    var yearOfRelease = document.getElementById("yearOfRelease").value;
    var color = document.getElementById("color").value;
    var mileage = document.getElementById("mileage").value;
    var price = document.getElementById("price").value;

        var carImages = [];
        
        for(var i = 0; i < $("#carImages").get(0).files.length; ++i) { 
            carImages.push($("#carImages").get(0).files[i].name);
        }

    var car = JSON.stringify({
        'title' : title,
        'description' : description,
        'transmission' : transmission,
        'wheelDrive' : wheelDrive,
        'type' : type,
        'brand' : brand,
        'model' : model,
        'yearOfRelease' : yearOfRelease,
        'color' : color,
        'mileage' : mileage,
        'transmission' : transmission,
        'price' : price,
        'carImages' : carImages
    });

    console.log(car);

    $.ajax({
        url: "/add_car",
        method: "POST",
        data: car,
        contentType: 'application/json',
        dataType: 'text',

        success: function (data) {
             $(location).attr("href", "/profile");
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
        $("#registration_error").text(msg);
        $("#registration_error").css("display", "block");
        console.log(msg)
        }
    })
}

