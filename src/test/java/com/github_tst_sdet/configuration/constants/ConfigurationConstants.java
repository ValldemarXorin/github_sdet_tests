package com.github_tst_sdet.configuration.constants;


import java.nio.file.Path;

public final class ConfigurationConstants {

    private ConfigurationConstants() {
        throw new UnsupportedOperationException(
                "Utility class cannot be instantiated."
        );
    }

    /*
     * Default paths
     */
    public static final Path PIPELINE_CONTEXT_PATH =
            Path.of("src", "test", "resources", "pipeline-context-configuration-tool.json");

    public static final String DEFAULT_RESOURCES_DIRECTORY =
            "src/test/resources";

    /*
     * Default loader keys
     */
    public static final String JSON_READER_KEY = "json";
    public static final String YAML_READER_KEY = "yaml";

    public static final String JSON_PARSER_KEY = "json";
    public static final String YAML_PARSER_KEY = "yaml";

    /*
     * Pipeline context keys
     */
    public static final String TARGET_CLASS = "target_class";

    public static final String RESOURCES_PATH = "resources_path";
    public static final String RESOURCE_PATH = "path_to_resource";

    public static final String EXTENSIONS_PRIORITY = "extensions_priority";

    public static final String READER_KEY = "reader_key";
    public static final String PARSER_KEY = "parser_key";

    public static final String ENTITY_INDEX = "entity_index";
    public static final String RANDOM_ENTITY = "random_entity";
    public static final String RANDOM_BOUNDS = "random_bounds";

    public static final String RETURN_LIST = "return_list";
    public static final String RETURN_LIST_SIZE = "return_list_size";
    public static final String RANDOM_RETURN_LIST_SIZE = "random_return_list_size";

    public static final String USE_CACHE = "use_cache";
}
