<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Update Student</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <div class="container">
        <h1>Update Student Details</h1>
        
        <c:if test="${not empty error}">
            <div class="alert alert-danger">${error}</div>
        </c:if>

        <form action="${pageContext.request.contextPath}/students/update" method="post">
            <input type="hidden" name="id" value="${student.id}">
            
            <div class="form-group">
                <label>Name</label>
                <input type="text" name="name" value="${student.name}" required>
            </div>
            <div class="form-group">
                <label>Email</label>
                <input type="email" name="email" value="${student.email}" required>
            </div>
            <div class="form-group">
                <label>Course</label>
                <select name="course.id" required>
                    <c:forEach var="course" items="${courses}">
                        <option value="${course.id}" ${course.id == student.course.id ? 'selected' : ''}>
                            ${course.name} (${course.code})
                        </option>
                    </c:forEach>
                </select>
            </div>
            <div class="actions">
                <button type="submit" class="btn btn-primary">Update Student</button>
                <a href="${pageContext.request.contextPath}/students" class="btn">Cancel</a>
            </div>
        </form>
    </div>
</body>
</html>
