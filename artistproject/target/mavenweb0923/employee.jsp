<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Coffee View</title>
</head>
<body>
<table border="1" width="70%">
 <thead>
    <td>員工編號</td>
    <td>員工姓氏</td>
    <td>員工名字</td>
    <td>分機</td>
    <td>電子郵件</td>
    <td>辦公室代號</td>
    <td>主管員編</td>
    <td>職稱</td>
   
 </thead>
 <c:forEach var="s" items="${employees}">
   <tr>
    <td>${s.employeeNumber}</td>
    <td>${s.firstName}</td>
    <td>${s.lastName}</td>
    <td>${s.extension}</td>
    <td>${s.email}</td>
    <td>${s.officeCode}</td>   
    <td>${s.reportsTo}</td>   
    <td>${s.jobTitle}</td>       
   </tr>
 </c:forEach>

</table>
</body>
</html>