$(document).ready(function () {
    $.get("/profile/get_current_user", function (data) {
        console.log(data)
        $("#user_firstname").text(data.firstName);
    })

    $.get("/profile/get_all_car_by_user", function (data) {
        console.log(data[0])
        if (data === undefined || data.length == 0) {
            console.log("Пусто")
        } else {
            var list_card = document.getElementById("list_car");

            data.forEach(carData => { 
                var imageSrc = ``;
            
                if (carData.carImages === undefined || carData.carImages.length == 0) {
                    imageSrc = `images/default_car.jpg`
                } else {
                var imageSrc = `images/${carData.carImages[0].path}`;
                }

                var cardCar = document.createElement('a');
                cardCar.href = `/car/${carData.id}`;
                cardCar.classList.add('card_car');
                cardCar.innerHTML = `
      <div class="image_car">
        <img src="${imageSrc}">
      </div>
      <div class="title_car">${carData.title}</div>
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
    })
})

function logout() {
    $(location).attr("href", "/logout");
}