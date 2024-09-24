<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Employee Form</title>
</head>
<body>
<form action="updateemployee" method="post">
            員工編號:<input type="text" name="no" value="${emp.employeeNumber}"/><br/>
            員工姓氏:<input type="text" name="fn" value="${emp.firstName}"/><br/>
            員工名字:<input type="text" name="ln" value="${emp.lastName}"/><br/>
            電話分機:<input type="text" name="extension" value="${emp.extension}"/><br/>
            電子郵件:<input type="text" name="email" value="${emp.email}"/><br/>
            辦公室代號:<input type="text" name="code" value="${emp.officeCode}"/><br/>
            主管編號:<input type="text" name="rpt" value="${emp.reportsTo}"/><br/>
            員工職稱:<input type="text" name="title" value="${emp.jobTitle}"/><br/>
        <input type="submit" value="Send"/>
  </form>
</body>
</html>