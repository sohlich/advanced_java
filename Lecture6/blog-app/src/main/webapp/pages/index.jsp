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
    <%@include file="partials/header.jsp" %>
    <div class="row">
        <div class="col-md-10">
            <h2>Recent posts</h2>
        </div>
    </div>
    <div class="row">
        <c:forEach items="${posts}" var="post">
            <div class="col-md-10">
                <h3>${post.title}</h3>
                <fmt:formatDate value="${post.createDate}"
                                pattern="dd.MM.yyyy"/>
                <div class="lead">
                    <c:out value="post.content"></c:out>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
</body>
</html>
