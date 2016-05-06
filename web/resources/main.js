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

    $('#search_file_form').submit(function (event) {
        $(this).ajaxSubmit({
            error: function (err) {
                console.log(err.message);
                $('#search_file_success').hide();
                $('#search_file_error').show().empty().append(
                    '<strong>Error: </strong>' + err.message
                );
            },
            success: function (data) {
                debugger;
                console.log("--------");
                console.log(data);
                $('#search_file_error').hide();
                $('#search_file_success').show().empty().append(
                    '<strong>Success: </strong>' + data.uploadPath + '<br>'
                    + 'Your upload data is being proceesed.'
                );
            }
        });
        event.preventDefault();
    });

    var progress;

    $('#search_word_form').submit(function (event) {
        $('#search_word_table').hide();
        startProgressbar();
        $(this).ajaxSubmit({
            error: function (err) {
                console.log(err.message);
            },
            success: function (data) {
                console.log("--------");
                console.log(data);
                stopProgressbar();
                $('#search_word_tbody').empty().append(
                    '<tr><td>'+data.baidu_ch+'</td>' +
                    '<td>'+data.sogou_ch+'</td>' +
                    '<td>'+data.qihu_ch+'</td>' +
                    '<td>'+data.baidu_en+'</td>' +
                    '<td>'+data.sogou_en+'</td>' +
                    '<td>'+data.qihu_en+'</td></tr>');
                $('#search_word_table').show();
            }
        });
        event.preventDefault();
    });


    function startProgressbar() {
        $('.progress').show();
        var percentage = 0;
        progress = setInterval(function () {
            var $search_bar = $('#search_bar');

            if (percentage >= 100){
                percentage = 0;
            } else {
                percentage += 10;
            }
            $search_bar.width(percentage+'%');
        }, 800);
    }

    function stopProgressbar() {
        clearInterval(progress);
        $('#search_bar').width('0%');
        $('.progress').hide();
    }





    /*
    var pGress = setInterval(function() {
        var pVal = $('#progressbar').progressbar('option', 'value');
        var pCnt = !isNaN(pVal) ? (pVal + 1) : 1;
        if (pCnt > 100) {
            //clearInterval(pGress);
            pCnt = 0;
        }
        $('#progressbar').progressbar({value: pCnt});
    },100);


    
     $('#progressbar').progressbar({
     value:false
     });

    var pGress = setInterval(function() {
        var pVal = $('#search_bar').progressbar('value');
        var pCnt = !isNaN(pVal) ? (pVal + 1) : 1;
        if (pCnt > 100) {
            clearInterval(pGress);
        } else {
            $('#search_bar').progressbar({value: pCnt});
        }
    },10);
    
    */
});

$(window).resize(function () {
    $('body').css('padding-top', parseInt($('#main-navbar').css("height"))+10);
});

$(window).load(function () {
    $('body').css('padding-top', parseInt($('#main-navbar').css("height"))+10);
});