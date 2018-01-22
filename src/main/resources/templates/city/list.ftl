<!DOCTYPE html>
<html lang="en">
<head>
    <title>${title}</title>
</head>
<#include "../common/common.ftl">
<body>
<table class="table table-bordered">
         <#list page.content as item>
             <tr>
                 <td>${item.name!""}</td>
                 <td>${item.address!""}</td>
                 <td>${item.population!""}</td>
                 <td>${item.population!""}</td>
             </tr>
         </#list>
</table>
    <@pagination modelName="page" url="${requestContext.getContextPath()}/city"></@pagination>
</body>
</html>