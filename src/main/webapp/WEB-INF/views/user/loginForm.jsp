<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR" %>

<%@ include file="../layout/header.jsp" %>

    <div class="container">
        <form action="/auth/loginProc" method="post">
            <div class="form-group">
                <label for="username">Username</label>
                <input type="text" class="form-control" id="username" placeholder="Enter Username" name="username">
            </div>
            <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" class="form-control" id="password" placeholder="Enter password" name="password">
            </div>
<%--            <div class="form-group form-check">--%>
<%--                <label class="form-check-label">--%>
<%--                    <input class="form-check-input" type="checkbox" name="remember"> Remember me--%>
<%--                </label>--%>
<%--            </div>--%>
            <button id="btn-login" class="btn btn-primary">·Î±×ÀÎ</button>
        </form>
    </div>

<script src="/js/user.js"></script>
<%@ include file="../layout/footer.jsp" %>


