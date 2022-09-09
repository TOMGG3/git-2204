<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>首页</title>
</head>
<body>
  <div style="text-align: center">
      <h1>首页</h1>
      <p><a href="/user/list">批量查询</a></p>
      <p><a href="/user/get/101">单条查询</a></p>
      <form action="/user/save" method="post">
          <button>新增提交</button>
      </form>
      <!--原生form表单只能提交get或post请求-->
      <form action="/user/edit" method="post">
          <input type="hidden" name="_method" value="put">
          <button>编辑提交</button>
      </form>

      <form action="/user/remove/1002" method="post">
          <input type="hidden" name="_method" value="delete">
          <button>删除提交</button>
      </form>
  </div>
</body>
</html>
