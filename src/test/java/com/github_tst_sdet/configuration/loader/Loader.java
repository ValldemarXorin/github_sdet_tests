package com.github_tst_sdet.configuration.loader;

import com.github_tst_sdet.configuration.constants.ConfigurationConstants;
import com.github_tst_sdet.configuration.loader.parser.JsonParser;
import com.github_tst_sdet.configuration.loader.parser.Parser;
import com.github_tst_sdet.configuration.loader.parser.YamlParser;
import com.github_tst_sdet.configuration.loader.reader.JsonReader;
import com.github_tst_sdet.configuration.loader.reader.Reader;
import com.github_tst_sdet.configuration.loader.reader.YamlReader;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class Loader {
    private Map<String, Reader> readers = new HashMap<>();
    private Map<String, Parser> parsers = new HashMap<>();

    public Loader() {
        readers.put("yaml", new YamlReader());
        readers.put("json", new JsonReader());

        parsers.put("yaml", new YamlParser());
        parsers.put("json", new JsonParser());
    }

    public Object load(Map<String, Object> pipelineContext) {
        Path path = Path.of(pipelineContext.get(ConfigurationConstants.RESOURCE_PATH).toString());
        Object data = readers
                .get((String) pipelineContext.get(ConfigurationConstants.READER_KEY))
                .read(path);
        return parsers
                .get((String) pipelineContext.get(ConfigurationConstants.PARSER_KEY))
                .parse(data);
    }

    public Map<String, Object> loadPipelineContext(Path path) {
        Object data = readers.get("json").read(path);
        return (Map<String, Object>) parsers.get("json").parse(data);
    }

    public void registerReader(String key, Reader reader) {
        readers.put(key, reader);
    }

    public void registerParser(String key, Parser parser) {
        parsers.put(key, parser);
    }
}
