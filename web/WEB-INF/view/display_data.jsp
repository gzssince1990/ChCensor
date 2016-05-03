<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@ include file="head.jsp"%>
<body>
<%@ include file="navbar.jsp"%>

<div class="container">
    <div class="panel panel-default">
        <div class="panel-heading">
            <h1>Search Data</h1>
        </div>
        <div class="panel-body">
            <div class="table-responsive">
                <table class="table table-striped table-hover">

                    <tr>
                        <th rowspan="2" style="vertical-align: middle; text-align: center;">No.</th>
                        <th rowspan="2" style="vertical-align: middle; text-align: center;">Word</th>
                        <th colspan="3" style="text-align: center;">Blocked</th>
                        <th rowspan="2" style="vertical-align: middle; text-align: center">Translation</th>
                        <th colspan="3" style="text-align: center;">Blocked</th>
                        <th rowspan="2" style="vertical-align: middle; text-align: center;">Date</th>
                    </tr>



                    <tr>

                        <th>Baidu</th>
                        <th>Sogou</th>
                        <th>Qihu</th>
                        <th>Baidu</th>
                        <th>Sogou</th>
                        <th>Qihu</th>
                    </tr>
                    <c:forEach var="word" items="${wordList}" varStatus="status">
                        <tr>
                            <td>${status.index + 1}</td>
                            <td>${word.word}</td>
                            <td>${word.baidu_ch}</td>
                            <td>${word.sogou_ch}</td>
                            <td>${word.qihu_ch}</td>
                            <td>${word.translation}</td>
                            <td>${word.baidu_en}</td>
                            <td>${word.sogou_en}</td>
                            <td>${word.qihu_en}</td>
                            <td>${word.createDate}</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </div>
</div>

</body>
</html>
