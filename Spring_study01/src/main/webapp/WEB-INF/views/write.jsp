<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!!!  
</h1>

<P>  The time on the server is ${serverTime}. </P>
<form method="post" action="write" >
	유저아이디: <input type="text" name="id" /></br>
	제목: <input type="text" name="title" /></br>
	파일 이름: <input type="text" name="Fname" /></br>
	확장자: <select name="type">
                    <option value=".c">C</option>
                    <option value=".java">Java</option>
          </select></br>
	글 내용: <input type="text" name="contents" /></br>
	<p> 코드작성부분: </p>
	<p> <textarea name="code" rows="50" cols="50"></textarea>
	<input type="submit" value="저장"/></br>
</form>
</body>
</html>
