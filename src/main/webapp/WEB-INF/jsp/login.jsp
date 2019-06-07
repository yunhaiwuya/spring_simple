<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  isELIgnored="false"  %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath }/static/css/login.css">
<title>登陆页面</title>
<style type="text/css">
        .p
        {
            color:red;
            font-size:16px;
        }
    </style>
</head>
<body>
<c:if test="${msg != null}">
    <p class="p">${msg}</p>
</c:if>
<section class="container">
    <div class="login">
      <h1>用户登录</h1>
      <form method="post" action="manager">
        <!-- <p><input type="text" name="login" value="" placeholder="邮箱"></p> -->
        <p><input type="text" name="username" value="" placeholder="用户名"></p>
        <p><input type="password" name="password" value="" placeholder="密码"></p>
        <p class="remember_me">
          <label>
            <input type="checkbox" name="remember_me" id="remember_me">
            记住密码
          </label>
        </p>
        <p class="submit"><input type="submit" name="commit" value="登录"></p>
      </form>
    </div>

    <div class="login-help">
      <p>忘记密码? <a href="index.html">点击修改</a>.</p>
    </div>
  </section>
<div style="text-align:center;"></div>
</body>
</html>