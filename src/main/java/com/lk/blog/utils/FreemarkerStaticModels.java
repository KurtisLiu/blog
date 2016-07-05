package com.lk.blog.utils;

import freemarker.ext.beans.BeansWrapper;
import freemarker.template.TemplateHashModel;

import java.util.HashMap;
import java.util.Properties;
import java.util.Set;

public class FreemarkerStaticModels extends HashMap<Object, Object> {
    private static FreemarkerStaticModels FREEMARKER_STATIC_MODELS;
    private Properties staticModels;

    private FreemarkerStaticModels(){

    }

    public static FreemarkerStaticModels getInstance(){
        if(FREEMARKER_STATIC_MODELS == null){
            FREEMARKER_STATIC_MODELS = new FreemarkerStaticModels();
        }
        return FREEMARKER_STATIC_MODELS;
    }

    public Properties getStaticModels() {
        return staticModels;
    }

    public void setStaticModels(Properties staticModels) {
        if(this.staticModels == null && staticModels != null){
            this.staticModels = staticModels;
            Set<String> keys=this.staticModels.stringPropertyNames();
            for (String key : keys) {
                FREEMARKER_STATIC_MODELS.put(key, useStaticPackage(this.staticModels.getProperty(key)));
            }
        }
    }

    public static TemplateHashModel useStaticPackage(String packageName){
        try
        {
            BeansWrapper wrapper = BeansWrapper.getDefaultInstance();
            TemplateHashModel staticModels = wrapper.getStaticModels();
            TemplateHashModel fileStatics = (TemplateHashModel) staticModels.get(packageName);
            return fileStatics;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
