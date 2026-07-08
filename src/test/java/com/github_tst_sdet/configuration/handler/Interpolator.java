package com.github_tst_sdet.configuration.handler;

import com.github_tst_sdet.configuration.exception.SubstitutionHandlerNotFound;

import java.util.ArrayList;
import java.util.List;

public class Interpolator {
    private List<SubstitutionHandler> substitutionHandlers;

    Interpolator(List<SubstitutionHandler> substitutionHandlers) {
        this.substitutionHandlers = substitutionHandlers;
    }

    Interpolator() {
        substitutionHandlers = new ArrayList<SubstitutionHandler>();
        substitutionHandlers.add(new EnvHandler());
        substitutionHandlers.add(new SysHandler());
        substitutionHandlers.add(new RandomHandler());
    }

    public void addSubstitutionHandler(SubstitutionHandler substitutionHandler) {
        substitutionHandlers.add(substitutionHandler);
    }

    public Object interpolate(String input) {
        for (SubstitutionHandler substitutionHandler : substitutionHandlers) {
            if (substitutionHandler.canHandle(input)) {
                return substitutionHandler.process(input);
            }
        }

        throw new SubstitutionHandlerNotFound(
                "Not found substitution handler in " + substitutionHandlers
                        + " to interpolate " + input
        );
    }
}
