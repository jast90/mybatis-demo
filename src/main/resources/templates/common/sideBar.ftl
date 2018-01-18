<side-bar>
    <ul class="sidebar-menu tree" data-widget="tree">
        <li class="header">XXX导航</li>
        <!-- 当前链接存在该路径下时设置active和menu-open class-->
        <li class="treeview <#if requestContext.getRequestUri()?contains("/city/")>active menu-open</#if>">
            <a href="#">
                <i class="fa fa-dashboard"></i>
                <span>中国行政区划</span>
                <span class="pull-right-container">
                            <i class="fa fa-angle-left pull-right"></i>
                        </span>
            </a>
            <ul class="treeview-menu">
                <li <#if requestContext.getRequestUri()?contains("/city/")> class="active"</#if>>
                    <a href="${requestContext.getContextPath()}/city/1"><i class="fa fa-circle-o"></i>
                        城市列表</a>
                </li>
                <li>
                    <a href="#"><i class="fa fa-circle-o"></i> 菜单1.2</a>
                </li>
                <li>
                    <a href="#"><i class="fa fa-circle-o"></i> 菜单1.3</a>
                </li>
            </ul>
        </li>
        <li class="treeview">
            <a href="#">
                <i class="fa fa-dashboard"></i>
                <span>菜单2</span>
                <span class="pull-right-container">
                            <i class="fa fa-angle-left pull-right"></i>
                        </span>
            </a>
            <ul class="treeview-menu">
                <li>
                    <a href="#"><i class="fa fa-circle-o"></i> 菜单2.1</a>
                </li>
                <li>
                    <a href="#"><i class="fa fa-circle-o"></i> 菜单2.2</a>
                </li>
                <li>
                    <a href="#"><i class="fa fa-circle-o"></i> 菜单2.3</a>
                </li>
            </ul>
        </li>
    </ul>
</side-bar>