<!DOCTYPE html>
<html lang="en">
<link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.css" rel="stylesheet">
<link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<table>
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
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</html>