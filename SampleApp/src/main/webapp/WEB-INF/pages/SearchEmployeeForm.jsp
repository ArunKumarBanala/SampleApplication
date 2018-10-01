<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Search Contact</title>
</head>
<body>
    <div align="center">
        <h1>Search Employee</h1>
        <form:form action="search" method="get">
        <table>
            <tr>
                <td>Name:</td>
                <td><input type="text" name="empName" /></td>
            </tr>
            <tr>
                <td colspan="2" align="center"><input type="submit" value="Search"></td>
            </tr>
        </table>
        </form:form>
    </div>
</body>
</html>