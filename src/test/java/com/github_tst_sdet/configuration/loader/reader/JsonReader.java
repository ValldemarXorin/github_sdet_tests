package com.github_tst_sdet.configuration.loader.reader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class JsonReader implements Reader {

    @Override
    public Object read(Path path) {
        try {
            return Files.readString(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
