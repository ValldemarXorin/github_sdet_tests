package com.github_tst_sdet.configuration.loader.parser;

import org.yaml.snakeyaml.Yaml;

import java.util.Map;

public class YamlParser implements Parser {

    private static final Yaml yaml = new Yaml();

    @Override
    public Map<String, Object> parse(Object data) {
        return yaml.load((String) data);
    }
}