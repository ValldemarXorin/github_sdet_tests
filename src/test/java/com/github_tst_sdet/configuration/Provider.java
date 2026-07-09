package com.github_tst_sdet.configuration;

import com.github_tst_sdet.configuration.cache.ResourceKey;
import com.github_tst_sdet.configuration.cache.TestDataCache;
import com.github_tst_sdet.configuration.constants.ConfigurationConstants;
import com.github_tst_sdet.configuration.convention.PathResolver;
import com.github_tst_sdet.configuration.loader.Loader;
import com.github_tst_sdet.configuration.loader.parser.Parser;
import com.github_tst_sdet.configuration.loader.reader.Reader;
import com.github_tst_sdet.configuration.object_creator.ObjectCreator;
import com.github_tst_sdet.configuration.utils.IndexSelectorUtil;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*
this class provides API to use Configuration tool
this class is orchestrator of pipeline and a single entrypoint to Configuration tool
*/
public class Provider {

    /*
    Stored data, received before return calling from methods provide.
    Used to provide data from previous iterations without executing the whole pipeline.
     */
    private static final TestDataCache<ResourceKey, Object> cache =
            new TestDataCache<>(1000, 10);

    /*
    Object that stores Convention over Configuration rules.
     */
    private static final PathResolver pathResolver = new PathResolver();

    private static final Loader loader = new Loader();

    /*
    Creates object models from parsed data.
     */
    private static final ObjectCreator objectCreator = new ObjectCreator();

    /*
    Creates object(s) of clazz from resource.
     */
    @SuppressWarnings("unchecked")
    public static Object provide(Class<?> clazz) {

        try {

            Map<String, Object> pipelineContext = getPipelineContext();

            pipelineContext.put(ConfigurationConstants.TARGET_CLASS, clazz);

            pathResolver.calculatePath(pipelineContext);

            Object parsedData = loader.load(pipelineContext);

            /*
             * Configuration file contains only one object.
             */
            if (parsedData instanceof Map<?, ?> map) {

                boolean useCache = (Boolean) pipelineContext.get(ConfigurationConstants.USE_CACHE);

                ResourceKey key = new ResourceKey(
                        clazz,
                        Path.of(pipelineContext.get(ConfigurationConstants.RESOURCE_PATH).toString()),
                        (Integer) pipelineContext.get(ConfigurationConstants.ENTITY_INDEX)
                );

                if (useCache && cache.contains(key)) {
                    return cache.get(key);
                }

                Object result = objectCreator.createObject(
                        (Map<String, Object>) map,
                        clazz
                );

                if (useCache) {
                    cache.put(key, result);
                }

                return result;
            }

            /*
             * Configuration file contains a list of objects.
             */
            if (parsedData instanceof List<?> parsedList) {

                List<Integer> indexes = IndexSelectorUtil.findIndexes(pipelineContext);

                List<Object> result = new ArrayList<>();

                Path resourcePath = Path.of(
                        (String) pipelineContext.get(ConfigurationConstants.RESOURCE_PATH)
                );

                boolean useCache = (Boolean) pipelineContext.get(ConfigurationConstants.USE_CACHE);

                for (Integer index : indexes) {

                    ResourceKey key = new ResourceKey(
                            clazz,
                            resourcePath,
                            index
                    );

                    if (useCache && cache.contains(key)) {
                        result.add(cache.get(key));
                        continue;
                    }

                    Map<String, Object> data =
                            (Map<String, Object>) parsedList.get(index);

                    Object object =
                            objectCreator.createObject(data, clazz);

                    if (useCache) {
                        cache.put(key, object);
                    }

                    result.add(object);
                }

                if ((Boolean) pipelineContext.get(ConfigurationConstants.RETURN_LIST)) {
                    return result;
                }

                return result.getFirst();
            }

            throw new IllegalStateException(
                    "Unsupported parsed data type: " +
                            parsedData.getClass().getName()
            );

        } catch (Exception e) {
            throw new RuntimeException("Unable to provide object.", e);
        }
    }

    public static void registerLoaderReader(String key, Reader reader) {
        loader.registerReader(key, reader);
    }

    public static void registerLoaderParser(String key, Parser parser) {
        loader.registerParser(key, parser);
    }

    /*
    Reads pipeline context configuration.
     */
    private static Map<String, Object> getPipelineContext() throws FileNotFoundException {

        Path pipelineContextPath = ConfigurationConstants.PIPELINE_CONTEXT_PATH;

        if (!Files.exists(pipelineContextPath)) {
            throw new FileNotFoundException(
                    "Pipeline context not found: " + pipelineContextPath
            );
        }

        return loader.loadPipelineContext(pipelineContextPath);
    }
}