<side-bar>
    <ul class="sidebar-menu tree" data-widget="tree">
        <li class="header">XXX导航</li>
        <!-- 当前链接存在该路径下时设置active和menu-open class-->
        <#assign list = "/city/,/city/save"?split(",")/>
        <#assign has = false/>
        <#list list as item>
            <#if requestContext.getRequestUri()?contains(item)>
                <#assign has = true/>
            </#if>
        </#list>
        <li class="treeview <#if has>active menu-open</#if>">
            <a href="#">
                <i class="fa fa-dashboard"></i>
                <span>中国行政区划</span>
                <span class="pull-right-container">
                            <i class="fa fa-angle-left pull-right"></i>
                        </span>
            </a>
            <ul class="treeview-menu">
                <!-- 当前路径存在菜单组中当前菜单组展开 -->
                <li <#if requestContext.getRequestUri()?contains("/city/")>
                        class="active"</#if>>
                    <a href="${requestContext.getContextPath()}/city/1"><i class="fa fa-circle-o"></i>
                        城市列表</a>
                </li>
            </ul>
        </li>
        <li class="treeview">
            <a href="#">
                <i class="fa fa-dashboard"></i>
                <span>权限</span>
                <span class="pull-right-container">
                            <i class="fa fa-angle-left pull-right"></i>
                        </span>
            </a>
            <ul class="treeview-menu">
                <li>
                    <a href="${requestContext.getContextPath()}/auth/business/1"><i class="fa fa-circle-o"></i> 业务管理</a>
                </li>
            </ul>
        </li>
    </ul>
</side-bar>