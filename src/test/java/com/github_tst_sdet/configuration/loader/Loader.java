package com.github_tst_sdet.configuration.loader;

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

    public Map<String, Object> load(Map<String, Object> pipelineContext) {
        Path path = Path.of((String) pipelineContext.get("path_to_resource"));
        Object data = readers.get((String) pipelineContext.get("key_reader")).read(path);
        return parsers.get((String) pipelineContext.get("key_parser")).parse(data);
    }

    public void registerReader(String key, Reader reader) {
        readers.put(key, reader);
    }

    public void registerParser(String key, Parser parser) {
        parsers.put(key, parser);
    }
}
