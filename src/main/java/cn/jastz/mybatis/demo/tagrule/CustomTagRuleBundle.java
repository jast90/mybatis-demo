package cn.jastz.mybatis.demo.tagrule;

import org.sitemesh.SiteMeshContext;
import org.sitemesh.content.ContentProperty;
import org.sitemesh.content.tagrules.TagRuleBundle;
import org.sitemesh.content.tagrules.html.ExportTagToContentRule;
import org.sitemesh.tagprocessor.State;

/**
 * @author zhiwen
 */
public class CustomTagRuleBundle implements TagRuleBundle {
    @Override
    public void install(State defaultState, ContentProperty contentProperty, SiteMeshContext siteMeshContext) {
        defaultState.addRule("side-bar", new ExportTagToContentRule(siteMeshContext
                , contentProperty.getChild("sideBar"), false));
        defaultState.addRule("top-nav", new ExportTagToContentRule(siteMeshContext
                , contentProperty.getChild("topNav"), false));
    }

    @Override
    public void cleanUp(State defaultState, ContentProperty contentProperty, SiteMeshContext siteMeshContext) {
        if (!contentProperty.getChild("body").hasValue()) {
            contentProperty.getChild("body").setValue(contentProperty.getValue());
        }
    }
}
