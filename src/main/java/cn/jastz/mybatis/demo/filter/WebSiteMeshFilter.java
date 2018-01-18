package cn.jastz.mybatis.demo.filter;

import cn.jastz.mybatis.demo.tagrule.CustomTagRuleBundle;
import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;

import javax.servlet.annotation.WebFilter;

/**
 * @author zhiwen
 */
@WebFilter(urlPatterns = {"/*"})
public class WebSiteMeshFilter extends ConfigurableSiteMeshFilter {

    @Override
    protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
        builder.addDecoratorPath("/*"
                , "/decorator/default.html")
                .addTagRuleBundle(new CustomTagRuleBundle());
    }
}
