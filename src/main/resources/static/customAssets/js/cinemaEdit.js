let baseUrl = window.location.href;
$(document).ready(function () {
    showAllImages();
    showLogo();
    showTopBanner();
    const textarea = document.querySelectorAll('[data-autosize]');
    autosize(textarea);
    console.log("CinemaEdit.js loaded")

});
// Logo
showLogo = () => {
    let url = baseUrl + '/showLogoPath/' + window.cinemaId;
    $.ajax({
        url: url,
        method: 'GET',
        dataType: "Text",
        success: function (result) {
            console.log("Лого должно успешно отобразиться")
            $('<img src="' +
                (result == null || result === "" ? 'https://via.placeholder.com/250x200&text=Empty' : result)
                + '"' +
                ' class="img-fluid"\n' +
                ' id="logoImage" \n' +
                ' style="max-width: 250px;' +
                ' max-height: 200px;">').appendTo('#logoImageBlock');
        },
        error: function (result) {

            console.log("Лого не должно отобразиться", result)
        }
    })
}

function changeLogo(element) {
    let file = element.files[0];
    let formData = new FormData();
    formData.append("file", file);
    let url = baseUrl + '/editLogoPath/' + window.cinemaId
    $.ajax({
        url: url,
        method: 'POST',
        data: formData,
        processData: false,
        contentType: false,
        success: function (result) {
            console.log("Изменение лого успешно");
            let nearestImg = $(element).closest('#logoBlock').find('#logoImage');
            nearestImg.remove();
            showLogo();
        },
        error: function (error) {
            console.error("Ошибка при загрузке лого:", error);
        }
    });
}

function deleteLogo(element) {
    let nearestImg = $(element).closest('#logoBlock').find('#logoImage');
    nearestImg.remove();
    let url = baseUrl + '/deleteLogoPath/' + window.cinemaId
        $.ajax({

        url: url,
        method: 'POST',
        processData: false,
        contentType: false,
        success: function (result) {
            showLogo();
            console.log("Удаление лого прошло успешно", result);
        },
        error: function () {
            console.log("Ошибка удаления лого")
        }
    })
}

// TopBanner

showTopBanner = () => {
    let url = baseUrl + '/showTopBanner/'
    $.ajax({
        url: url,
        method: 'GET',
        dataType: 'Text',
        success: function (result) {
            console.log("Лого должно успешно отобразиться")
            $('<img src="' +
                (result == null || result === "" ? 'https://via.placeholder.com/250x200&text=Empty' : result)
                + '"' +
                ' class="img-fluid"\n' +
                ' id="topBannerImage" \n' +
                ' style="max-width: 250px;' +
                ' max-height: 200px;">').appendTo('#topBannerImageBlock');
        },
        error: function () {
            console.log('error logo');
        }
    })
}

function changeTopBanner(element) {
    let file = element.files[0];
    let formData = new FormData();
    formData.append("file", file);
    let url = baseUrl + '/editTopBanner/' + window.cinemaId
    $.ajax({
        url: url,
        method: 'POST',
        data: formData,
        processData: false,
        contentType: false,
        success: function (result) {
            console.log("Изменение лого успешно");
            let nearestImg = $(element).closest('#topBannerBlock').find('#topBannerImage');
            nearestImg.remove();
            showTopBanner();
        },
        error: function (error) {
            console.error("Ошибка при загрузке лого:", error);
        }
    });
}

function deleteTopBanner(element) {
    let nearestImg = $(element).closest('#topBannerBlock').find('#topBannerImage');
    nearestImg.remove();
    let url = baseUrl + '/deleteTopBanner/' + window.cinemaId
    $.ajax({
        url: url,
        method: 'POST',
        processData: false,
        contentType: false,
        success: function (result) {
            showTopBanner();
            console.log("Удаление лого прошло успешно", result);
        },
        error: function () {
            console.log("Ошибка удаления лого")
        }
    })
}

showAllImages = () => {
    let url = baseUrl + '/showAllImages/' + window.cinemaId
        $.ajax({
        url: url,
        method: 'GET',
        dataType: 'JSON',
        success: function (result) {
            console.log("All image");
            drawImages(result)
        },
        error: function (error) {
            console.log("Картинки не отображается");
        }
    })
}

function drawImages(result) {
    for (const data of result) {
        $('<div class="col-3">' +
            '<div class="d-flex flex-column" style="max-width: 100%;">' +
            '<img src="' + (data.url != null ? data.url : 'https://via.placeholder.com/250x200&text=Empty') + '"' +
            ' id="imagePage' + data.id + '" ' +
            'class="img-fluid mb-2" style="max-width: 250px; max-height: 200px;">' +
            '<label class="form-label" id="imagePageEdit' + data.id + '" style="max-width: 250px;">' +
            '<input type="file" ' +
            'class="form-control" ' +
            'accept="image/png, image/jpeg" onchange="editImagePage(this,' + data.id + ')" multiple/>' +
            '</label>' +
            '<label class="form-label" style="max-width: 250px;">' +
            '<button style="width: 250px;" ' +
            'type="button" ' +
            'class="btn btn-danger" ' +
            'onclick="removeBlock(this, ' + data.id + ' )">' +
            'Удалить' +
            '</button>' +
            '</label>' +
            '</div>' +
            '</div>').appendTo('#gallery-image');

    }
}

const addBlock = () => {
    let index;
    let url = baseUrl + '/createNewImage/' + window.cinemaId
    $.ajax({
        url: url,
        method: 'GET',
        dataType: 'JSON',
        success: function (result) {
            console.log(result)
            $('<div class="col-3">' +
                '<div class="d-flex flex-column" style="max-width: 100%;">' +
                '<img src="https://via.placeholder.com/250x200.png?text=Нет+Изображения"' +
                ' id="imagePage"' + result.id + ' ' +
                'class="img-fluid mb-2" style="max-width: 250px; max-height: 200px;">' +
                '<label class="form-label"  id="imagePageEdit' + result.id + '" style="max-width: 250px;">' +
                '<input type="file" ' +
                'class="form-control"' +
                'accept="image/png, image/jpeg" onchange="editImagePage(this,' + result.id + ')" multiple/>' +
                '</label>' +
                '<label class="form-label" style="max-width: 250px;">' +
                '<button style="width: 250px;" ' +
                'type="button" ' +
                'class="btn btn-danger" ' +
                'onclick="removeBlock(this, ' + result.id + ' )">' +
                'Удалить' +
                '</button>' +
                '</label>' +
                '</div>' +
                '</div>').appendTo('#gallery-image');
        }
    })
}
document.querySelector('#add-image-button').addEventListener('click', addBlock);

// Изменяем картинку
function editImagePage(element, id) {
    let file = element.files[0];
    let formData = new FormData();
    formData.append("file", file);
    let url = baseUrl + '/editImageCinema/' + id
    $.ajax({
        url: url,
        method: 'POST',
        data: formData,
        processData: false,
        contentType: false,
        success: function (result) {
            console.log("Выгрузка файла прошла успешно");
            let url2 = baseUrl + '/getImage/' + id
            $.ajax({
                url: url2,
                method: 'GET',
                success: function (result) {
                    console.log("Загрузка файла прошла успешно");
                    let block = $(element).closest('.col-3').find('img');
                    $('<img src="' + result.url + '" ' +
                        'id="imagePage' + result.id + '" ' +
                        'class="img-fluid mb-2" ' +
                        'style="max-width: 250px; max-height: 200px;">')
                        .insertBefore($("#imagePageEdit" + result.id));
                    block.remove();
                },
                error: function (error) {
                    console.error("Ошибка при загрузке файла:", error);
                }
            });
        },
        error: function (error) {
            console.error("Ошибка при загрузке файла:", error);
        }
    });
}

function removeBlock(element, id) {
    let block = element.closest('.col-3');
    block.remove();
    let url = baseUrl + '/deleteImage/' + id
    $.ajax({
        url: url,
        method: 'GET',
        dataType: 'JSON',
        success: function (result) {
            console.log(result);
        }
    })
}
