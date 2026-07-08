package com.github_tst_sdet.configuration.convention;

import com.github_tst_sdet.configuration.convention.annotation.ResourcePath;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class PathResolver {
    private final Path pipelineContextPath = Path.of("src/test/resources/pipeline-context-configuration-tool.json");
    private final Path workDir = Path.of(System.getProperty("user.dir"));

    public void calculatePath(Map<String, Object> pipelineContext) throws FileNotFoundException {
        Class<?> clazz = (Class<?>) pipelineContext.get("class");
        if (clazz.getAnnotation(ResourcePath.class) != null) {
            pipelineContext.put("path_to_resource", annotationResolverPath(clazz.getAnnotation(ResourcePath.class)));
            return;
        }

        Path pathWithoutExtension = simpleClassNameResolverPath(clazz.getSimpleName(), (String) pipelineContext.get("resources_path"));
        try {
            Path totalPath = ExtensionResolver.resolveExtension(pathWithoutExtension, (List<String>) pipelineContext.get("extensions_with_priority"));
            pipelineContext.put("path_to_resource", totalPath);
        } catch (Exception e) {
            throw new FileNotFoundException(e.getMessage());
        }
    }

    public Path getPipelineContextPath() {
        return pipelineContextPath;
    }

    private Path annotationResolverPath(ResourcePath resourcePath) {
        return workDir.resolve(resourcePath.value());
    }

    private Path simpleClassNameResolverPath(String className, String resourcesPath) {
        String[] parts = className.split("(?=[A-Z])");
        Path path = workDir;
        if (resourcesPath == null) {
            path = path.resolve("src/test/resources");
        } else {
            path = path.resolve(resourcesPath);
        }
        for (int i = parts.length - 1; i >= 0; i--) {
            path = path.resolve(parts[i].toLowerCase());
        }

        return path;
    }

}
