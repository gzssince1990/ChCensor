<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@ include file="head.jsp"%>
<body>
<%@ include file="navbar.jsp"%>

<div class="container">
    <div class="col-sm-offset-2 col-sm-8">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h1>Person</h1>
            </div>
            <div class="panel-body">
                <form class="form-horizontal" action="/person1" method="post" role="form">
                    <input type="hidden" name="id">
                    <div class="form-group">
                        <label class="control-label col-sm-2" for="name">Username:</label>
                        <div class="col-sm-10">
                            <input type="text" class="form=control" name="name" id="name" required>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-2" for="age">Age:</label>
                        <div class="col-sm-10">
                            <input type="text" class="form=control" name="age" id="age" required>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button type="submit" class="btn btn-default">Submit</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>


