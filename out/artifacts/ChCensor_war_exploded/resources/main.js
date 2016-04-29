$(document).ready(function() {

    $('#crunchifyMessage').html("<h4>This message is coming from 'main.js' file...</h4>")

    $('#upload_form').submit(function (event) {
        $(this).ajaxSubmit({
            error: function (err) {
                console.log(err.message);
            },
            success: function (data) {
                debugger;
                console.log("--------");
                console.log(data);
            }
        });
        event.preventDefault();
    });

    /*
    $('#search_file_form').submit(function (event) {
        $(this).ajaxSubmit({
            error: function (err) {
                console.log(err.message);
            },
            success: function (data) {
                debugger;
                console.log("--------");
                console.log(data);
            }
        });
        event.preventDefault();
    });
    */
});

$(window).resize(function () {
    $('body').css('padding-top', parseInt($('#main-navbar').css("height"))+10);
});

$(window).load(function () {
    $('body').css('padding-top', parseInt($('#main-navbar').css("height"))+10);
});