package com.github_tst_sdet.configuration;

import com.github_tst_sdet.configuration.cache.ResourceKey;
import com.github_tst_sdet.configuration.convention.Convention;
import com.github_tst_sdet.configuration.cache.TestDataCache;
import com.github_tst_sdet.configuration.locator.Locator;

import java.io.File;
import java.nio.file.Path;
import java.util.Map;

/*
this class provides API to use Configuration tool
this class is orchestrator of pipeline and a single entrypoint to Configuration tool
 */
public class Provider {

    /*
    Stored data, received before return calling from methods provide
    Used to provide data from past iterations without calling all steps from pipeline
     */
    private static TestDataCache<ResourceKey, Object> cache = new TestDataCache<>(1_000, 10);

    /*
    object that stored declarations of convention over configuration
     */
    private static Convention convention = new Convention();

    /*
    object that provide access to file
     */
    private static Locator locator = new Locator();

    /*
    object that parse data from file to Map
     */
    private static Parser parser = new Parser();

    /*
    object that create object of clazz (in methode provide) depends on Map<String, Object> data
     */
    private static ObjectCreator objectCreator = new ObjectCreator();

    /*
    Class<?> clazz - class of object model we need
    return Object - object of clazz with filled data from resources
     */
    public static Object provide(Class<?> clazz) {
        Map<String, Object> pipelineContext = getPipelineContext();

        pipelineContext.put("class", clazz);
        convention.calculatePath(pipelineContext);

        ResourceKey resourceKey = null;
        if ((Integer) pipelineContext.get("sequence_locator") != -1) {
            resourceKey = new ResourceKey(clazz, pathToResource, (Integer) pipelineContext.get("sequence_locator"));

            if (cache.contains(resourceKey)) {
                return cache.get(resourceKey);
            }
        }

        locator.getFile(pipelineContext);

        Map<String, Object> data = parser.parse(resourceFile, pipelineContext);

        Object result = objectCreator.createObject(data, pipelineContext);

        if ((Integer) pipelineContext.get("sequence_locator") != -1) {
            cache.put(resourceKey, result);
        }

        return result;
    }

    /*
    get content of resources/pipeline-context-configuration-tool.json in Map<String, Object>
    return Map<String, Object> Map that including all settings and variables that live in pipeline
     */
    private static Map<String, Object> getPipelineContext() {
        if (Locator.exist(convention.getPipelineContextPath())) {
            return Parser.jsonToMap(JsonReader.read(Locator.getFile(convention.getPipelineContextPath())));
        }
        return null;
    }
}
