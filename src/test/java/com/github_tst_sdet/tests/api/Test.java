package com.github_tst_sdet.tests.api;



import com.github_tst_sdet.configuration.convention.annotation.ResourcePath;
import com.github_tst_sdet.model.Browser;
import com.github_tst_sdet.model.Repository;

public class Test {

    @org.junit.jupiter.api.Test
    public void test() {
        Class<Browser> browserClass = Browser.class;
        Class<Repository> repositoryClass = Repository.class;
        System.out.println(browserClass.getAnnotation(ResourcePath.class).value());
        System.out.println(repositoryClass.getAnnotation(ResourcePath.class));
    }
}
