$(document).ready(function () {
    
    function validateEmail() {
        var email = $("#email_login").val();
        var emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (email === "") {
            $(".email_login_err").text("Hãy điền địa chỉ email");
            return false;
        } else if (!emailRegex.test(email)) {
            $(".email_login_err").text("Định dạng email không hợp lệ");
            return false;
        } else {
            $(".email_login_err").text("");
            return true;
        }
    }
    
    function validatePassword() {
        var password = $("#password_login").val();
        if (password.length < 8) {
            $(".password_login_err").text("Mật khẩu phải có ít nhất 8 ký tự");
            return false;
        } else {
            $(".password_login_err").text("");
            return true;
        }
    }

    // Event listener for form submission
    $("#login-form").submit(function (event) {
        // Prevent default form submission
        event.preventDefault();

        // Perform individual validations
        var isEmailValid = validateEmail();
        var isPasswordValid = validatePassword();

        if (!isEmailValid || !isPasswordValid) {
            return;
        }

        var formData = {
            email: $("#email_login").val(),
            password: $("#password_login").val()
        };
        var myJSON = JSON.stringify(formData);
        $.ajax({
            url: "/v1/auth/login",
            type: "POST",
            contentType: "application/json; charset=utf-8",
            data: myJSON,
            success: function (data) {
                toastr.success("Đăng nhập thành công", "", { timeOut: 1000 });
                setTimeout(function() {
                    window.location.href = "/";
                }, 1000);
            },
            error: function (error) {
                toastr.error("Đăng nhập thất bại");
            }
        });
    });

    // Event listeners for input changes
    $("#email_login").on("input", validateEmail);
    $("#password_login").on("input", validatePassword);
});
