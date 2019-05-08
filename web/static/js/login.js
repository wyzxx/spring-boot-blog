$(document).ready(function () {



    // $('#login').on('click', function () {
    //     //登陆
    //     var user = $("#user").val();
    //     var passwd = $("#passwd").val();
    //     $.ajax(
    //         {
    //             type: "post",
    //             url: "http://localhost:8080/admin/login",
    //             data:{"user":user,"passwd":passwd},
    //             // async: true,
    //             success(result) {
    //                 console.log(result);
    //                 var url = "admin.html#"+result;
    //                 alert(url);
    //                 // if (result == true) {
    //                     window.location.href = url;
    //                     return;
    //                 // }
    //                 // console.log(result);
    //             }
    //         });
    // });


    $('#login').on('click', function () {
        //登陆
        var user = $("#user").val();
        var passwd = $("#passwd").val();
        $.ajax(
            {
                type: "post",
                url: "http://localhost:8080/admin/login",
                xhrFields: { withCredentials: true },
                data: { "user": user, "passwd": passwd },
                // async: true,
                success(result) {
                    // alert(result);
                    // console.log($.cookie('JSESSIONID', result));
                    window.location.href = "admin.html";
                    return false;
                }
            });
    });




})