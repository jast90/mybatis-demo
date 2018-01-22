<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>${title}</title>
</head>
<#include "../../common/common.ftl">
<body>
<table class="table table-bordered">
    <tr>
        <th>名称</th>
        <th>编号</th>
        <th>
            <a href="javascript:void(0);" onclick="showSaveModal(this)">
                <i class="fa fa-plus" aria-hidden="true"></i>
            </a>
        </th>
    </tr>
         <#list page.content as item>
             <tr>
                 <td>${item.name!""}</td>
                 <td>${item.code!""}</td>
                 <td></td>
             </tr>
         </#list>
</table>
    <@pagination modelName="page" url="${requestContext.getContextPath()}/auth/business"></@pagination>

<div id="addBusinessForm" style="display: none">
    <form class="form-horizontal">
        <div class="box-body">
            <div class="form-group">
                <label for="inputEmail3" class="col-sm-2 control-label">名称</label>

                <div class="col-sm-10">
                    <input type="name" class="form-control" id="inputEmail3" placeholder="名称">
                </div>
            </div>
            <div class="form-group">
                <label for="inputPassword3" class="col-sm-2 control-label">唯一编码</label>

                <div class="col-sm-10">
                    <input type="code" class="form-control" id="inputPassword3" placeholder="唯一编码">
                </div>
            </div>
        </div>
    </form>
</div>
<javascript>
    <script type="application/javascript" src="/js/modal.js"></script>
    <script type="application/javascript">
        function showSaveModal(elemet) {
            $(elemet).myModal({
                "title": "添加业务",
                "bodyHtml": $("#addBusinessForm").html(),
                "okBtn": {
                    "text": "提交",
                    "onclick": function () {
                        alert("保存新增业务");
                    }
                }
            });
        }
    </script>
</javascript>
</body>
</html>