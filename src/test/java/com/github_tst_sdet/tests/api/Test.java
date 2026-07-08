package com.github_tst_sdet.tests.api;



import com.github_tst_sdet.configuration.convention.PathResolver;
import com.github_tst_sdet.configuration.loader.Loader;
import com.github_tst_sdet.model.Browser;
import com.github_tst_sdet.model.ApplicationConfig;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {

    @org.junit.jupiter.api.Test
    public void testConvention() {
        Map<String, Object> pipelineContext = new HashMap<String, Object>();
        PathResolver pathResolver = new PathResolver();

        List<String> extensions = new ArrayList<String>();
        extensions.add(".yaml");
        extensions.add(".json");
        pipelineContext.put("extensions_with_priority", extensions);

        pipelineContext.put("class", ApplicationConfig.class);
        try {
            pathResolver.calculatePath(pipelineContext);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(pipelineContext.get("path_to_resource"));

        pipelineContext.put("class", Browser.class);
        try {
            pathResolver.calculatePath(pipelineContext);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(pipelineContext.get("path_to_resource"));
    }

    @org.junit.jupiter.api.Test
    public void testLoad() {
        Map<String, Object> pipelineContext = new HashMap<String, Object>();

        pipelineContext.put("key_reader", "yaml");
        pipelineContext.put("key_parser", "yaml");
        pipelineContext.put("path_to_resource", "D:\\для стаж\\books-github\\spring-boot-microservice\\github_tst_sdet\\src\\test\\resources\\config\\application.yaml");

        Loader loader = new Loader();
        System.out.println(loader.load(pipelineContext));
    }
}
