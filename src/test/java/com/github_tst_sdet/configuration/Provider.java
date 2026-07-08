//package com.github_tst_sdet.configuration;
//
//import com.github_tst_sdet.configuration.cache.ResourceKey;
//import com.github_tst_sdet.configuration.cache.TestDataCache;
//import com.github_tst_sdet.configuration.convention.PathResolver;
//import com.github_tst_sdet.configuration.loader.Loader;
//import com.github_tst_sdet.configuration.loader.parser.Parser;
//import com.github_tst_sdet.configuration.loader.reader.Reader;
//
//import java.io.FileNotFoundException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.util.Map;
//
///*
//this class provides API to use Configuration tool
//this class is orchestrator of pipeline and a single entrypoint to Configuration tool
// */
//public class Provider {
//
//    /*
//    Stored data, received before return calling from methods provide
//    Used to provide data from past iterations without calling all steps from pipeline
//     */
//    private static TestDataCache<ResourceKey, Object> cache = new TestDataCache<>(1_000, 10);
//
//    /*
//    object that stored declarations of convention over configuration
//     */
//    private static PathResolver pathResolver = new PathResolver();
//
//    private static Loader loader = new Loader();
//
//    /*
//    object that create object of clazz (in methode provide) depends on Map<String, Object> data
//     */
//    private static ObjectCreator objectCreator = new ObjectCreator();
//
//    /*
//    Class<?> clazz - class of object model we need
//    return Object - object of clazz with filled data from resources
//     */
//    public static Object provide(Class<?> clazz) {
//        Map<String, Object> pipelineContext = getPipelineContext();
//
//        pipelineContext.put("class", clazz);
//        pathResolver.calculatePath(pipelineContext);
//
//        ResourceKey resourceKey = null;
//        if ((Integer) pipelineContext.get("sequence_locator") != -1) {
//            resourceKey = new ResourceKey(
//                    clazz,
//                    (Path) pipelineContext.get("path_to_resource"),
//                    (Integer) pipelineContext.get("sequence_locator")
//            );
//
//            if (cache.contains(resourceKey)) {
//                return cache.get(resourceKey);
//            }
//        }
//
//        Map<String, Object> data = loader.load(pipelineContext);
//
//        Object result = objectCreator.createObject(data, pipelineContext);
//
//        if ((Integer) pipelineContext.get("sequence_locator") != -1) {
//            cache.put(resourceKey, result);
//        }
//
//        return result;
//    }
//
//    public static void registerLoaderReader(String key, Reader reader) {
//        loader.registerReader(key, reader);
//    }
//
//    public static void registerLoaderParser(String key, Parser parser) {
//        loader.registerParser(key, parser);
//    }
//
//
//    /*
//    get content of resources/pipeline-context-configuration-tool.json in Map<String, Object>
//    return Map<String, Object> Map that including all settings and variables that live in pipeline
//     */
//    private static Map<String, Object> getPipelineContext() throws FileNotFoundException {
//        if (!Files.exists(pathResolver.getPipelineContextPath())) {
//            throw new FileNotFoundException("File with path " +
//                    pathResolver.getPipelineContextPath() +
//                    " not found"
//            );
//        }
//        return JsonParser.parse(JsonReader.read(pathResolver.getPipelineContextPath()));
//    }
//}
