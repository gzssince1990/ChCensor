<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@ include file="head.jsp"%>
<body>
<%@ include file="navbar.jsp"%>

<div class="container">
    <div class="col-sm-offset-2 col-sm-8">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h1>Search Word</h1>
            </div>
            <div class="panel-body">
                <form class="form-inline" action="/search/word" method="post" role="form" id="search_word_form">
                    <div class="form-group">
                        <input type="text" class="form-control" name="word" id="word" required>
                    </div>
                    <button type="submit" class="btn btn-default"><span class="glyphicon glyphicon-search"></span></button>
                </form>

                <div class="progress" style="display: none;">
                    <div class="progress-bar progress-bar-striped active" role="progressbar" style="width: 0%;" id="search_bar">
                    </div>
                </div>
                <!--div id="progressbar"></div-->

                <table class="table table-striped table-hover" style="display: none;" id="search_word_table">
                    <thead>
                    <tr>
                        <th>baidu_ch</th>
                        <th>sogou_ch</th>
                        <th>qihu_ch</th>
                        <th>baidu_en</th>
                        <th>sogou_en</th>
                        <th>qihu_en</th>
                    </tr>
                    </thead>
                    <tbody id="search_word_tbody"></tbody>
                </table>
            </div>
        </div>
    </div>
</div>

</body>
</html>