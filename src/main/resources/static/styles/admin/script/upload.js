$('.dandev_insert_attach').click(function() {
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
        url: '/api/file/uploads', // Đường dẫn tải lên của bạn
        type: 'POST',
        data: formData,
        processData: false,
        contentType: false,
        success: function (response) {
            // Xử lý phản hồi từ máy chủ, nếu cần
            console.log('Upload successful: ', response);
            for (var i = 0; i < response.length; i++) {
                var imageUrl = response[i];
                var d = new Date();
                var _time = d.getTime();
                var _html = '<li id="li_files_' + _time + '">';
                _html += '<div class="img-wrap">';
                _html += '<span class="close" onclick="DelImg(this)">×</span>';
                _html += ' <div class="img-wrap-box"><img src="' + imageUrl + '"></div>';
                _html += '</div>';
                _html += '</li>';
                $('.dandev_attach_view').append(_html);
            }
        },
        error: function (xhr, status, error) {
            console.error('Upload failed: ', error);
        }
    });
}


function DelImg(el) {
    jQuery(el).closest('li').remove();

}