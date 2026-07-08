package com.github_tst_sdet.configuration.handler;


import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class RandomHandler implements SubstitutionHandler{

    @Override
    public boolean canHandle(String field) {
        return field.startsWith("${random:") && field.endsWith("}");
    }

    @Override
    public Object process(String field) {
        field = field.replace("${random:", "");
        field = field.replace("}", "");

        String[] parts = field.split(":");
        String type = parts[0].toLowerCase();

        ThreadLocalRandom random = ThreadLocalRandom.current();

        return switch (type) {
            case "int" -> randomInt(parts);
            case "long" -> randomLong(parts);
            case "double" -> randomDouble(parts);
            case "boolean" -> ThreadLocalRandom.current().nextBoolean();
            case "string" -> randomString(parts);
            case "uuid" -> UUID.randomUUID().toString();
            default -> throw new IllegalArgumentException("Unknown random type: " + type);
        };
    }

    private int randomInt(String[] parts) {
        if (parts.length == 1) {
            return ThreadLocalRandom.current().nextInt();
        }

        int lower = Integer.parseInt(parts[1]);
        int upper = Integer.parseInt(parts[2]);

        return ThreadLocalRandom.current().nextInt(lower, upper + 1);
    }

    private long randomLong(String[] parts) {
        if (parts.length == 1) {
            return ThreadLocalRandom.current().nextLong();
        }

        long lower = Long.parseLong(parts[1]);
        long upper = Long.parseLong(parts[2]);

        return ThreadLocalRandom.current().nextLong(lower, upper + 1);
    }

    private double randomDouble(String[] parts) {
        if (parts.length == 2) {
            return ThreadLocalRandom.current().nextDouble();
        }

        double lower = Double.parseDouble(parts[1]);
        double upper = Double.parseDouble(parts[2]);

        return ThreadLocalRandom.current().nextDouble(lower, upper);
    }

    private String randomString(String[] parts) {
        int length = parts.length >= 2 ? Integer.parseInt(parts[1]) : 10;

        final String alphabet =
                "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        StringBuilder builder = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            builder.append(
                    alphabet.charAt(
                            ThreadLocalRandom.current().nextInt(alphabet.length())
                    )
            );
        }

        return builder.toString();
    }
}
