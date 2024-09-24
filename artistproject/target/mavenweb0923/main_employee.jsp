<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Main Employee View</title>
</head>
<body>
<h2>
<a href='add_employee.html'>員工資料新增</a> 
</h2>
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
    <td>操作/異動</td>   
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
    <td><a href='update_employee?no=${s.employeeNumber}'>資料修改</a>&nbsp;&nbsp;<a href='delete_employee?no=${s.employeeNumber}' onclick='return confirm("刪除?")'>資料刪除</a></td>
   </tr>
 </c:forEach>

</table>
</body>
</html>