package com.github_tst_sdet.configuration.loader.reader;

import java.nio.file.Path;

public interface Reader {
    public Object read(Path path);
}
