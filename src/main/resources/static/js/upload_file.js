$('.dandev_insert_attach').click(function () {
    if ($('.list_attach').hasClass('show-btn') === false) {
        $('.list_attach').addClass('show-btn');
    }
    var _lastimg = jQuery('.dandev_attach_view li').last().find('input[type="file"]').val();

    if (_lastimg != '') {
        var d = new Date();
        var _time = d.getTime();
        var _html = '<li id="li_files_' + _time + '" class="li_file_hide">';
        _html += '<div class="img-wrap">';
        _html += '<span class="close" onclick="DelImg(this)">×</span>';
        _html += ' <div class="img-wrap-box"></div>';
        _html += '</div>';
        _html += '<div class="' + _time + '">';
        _html += '<input type="file" class="hidden"  onchange="uploadImg(this)" id="files_' + _time + '"   />';
        _html += '</div>';
        _html += '</li>';
        jQuery('.dandev_attach_view').append(_html);
        jQuery('.dandev_attach_view li').last().find('input[type="file"]').click();
    } else {
        if (_lastimg == '') {
            jQuery('.dandev_attach_view li').last().find('input[type="file"]').click();
        } else {
            if ($('.list_attach').hasClass('show-btn') === true) {
                $('.list_attach').removeClass('show-btn');
            }
        }
    }
});

function uploadImg(el) {
    var file_data = $(el).prop('files')[0];
    var formData = new FormData();
    formData.append('files', file_data);

    $.ajax({
        url: '/api/file/uploads',
        type: 'POST',
        data: formData,
        contentType: false,
        processData: false,
        success: function(response) {
            displayUploadedImg(el, response); // Sửa thành imageUrls
        },
        error: function(xhr, status, error) {
            console.error("Có lỗi xảy ra khi tải ảnh lên: ", error);
        }
    });
}

function displayUploadedImg(el, imageUrls) {
    var _li = $(el).closest('li');
    if (_li.hasClass('li_file_hide')) {
        _li.removeClass('li_file_hide');
    }

    var imgWrapBox = _li.find('.img-wrap-box');
    // Xóa tất cả ảnh hiện tại trong imgWrapBox (nếu có)
    imgWrapBox.empty();

    // Kiểm tra xem imageUrls có được định nghĩa và là một mảng không
    if (Array.isArray(imageUrls)) {
        // Duyệt qua mảng các URL và hiển thị ảnh
        imageUrls.forEach(function (imageUrl) {
            var newImage = document.createElement('img');
            newImage.onload = function () {
                // Ảnh đã tải thành công
                imgWrapBox.append(newImage);
            };
            newImage.onerror = function () {
                // Xử lý khi có lỗi tải ảnh
                console.error('Có lỗi khi tải ảnh.');
            };
            newImage.src = imageUrl;
        });
    } else {
        console.error('imageUrls không phải là một mảng hợp lệ.');
    }
}


function DelImg(el) {
    jQuery(el).closest('li').remove();
}
