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
    $.ajax({
        url: '/admin/cinema/editCinema/showLogoPath/' + window.cinemaId,
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

    $.ajax({
        url: '/admin/cinema/editCinema/editLogoPath/' + window.cinemaId,
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
    $.ajax({
        url: '/admin/cinema/editCinema/deleteLogoPath/' + window.cinemaId,
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
    $.ajax({
        url: '/admin/cinema/editCinema/showTopBanner/' + window.cinemaId,
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

    $.ajax({
        url: '/admin/cinema/editCinema/editTopBanner/' + window.cinemaId,
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
    $.ajax({
        url: '/admin/cinema/editCinema/deleteTopBanner/' + window.cinemaId,
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
    $.ajax({
        url: '/admin/cinema/editCinema/showAllImages/' + window.cinemaId,
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
    $.ajax({
        url: '/admin/cinema/editCinema/createNewImage/' + window.cinemaId,
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

    $.ajax({
        url: '/admin/cinema/editCinema/editImageCinema/' + id,
        method: 'POST',
        data: formData,
        processData: false,
        contentType: false,
        success: function (result) {
            console.log("Выгрузка файла прошла успешно");
            $.ajax({
                url: '/admin/cinema/editCinema/getImage/' + id,
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
    $.ajax({
        url: '/admin/cinema/editCinema/deleteImage/' + id,
        method: 'GET',
        dataType: 'JSON',
        success: function (result) {
            console.log(result);
        }
    })
}
