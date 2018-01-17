<!DOCTYPE html>
<html lang="en">
<head>
    <title>城市列表</title>
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
</html>