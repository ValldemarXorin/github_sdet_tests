package com.github_tst_sdet.configuration.handler;

public class EnvHandler implements SubstitutionHandler{

    @Override
    public boolean canHandle(String field) {
        return field.startsWith("${env:") && field.endsWith("}");
    }

    @Override
    public Object process(String field) {
        field = field.replace("${env:", "");
        field = field.replace("}", "");
        return System.getenv(field);
    }
}
