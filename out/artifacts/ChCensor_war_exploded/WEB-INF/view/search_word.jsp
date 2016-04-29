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
            </div>
        </div>
    </div>
</div>

</body>
</html>
