<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: dllo
  Date: 17/10/16
  Time: 上午10:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>list_staff</title>
    <script type="text/javascript">

//         创建 ajax 请求对象
        function createXMLHttpRequest() {
            try {
                return new XMLHttpRequest();
            } catch (e) {
                try {
                    return new ActiveXObject("Msxml2.HTTP");
                } catch (e) {
                    try {
                        return new ActiveXObject("Microsoft.HTTP");
                    } catch (e) {
                        throw e;
                    }
                }
            }
        }

//        根据部门选中状态发起职业查询的请求
        function showPost(obj) {
//            获取部门选中 id
            var departId = obj.value;

//            json 请求地址
            var url = "${pageContext.request.contextPath}/findPostListByPid2.action";

//            1. 创建 ajax 请求对象
            var httpRequest = createXMLHttpRequest();
//            2. 打开一个 url 连接对象
            httpRequest.open("POST", url, true);
//            3. 设置请求头
            httpRequest.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
//            4. 发起请求 设置请求参数
            httpRequest.send("departId=" + departId);
//            5. 设置请求响应
            httpRequest.onreadystatechange = function () {
                if (httpRequest.readyState == 4 && httpRequest.status == 200){
//                    6. 响应成功, 处理响应结果
//                    6.1 将响应数据转换为 json 格式解析
                    var jsonData = eval("(" + httpRequest.responseText + ")");
//                    6.2 根据组件 id 获得职务下拉列表对象
                    var postSelect = document.getElementById("post");
//                    6.3 添加请选择
                    postSelect.innerHTML = "<option value='-1'>---请选择---</option>";
//                    6.4 遍历 json 数组, 添加下拉选项
                    for (var i = 0; i < jsonData.length; i++){
//                        职务 id
                        var id = jsonData[i].id;
//                        职务名称
                        var pname = jsonData[i].pname;
                        postSelect.innerHTML += "<option value='" + id + "'>" + pname + "</option>"
                    }
                }
            }
        }

    </script>
</head>
<body>

<h3>员工列表</h3>

部门:
<select id="depart" onchange="showPost(this)">
    <option value="-1">---请选择---</option>

    <%-- 遍历数据集合显示下来条数 --%>
    <s:iterator value="departList" var="depart">
        <option value="${depart.id}">${depart.dname}</option>
    </s:iterator>
</select>
职务:
<select id="post">
    <option value="-1">---请选择---</option>
</select>


</body>
</html>
