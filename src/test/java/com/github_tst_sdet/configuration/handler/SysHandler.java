package com.github_tst_sdet.configuration.handler;

public class SysHandler implements SubstitutionHandler{

    @Override
    public boolean canHandle(String field) {
        return field.startsWith("${sys:") && field.endsWith("}");
    }

    @Override
    public Object process(String field) {
        field = field.replace("${sys:", "");
        field = field.replace("}", "");
        return System.getProperty(field);
    }
}
