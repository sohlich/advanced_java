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
<div class="container">
    <%@include file="../partials/header.jsp" %>
    <div class="row">
        <div class="col-md-10">
            <h2>All posts</h2>
        </div>
    </div>
    <div class="row">
        <div class="col-md-10">
            <a class="btn" href="createpost">New</a>
        </div>
    </div>
    <c:forEach items="${posts}" var="post">
        <div class="row">
            <div class="col-md-10">
                <a href="createpost?id=${post.id}">${post.title}
                        <fmt:formatDate
                                value="${post.createDate}"/>
                        <c:out value="${post.published}"></c:out>
            </div>
        </div>
    </c:forEach>
</div>
</body>
</html>
