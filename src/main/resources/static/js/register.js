$(document).ready(function () {
    // Function to validate name
    function validateName() {
        var name = $("#user_register").val();
        if (name === "") {
            $(".user_register_err").text("Hãy điền họ tên");
            return false;
        } else {
            $(".user_register_err").text("");
            return true;
        }
    }

    // Function to validate account
    function validateAccount() {
        var account = $("#account_register").val();
        if (account === "") {
            $(".account_register_err").text("Hãy điền tên tài khoản");
            return false;
        } else {
            $(".account_register_err").text("");
            return true;
        }
    }

    // Function to validate email format
    function validateEmail() {
        var email = $("#email_register").val();
        var emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (email === "") {
            $(".email_register_err").text("Hãy điền địa chỉ email");
            return false;
        } else if (!emailRegex.test(email)) {
            $(".email_register_err").text("Định dạng email không hợp lệ");
            return false;
        } else {
            $(".email_register_err").text("");
            return true;
        }
    }

    // Function to validate password length
    function validatePassword() {
        var password = $("#password_register").val();
        if (password.length < 8) {
            $(".password_register_err").text("Mật khẩu phải có ít nhất 8 ký tự");
            return false;
        } else {
            $(".password_register_err").text("");
            return true;
        }
    }

    // Event listener for form submission
    $("#register-form").submit(function (event) {
        // Prevent default form submission
        event.preventDefault();

        // Perform individual validations
        var isNameValid = validateName();
        var isAccountValid = validateAccount();
        var isEmailValid = validateEmail();
        var isPasswordValid = validatePassword();

        if (!isNameValid || !isAccountValid || !isEmailValid || !isPasswordValid) {
            return;
        }

        // Create data object to be sent to the server
        var formData = {
            name: $("#user_register").val(),
            accountName: $("#account_register").val(),
            email: $("#email_register").val(),
            password: $("#password_register").val()
        };
        var myJSON = JSON.stringify(formData);
        // Make an AJAX request to the server
        $.ajax({
            url: "/v1/auth/register",
            type: "POST",
            contentType: "application/json; charset=utf-8",
            data: myJSON,
            success: function (data) {
                toastr.success("Đăng ký thành công", "", { timeOut: 2000 });
                setTimeout(function() {
                    window.location.href = "/login";
                }, 2000);
            },
            error: function (error) {
                toastr.error("Đăng ký thất bại");
            }
        });
    });

    // Event listeners for input changes
    $("#user_register").on("input", validateName);
    $("#account_register").on("input", validateAccount);
    $("#email_register").on("input", validateEmail);
    $("#password_register").on("input", validatePassword);
});
