<%--
  Created by IntelliJ IDEA.
  User: Joins
  Date: 2019-07-22
  Time: 오후 4:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert Title Here</title>
</head>
<body>
<h1>/sample/admin page</h1>

<!--스프링 시큐리티와 관련된 정보를 출력/사용-->
<!--sec : au~~~principal 은 UserDatailsService에서 반환된 객체이다 ( CustomUserDeatilesService이용시 CustomUser객체-->
<p>principal : <sec:authentication property="principal"/></p>
<p>MemberVO : <sec:authentication property="principal.member"/></p>
<p>사용자이름 : <sec:authentication property="principal.member.userName"/></p>
<p>사용자아이디 : <sec:authentication property="principal.username"/></p>
<p>사용자 권한 리스트 : <sec:authentication property="principal.member.authList"/></p>

<!--로그아웃으로 이동하는 링크-->
<a href="/customLogout">Logout</a>
</body>
</html>