package com.kzree.lootboxes.utility;

import java.util.OptionalInt;

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
}
