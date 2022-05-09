package com.kzree.lootboxes.utility;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;
import java.util.Random;

public final class NumberUtilities {
    private NumberUtilities() {}

    public static int convertStringToIntegerWithDefaultValue(String stringToParse, int defaultValue) {
        try {
            return Integer.parseInt(stringToParse);
        } catch (NumberFormatException error) {
            return defaultValue;
        }
    }

    public static OptionalInt convertStringToIntegerWithOptionalInt(String string) {
        if (string == null) return OptionalInt.empty();
        try {
            return OptionalInt.of(Integer.parseInt(string));
        } catch (NumberFormatException e) {
            return OptionalInt.empty();
        }
    }

    public static int randomIntBetween(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }

    public static List<Integer> randomUniqueNumbers(int amount, int min, int max) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            boolean isUnique = false;
            while (!isUnique) {
                Integer randomNumber = randomIntBetween(min, max);
                if (!list.contains(randomNumber)) {
                    list.add(randomNumber);
                    isUnique = true;
                }
            }
        }

        return list;
    }
}
