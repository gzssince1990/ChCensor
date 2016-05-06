<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@ include file="head.jsp"%>
<body>
<%@ include file="navbar.jsp"%>

<div class="container">
    <div class="col-sm-offset-2 col-sm-8">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h1>ChCensor</h1>
            </div>
            <div class="panel-body">
                <div id="display_visual"></div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    $.ajax({
        url: "/display/visual",
        type: 'POST',
        dataType: 'json',
        data: {},
        success: function(data){
            console.log(data);
            var words = [];

            for(var i=0; i<data.length; i++){
                var weight = 6;
                weight += data[i].baidu_ch? 1:0;
                weight += data[i].baidu_en? 1:0;
                weight += data[i].sogou_ch? 1:0;
                weight += data[i].sogou_en? 1:0;
                weight += data[i].qihu_ch? 1:0;
                weight += data[i].qihu_en? 1:0;
                var color = "cyan";
                if(weight > 11){
                    color = "red";
                } else if (weight > 9){
                    color = "orange";
                } else if (weight > 7){
                    color = "green";
                }
                words.push({text: data[i].word, weight: weight, color: color});
            }

            $('#display_visual').jQCloud(words, {width: 500, height: 400});


            setTimeout(function(){
                var obj = $('#display_visual').data("jqcloud");
                var data = obj.word_array;
                for(var i in data){
                    $("#"+data[i]["attr"]["id"]).css("color", data[i]["color"]);
                }
            }, 100);
        },
        error: function(err){
            console.log(err);
            alert("err" + err);
        }
    });

</script>

</body>
</html>