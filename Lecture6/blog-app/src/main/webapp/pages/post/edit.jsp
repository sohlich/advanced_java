<%@ page import="cz.fai.blog.dto.PostDto" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="css/bootstrap.min.css" rel="stylesheet"></link>
    <link rel="stylesheet" href="css/bootstrap-theme.min.css"></link>
    <script src="js/bootstrap.min.js"></script>
    <style>
        .top-login {
            margin-top: 25%;
        }
    </style>
</head>
<body>
<% boolean isEdit = request.getParameter("id") != null; %>
<% PostDto post = (PostDto) request.getAttribute("post");
%>

<div class="container">
    <%@include file="../partials/header.jsp" %>
    <div class="row">
        <div class="col-md-10">
            <c:if test="${isEdit}">
                <h2>Edit post</h2>
            </c:if>
            <c:if test="${!isEdit}">
                <h2>Create new post</h2>
            </c:if>
        </div>
    </div>
    <div class="row">
        <form method="post" action="createpost">
            <input type="hidden" value="${post.id}" name="id">
            <div class="form-group">
                <label>Title</label>
                <input class="form-control" type="text" name="title"
                       value="${post.title}"/>
            </div>
            <div class="form-group">
                <label>Content</label>
            <textarea name="content" class="form-control">
                ${post.content}
            </textarea>
            </div>
            <div class="checkbox">
                <label>
                    Publish
                    <input type="checkbox" name="published"
                            <%
                                if (post.isPublished()) {
                                    out.print("checked=\"checked\"");
                                } %>
                    />
                </label>
            </div>
            <button type="submit" class="btn btn-default">Ok</button>
        </form>
    </div>
</div>
</body>
</html>
