package com.github_tst_sdet.configuration.loader.parser;

import java.util.Map;

public interface Parser {

    public Map<String, Object> parse(Object data);
}
