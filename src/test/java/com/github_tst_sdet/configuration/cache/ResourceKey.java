package com.github_tst_sdet.configuration.cache;

import java.nio.file.Path;
import java.util.Objects;

public class ResourceKey {
    private Class<?> clazz;
    private Path path;
    private Integer sequenceNumber;

    public ResourceKey(final Class<?> clazz, final Path path, final Integer sequenceNumber) {
        this.clazz = clazz;
        this.path = path;
        this.sequenceNumber = sequenceNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(clazz, path, sequenceNumber);
    }

    @Override
    public boolean equals(final Object obj) {
        return obj instanceof ResourceKey
                && this.clazz.equals(((ResourceKey) obj).clazz)
                && this.path.equals(((ResourceKey) obj).path)
                && this.sequenceNumber.equals(((ResourceKey) obj).sequenceNumber);
    }
}
