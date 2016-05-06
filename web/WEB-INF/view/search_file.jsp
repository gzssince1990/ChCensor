<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@ include file="head.jsp"%>
<body>
<%@ include file="navbar.jsp"%>

<div class="container">
    <div class="col-sm-offset-2 col-sm-8">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h1>Search File</h1>
            </div>
            <div class="panel-body">
                <form class="form-horizontal" action="/search/file" method="post" role="form" enctype="multipart/form-data" id="search_file_form">
                    <div class="form-group">
                        <label class="control-label col-sm-2" for="file">File:</label>
                        <div class="col-sm-10">
                            <input type="file" class="form=control" name="file" id="file" required>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button type="submit" class="btn btn-default">Submit</button>
                        </div>
                    </div>
                </form>
                <div class="row">
                    <div class="col-sm-12">
                        <div class="alert alert-danger fade in" id="search_file_error" style="display: none;"></div>
                    </div>
                </div>
                <div class="row">
                <div class="col-sm-12">
                    <div class="alert alert-success fade in" id="search_file_success" style="display: none;"></div>
                </div>
            </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>


