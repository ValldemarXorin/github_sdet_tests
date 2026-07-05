package com.github_tst_sdet.configuration.convention;

import com.github_tst_sdet.configuration.convention.annotation.ResourcePath;

import java.nio.file.Path;
import java.util.Map;

public class Convention {
    private final Path pipelineContextPath = Path.of("resources/pipeline-context-configuration-tool.json");

    public void calculatePath(Map<String, Object> pipelineContext) {
        Class<?> clazz = (Class<?>) pipelineContext.get("class");
        if (clazz.getAnnotation(ResourcePath.class) != null) {
            pipelineContext.put("path_to_resource", annotationResolverPath(clazz.getAnnotation(ResourcePath.class)));
        }
        pipelineContext.put("path_to_resource", simpleClassNameResolverPath(clazz.getSimpleName(), (String) pipelineContext.get("resources_path")));
    }

    public Path getPipelineContextPath() {
        return pipelineContextPath;
    }

    private Path annotationResolverPath(ResourcePath resourcePath) {
        return Path.of(resourcePath.value());
    }

    private Path simpleClassNameResolverPath(String className, String resourcesPath) {
        String[] parts = className.split("(?=[A-Z])");
        Path path;
        if (resourcesPath == null) {
            path = Path.of("resources");
        } else {
            path = Path.of(resourcesPath);
        }
        for (int i = parts.length - 1; i >= 0; i--) {
            path = path.resolve(parts[i].toLowerCase());
        }

        return path;
    }
}
