package com.github_tst_sdet.configuration.convention;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

class ExtensionResolver {
    public static Path resolveExtension(Path pathWithoutExtension, List<String> extensions) throws FileNotFoundException {
        for (String extension : extensions) {
            if (Files.exists(Path.of(pathWithoutExtension.toString() + extension))) {
                return Path.of(pathWithoutExtension + extension);
            }
        }
        throw new FileNotFoundException("No file with name " + pathWithoutExtension + " and any of extensions " + extensions);
    }
}
