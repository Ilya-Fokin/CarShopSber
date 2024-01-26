$(document).ready(function () {
    $.get("/profile/get_current_user", function (data) {
        console.log(data)
        $("#user_firstname").text(data.firstName);
    })

    loadMyCars();

})

function loadMyCars() {
    $.get("/profile/get_all_car_by_user", function (data) {
        console.log(data[0])
        if (data === undefined || data.length == 0) {
            var list_card = document.getElementById("list_car");
            list_card.innerHTML = '';
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
        url: "/car/drop",
        method: "POST",
        data: {
            id: carData.id
        },

        success: function (data) {
            loadMyCars();
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
      <div class="title_car">${carData.brand} ${carData.model}</div>
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

function addCar() {
    $(location).attr("href", "/add_car");
}

function logout() {
    $(location).attr("href", "/logout");
}
