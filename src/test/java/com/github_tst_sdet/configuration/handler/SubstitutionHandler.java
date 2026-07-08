package com.github_tst_sdet.configuration.handler;

public interface SubstitutionHandler {

    public boolean canHandle(String field);

    public Object process(String field);
}
