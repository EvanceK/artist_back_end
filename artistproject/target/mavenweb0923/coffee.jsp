<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
		<table border="1" width="60%">
			 <thead>
				    <td>咖啡名稱</td>
				    <td>供應商</td>
				    <td>價錢</td>
				    <td>賣出</td>
				    <td>總計</td>
			 </thead>
		<c:forEach var="c" items="${coffees}">
			   <tr>
			    <td>${c.cofName}</td>
			    <td>${c.supid}</td>
			    <td>${c.price}</td>
			    <td>${c.sales}</td>
			    <td>${c.total}</td>
			   </tr>
		 </c:forEach>

</body>
</html>