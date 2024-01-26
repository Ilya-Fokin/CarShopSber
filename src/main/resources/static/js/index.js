$(document).ready(function () {
  
    loadAllCars();
    search_cars();
})

function loadAllCars() {
    $.get("/get_all_cars", function (data) {
        console.log(data)
        if (data === undefined || data.length == 0) {
            console.log("Пусто")
        } else {
            insertCars(data);
        }
    })
}

function insertCars(data) {
    var list_card = document.getElementById("list_car");
    list_card.innerHTML = '';

    data.forEach(carData => { 
        var imageSrc = ``;
    
        if (carData.carImages === undefined || carData.carImages.length == 0) {
            imageSrc = `images/default_car.jpg`
        } else {
        var imageSrc = `images/${carData.carImages[0].path}`;
        }

        var cardCar = document.createElement('button');
        cardCar.onclick = function() {
            console.log(carData.id);
    $.ajax({
        url: "/favourite/add",
        method: "POST",
        data: {
            id: carData.id
        },

        success: function (data) {
            console.log(data)
            var info = JSON.parse(data);
            console.log(info.message)
            $("#registration_error").text(info.message);
            $("#registration_error").css("display", "block");
        },

        error: function (data) {
            var info = JSON.parse(data.responseText);
            console.log(info.message)
            $("#registration_error").text(info.message);
            $("#registration_error").css("display", "block");
        }
    })
        };        
        cardCar.classList.add('card_car');
        cardCar.innerHTML = `
<div class="image_car">
<img src="${imageSrc}">
</div>
<div class="title_car">${carData.title} </div>
<div class="info_about_car">
<div>Производитель: ${carData.brand}</div>
<div>Модель: ${carData.model}</div>
<div>Год выпуска: ${carData.yearOfRelease}</div>
<div>Пробег: ${carData.mileage}</div>
<div>Трансмиссия: ${carData.transmission.transmission}</div>
</div>`;

        list_card.appendChild(cardCar);

    });
}

function addFavourite(id) {
    console.log(id)
    $.ajax({
        url: "/favourite/add",
        method: "POST",
        data: {
            id: id
        },

        success: function (data) {
             console.log(data)
        },

        error: function (error) {
        console.log(error)
        }
    })
}

function search_cars() {
    var myInput = document.getElementById("search_my_car");
    
    myInput.addEventListener("input", function() {
        var list_card = document.getElementById("list_car");
        list_card.innerHTML = '';

        console.log("Значение изменено: " + myInput.value);

        $.ajax({
            url: "/get_cars_by_title",
            method: "GET",
            data: {
                title: myInput.value
            },
    
            success: function (data) {
                 if (data === undefined || data == null) {
                    loadAllCars();
                 } else {
                    insertCars(data);
                 }
            },
    
            error: function (error) {
            console.log(error)
            }
        })
    });
}