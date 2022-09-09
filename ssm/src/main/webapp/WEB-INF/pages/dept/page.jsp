<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <base href="${pageContext.request.contextPath}/">
    <meta charset="UTF-8">
    <title>部门列表</title>
    <link rel="stylesheet" href="static/bootstrap/css/bootstrap.min.css">
</head>
<body>
    <div style="text-align: center" class="container-fluid">
        <h1>分页列表</h1>
        <table class="table table-bordered table-hover table-striped">
            <tr>
                <th>部门编号</th>
                <th>部门名称</th>
                <th>部门位置</th>
            </tr>
            <c:forEach items="${pageInfo.list}" var="dept">
            <tr>
                <td>${dept.deptno}</td>
                <td>${dept.dname}</td>
                <td>${dept.loc}</td>
            </tr>
            </c:forEach>
        </table>
        <ul class="pagination">
            <c:if test="${!pageInfo.isFirstPage}"><li><a href="dept/page?pageNumber=${pageInfo.prePage}">上一页</a></li></c:if>
            <c:if test="${pageInfo.isFirstPage}"><li class="disabled"><a href="javascript:void(0);">上一页</a></li></c:if>
            <c:forEach begin="1" end="${pageInfo.pages}" var="i">
                <c:if test="${i == pageInfo.pageNum}"><li class="active"><a href="javascript:void(0);">${i}</a></li></c:if>
                <c:if test="${i != pageInfo.pageNum}"><li><a href="dept/page?pageNumber=${i}">${i}</a></li></c:if>
            </c:forEach>
            <c:if test="${!pageInfo.isLastPage}"><li><a href="dept/page?pageNumber=${pageInfo.nextPage}">下一页</a></li></c:if>
            <c:if test="${pageInfo.isLastPage}"><li class="disabled"><a href="javascript:void(0);">下一页</a></li></c:if>
        </ul>
    </div>
</body>
</html>