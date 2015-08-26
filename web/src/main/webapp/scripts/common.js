var selectedImage = {};
var areaSelect = {};

function saveImage() {

    var imageTransfer = {
        image: btoa(selectedImage),
        cutLeft: areaSelect.selection.x1,
        cutTop: areaSelect.selection.y1,
        cutWidth: areaSelect.selection.width,
        cutHeight: areaSelect.selection.height,
        clientWidth: areaSelect.image.clientWidth,
        clientHeight: areaSelect.image.clientHeight
    };

    $.ajax({
       type: 'POST',
        url: 'api/common/saveImage',
        contentType: 'application/json',
        data: JSON.stringify(imageTransfer)
    });
}

function previewImage(input) {

    if (input.files && input.files[0]) {

        //var reader = new FileReader();
        var reader2 = new FileReader();

        //reader.onload = function (e) {
        //    $('#imgTest').attr('src', e.target.result);
        //};

        reader2.onload = function(e) {
            selectedImage = e.target.result;
            storeImage();
        };

        //reader.readAsDataURL(input.files[0]);
        reader2.readAsBinaryString(input.files[0]);
    }
}

function storeImage() {

    var imageTransfer = {
        image: btoa(selectedImage),
        cutLeft: 10,
        cutTop: 5,
        cutWidth: 55,
        cutHeight: 120
    };

    $.ajax({
        type: 'POST',
        url: 'api/common/storeImage',
        contentType: 'application/json',
        data: JSON.stringify(imageTransfer),
        success: function() {
            $('#imgTest')[0].reload();
        }
    });
};