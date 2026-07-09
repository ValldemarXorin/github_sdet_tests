package com.github_tst_sdet.configuration.convention;

import com.github_tst_sdet.configuration.annotation.ResourcePath;
import com.github_tst_sdet.configuration.constants.ConfigurationConstants;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class PathResolver {
    private final Path workDir = Path.of(System.getProperty("user.dir"));

    public void calculatePath(Map<String, Object> pipelineContext) throws FileNotFoundException {
        Class<?> clazz = (Class<?>) pipelineContext.get(ConfigurationConstants.TARGET_CLASS);
        if (clazz.getAnnotation(ResourcePath.class) != null) {
            pipelineContext.put(ConfigurationConstants.RESOURCE_PATH, annotationResolverPath(clazz.getAnnotation(ResourcePath.class)));
            return;
        }

        Path pathWithoutExtension = simpleClassNameResolverPath(clazz.getSimpleName(), (String) pipelineContext.get(ConfigurationConstants.RESOURCES_PATH));
        try {
            Path totalPath = ExtensionResolver.resolveExtension(pathWithoutExtension, (List<String>) pipelineContext.get(ConfigurationConstants.EXTENSIONS_PRIORITY));
            pipelineContext.put(ConfigurationConstants.RESOURCE_PATH, totalPath);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private Path annotationResolverPath(ResourcePath resourcePath) {
        return workDir.resolve(resourcePath.value());
    }

    private Path simpleClassNameResolverPath(String className, String resourcesPath) {
        String[] parts = className.split("(?=[A-Z])");
        Path path = workDir;
        if (resourcesPath == null) {
            path = path.resolve(ConfigurationConstants.RESOURCES_PATH);
        } else {
            path = path.resolve(resourcesPath);
        }
        for (int i = parts.length - 1; i >= 0; i--) {
            path = path.resolve(parts[i].toLowerCase());
        }

        return path;
    }

}
