<%--
  Created by IntelliJ IDEA.
  User: hongyu
  Date: 2016/12/23
  Time: 上午10:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="HongYu" contect="HongYu, petrel2015@foxmail.com"/>
    <link rel="bookmark" type="image/x-icon" href="favicon.ico"/>
    <link rel="icon" href="favicon.ico" type="image/x-icon" />
    <link rel="shortcut icon" href="favicon.ico" type="image/x-icon" />
    <title>Book-store</title>

    <!-- Bootstrap -->
    <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<div class="container">
    <table class="table table-bordered table-striped">
        <caption>Book-store</caption>
        <thead>
        <tr>
            <th>id</th>
            <th>name</th>
            <th>author</th>
            <th>press</th>
            <th>price</th>
            <th>isbn</th>
            <th>option</th>
        </tr>
        </thead>
        <tbody>
        <s:iterator value="#session.bookList" var="book">
            <tr id="<s:property value="#book.bookId"/>">
                <td><s:property value="#book.bookId"/></td>
                <td name="name"><s:property value="#book.name"/></td>
                <td name="author"><s:property value="#book.author"/></td>
                <td name="press"><s:property value="#book.press"/></td>
                <td name="price"><s:property value="#book.price"/></td>
                <td name="isbn"><s:property value="#book.isbn"/></td>
                <td name="option">
                    <a href="#" class="edit" onclick="edit(<s:property value="#book.bookId"/>)">edit</a>
                    &nbsp;&nbsp;
                    <a href="#" class="delete" onclick="deleteBook(<s:property value="#book.bookId"/>)">delete</a>
                </td>
            </tr>
        </s:iterator>
        </tbody>
    </table>
    <form hidden role="form" action="Book_update" id="updateForm" name="updateForm" method="post">
        <input type="text" id="updateId" name="bookId" value="1">
        <input type="text" id="updateName" name="name" value="1">
        <input type="text" id="updateAuthor" name="author" value="1">
        <input type="text" id="updatePress" name="press" value="1">
        <input type="text" id="updateIsbn" name="isbn" value="1">
        <input type="text" id="updatePrice" name="price" value="1">
    </form>
    <form hidden role="form" action="Book_delete" id="deleteForm" name="updateForm" method="post">
        <input type="text" id="deleteId" name="bookId" value="1">
    </form>
</div>
<hr>
<div class="container">
    <div class="col-md-6 col-md-offset-3">
        <form role="form" action="Book_add" method="post" id="addForm" name="addForm">
            <div class="form-group">
                <label for="addName">New book name</label>
                <input type="text" class="form-control" id="addName" name="name" placeholder="New book name">
            </div>
            <div class="form-group">
                <label for="addAuthor">New book author</label>
                <input type="text" class="form-control" id="addAuthor" name="author" placeholder="New book author">
            </div>
            <div class="form-group">
                <label for="addPress">New book press</label>
                <input type="text" class="form-control" id="addPress" name="press" placeholder="New book press">
            </div>
            <div class="form-group">
                <label for="addIsbn">New book isbn</label>
                <input type="text" class="form-control" id="addIsbn" name="isbn" placeholder="New book isbn">
            </div>
            <div class="form-group">
                <label for="addPrice">New book price</label>
                <input type="text" class="form-control" id="addPrice" name="price" placeholder="New book price">
            </div>
            <button type="submit" class="btn btn-default">Add book</button>
        </form>
    </div>
</div>


<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>

<%--jquery-validation js--%>
<script src="https://static.runoob.com/assets/jquery-validation-1.14.0/dist/jquery.validate.min.js"></script>
<script>
    $.validator.setDefaults({
        submitHandler: function() {
            document.getElementById("addForm").submit();
        }
    });
    $().ready(function() {
        $("#addForm").validate({
            rules: {
                name: {
                    required: true
                },
                author: {
                    required: true
                },
                press: {
                    required: true
                },
                isbn: {
                    required: true,
                    digits:true
                },
                price: {
                    required: true,
                    number:true
                }
            },
            messages: {
                name: {
                    required: "不能为空"
                },
                author: {
                    required: "不能为空"
                },
                press: {
                    required: "不能为空"
                },
                isbn: {
                    required: "不能为空",
                    digits:"必须是整数"
                },
                price: {
                    required: "不能为空",
                    number:"必须是数字"
                }
            }
        });
    });
</script>

<script>
//    edit book in table
//    1.get book every attribute value in "temp"
//    2.clear "td" content
//    3.replace with "input" and fill "temp" in "input"
    function edit(id) {
        var temp = $("#" + id + " td[name='name']").html();
        $("#updateId").val(id);
        $("#" + id + " td[name='name']").html('<input type="text" class="form-control" id="tempName" name="tempName" placeholder="name" value="' + temp + '">');
        temp = $("#" + id + " td[name='author']").html();
        $("#" + id + " td[name='author']").html('<input type="text" class="form-control" id="tempAuthor" name="tempAuthor" placeholder="author" value="' + temp + '">');
        temp = $("#" + id + " td[name='press']").html();
        $("#" + id + " td[name='press']").html('<input type="text" class="form-control" id="tempPress" name="tempPress" placeholder="press" value="' + temp + '">');
        temp = $("#" + id + " td[name='price']").html();
        $("#" + id + " td[name='price']").html('<input type="text" class="form-control" id="tempPrice" name="tempPrice" placeholder="price" value="' + temp + '">');
        temp = $("#" + id + " td[name='isbn']").html();
        $("#" + id + " td[name='isbn']").html('<input type="text" class="form-control" id="tempIsbn" name="tempIsbn" placeholder="isbn" value="' + temp + '">');
        $("#" + id + " td[name='option']").html('<a href="#" onclick="updateSubmit()">save</a>&nbsp;&nbsp;<a href="#" onclick="refresh()">cancel</a>');
        $(".delete").attr("onclick","");
        $(".delete").css("text-decoration","line-through");
        $(".edit").attr("onclick","");
        $(".edit").css("text-decoration","line-through");
    }
//    refresh current page
    function refresh() {
        location.reload();
    }

    function deleteBook(id) {
        var r = confirm("是否删除"+id+"号图书？");
        if (r == true) {
            $("#deleteId").val(id);
            $("#deleteForm").submit();
        } else {
            location.reload();
        }

    }

    function updateSubmit() {
        $("#updateName").val($("#tempName").val());
        $("#updateAuthor").val($("#tempAuthor").val());
        $("#updateIsbn").val($("#tempIsbn").val());
        $("#updatePress").val($("#tempPress").val());
        $("#updatePrice").val($("#tempPrice").val());
        $("#updateForm").submit();
    }
</script>
</body>
</html>
