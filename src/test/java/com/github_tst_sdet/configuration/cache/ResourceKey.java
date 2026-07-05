package com.github_tst_sdet.configuration.cache;

import java.nio.file.Path;

public class ResourceKey {
    private Class<?> clazz;
    private Path path;
    private Integer sequenceNumber;

    public ResourceKey(final Class<?> clazz, final Path path, final Integer sequenceNumber) {
        this.clazz = clazz;
        this.path = path;
        this.sequenceNumber = sequenceNumber;
    }
}
