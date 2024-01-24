
    function callApi (type, url, data, succsessCallback, errorCallback, completeCallback) {

        var token = getCookie('JWT_TOKEN');
        $.ajax({
            type: type,
            url: url,
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            headers: {
                "Authorization": "Bearer " + token
            },
            success: succsessCallback,
            error: errorCallback,
            complete: completeCallback
        });

    }
    function getCookie(name) {
        var value = ";" + document.cookie;
        var parts = value.split("; " + name + "=");
        if (parts.length == 2) return parts.pop().split(";").shift();
    }