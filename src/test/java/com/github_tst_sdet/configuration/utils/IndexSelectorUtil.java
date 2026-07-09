package com.github_tst_sdet.configuration.utils;

import com.github_tst_sdet.configuration.constants.ConfigurationConstants;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class IndexSelectorUtil {

    public static List<Integer> findIndexes(Map<String, Object> pipelineContext) {

        if ((Boolean) pipelineContext.get(ConfigurationConstants.RETURN_LIST)) {
            return findListIndexes(pipelineContext);
        }

        List<Integer> result = new ArrayList<>(1);

        if ((Boolean) pipelineContext.get(ConfigurationConstants.RANDOM_ENTITY)) {
            result.add(nextRandomIndex(pipelineContext));
        } else {
            result.add((Integer) pipelineContext.get(ConfigurationConstants.ENTITY_INDEX));
        }

        return result;
    }

    private static List<Integer> findListIndexes(Map<String, Object> pipelineContext) {
        List<Integer> result = new ArrayList<>();

        int listSize = resolveListSize(pipelineContext);

        if ((Boolean) pipelineContext.get(ConfigurationConstants.RANDOM_ENTITY)) {
            for (int i = 0; i < listSize; i++) {
                result.add(nextRandomIndex(pipelineContext));
            }
            return result;
        }

        int firstIndex = (Integer) pipelineContext.get(ConfigurationConstants.ENTITY_INDEX);

        for (int i = 0; i < listSize; i++) {
            result.add(firstIndex + i);
        }

        return result;
    }

    private static int resolveListSize(Map<String, Object> pipelineContext) {

        if (!(Boolean) pipelineContext.get(ConfigurationConstants.RANDOM_RETURN_LIST_SIZE)) {
            return (Integer) pipelineContext.get(ConfigurationConstants.RETURN_LIST_SIZE);
        }

        List<Integer> bounds = getRandomBounds(pipelineContext);

        return ThreadLocalRandom.current()
                .nextInt(bounds.get(0), bounds.get(1) + 1);
    }

    @SuppressWarnings("unchecked")
    private static List<Integer> getRandomBounds(Map<String, Object> pipelineContext) {

        Object bounds = pipelineContext.get(ConfigurationConstants.RANDOM_BOUNDS);

        if (bounds == null) {
            throw new IllegalArgumentException(
                    "random_bounds must be specified when random selection is enabled."
            );
        }

        List<Integer> randomBounds = (List<Integer>) bounds;

        if (randomBounds.size() != 2) {
            throw new IllegalArgumentException(
                    "random_bounds must contain exactly two values."
            );
        }

        if (randomBounds.get(0) > randomBounds.get(1)) {
            throw new IllegalArgumentException(
                    "random_bounds[0] must be less than or equal to random_bounds[1]."
            );
        }

        return randomBounds;
    }

    private static int nextRandomIndex(Map<String, Object> pipelineContext) {

        List<Integer> bounds = getRandomBounds(pipelineContext);

        return ThreadLocalRandom.current()
                .nextInt(bounds.get(0), bounds.get(1) + 1);
    }
}
