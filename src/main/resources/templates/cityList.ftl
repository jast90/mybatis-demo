<!DOCTYPE html>
<html lang="en">
<head>
    <title>城市列表</title>
</head>
<body>
<ol class="breadcrumb">
    <li><a href="#">管理系统</a></li>
    <li><a href="#">首页</a></li>
    <li class="active">城市列表</li>
</ol>
<table class="table">
         <#list page.content as item>
             <tr>
                 <td>${item.name!""}</td>
                 <td>${item.address!""}</td>
                 <td>${item.population!""}</td>
                 <td>${item.population!""}</td>
             </tr>
         </#list>
</table>
    <@pagination modelName="page" url="${request.getContextPath()}/city/page"></@pagination>
</body>
</html>