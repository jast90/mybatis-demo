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
        <th>唯一编码</th>
        <th>所属业务</th>
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
                 <td><#if item.authBusiness??>${item.authBusiness.name!""}</#if></td>
                 <td></td>
             </tr>
         </#list>
</table>
    <@pagination modelName="page" url="${requestContext.getContextPath()}/auth/organization"></@pagination>

<div id="addBusinessForm" style="display: none">
    <div class="box-body">
        <div class="form-group">
            <label for="inputEmail3" class="col-sm-2 control-label">所属业务</label>
            <div class="col-sm-10">
                <select name="businessId" class="form-control" required>
                    <option value="">请选择业务</option>
                    <#list businessList as business>
                        <option value="${business.id}">${business.name}</option>
                    </#list>
                </select>
            </div>
        </div>
        <div class="form-group">
            <label for="inputEmail3" class="col-sm-2 control-label">名称</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" name="name" placeholder="名称" required>
            </div>
        </div>
        <div class="form-group">
            <label for="inputPassword3" class="col-sm-2 control-label">唯一编码</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" name="code" placeholder="唯一编码" required>
            </div>
        </div>
        <div class="form-group">
            <label for="inputPassword3" class="col-sm-2 control-label">父级组织</label>
            <div class="col-sm-10">
                <select name="parentId" class="form-control">
                    <option value="0">请选择父级组织</option>
                    <#list parentList as item>
                        <option value="${item.id}">${item.name}</option>
                    </#list>
                </select>
            </div>
        </div>
    </div>
</div>
<javascript>
    <script type="application/javascript" src="/js/modal.js"></script>
    <script type="application/javascript">
        function showSaveModal(element) {
            var id = "addOrganizationModal";
            var title = "添加组织";
            var formName = "addOrganizationForm";
            var formAction = "${requestContext.getContextPath()}/auth/organization";
            $(element).myModal({
                "id": id,
                "title": title,
                "formName": formName,
                "formAction": formAction,
                "bodyHtml": $("#addBusinessForm").html()
            });
        }
    </script>
</javascript>
</body>
</html>