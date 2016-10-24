<%@ page import="cz.fai.blog.dto.AuthorDto" %>
<%@ taglib prefix="fmt"
           uri="http://java.sun.com/jsp/jstl/fmt" %>
<% AuthorDto user = (AuthorDto) session.getAttribute("user");%>


<nav class="navbar navbar-default">
    <div class="container">
        <div class="navbar-header">
            <p class="navbar-brand">Simple Blog</p>
        </div>
        <c:if test="${user != null}">
            <ul class="nav navbar-nav navbar-left">
                <li>
                    <a href="posts">My Posts</a>
                </li>
            </ul>
        </c:if>
        <ul class="nav navbar-nav navbar-right">
            <li>
                <c:if test="${user != null}">
                    <p class="navbar-text">
                        <%= user.getFirstName() + " " + user.getLastName() %>
                        <br/>
                        <c:if test="${user != null}">
                            <a href="logout" class="navbar-link">Logout</a>
                        </c:if>
                    </p>
                </c:if>
                <c:if test="${user == null}">
                    <a href="login" class="navbar-link">Login</a>
                </c:if>
                <br/>
            </li>
        </ul>
    </div>
</nav>
