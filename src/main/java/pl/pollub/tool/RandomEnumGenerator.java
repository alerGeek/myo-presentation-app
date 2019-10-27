package pl.pollub.tool;

import java.util.Random;


public class RandomEnumGenerator {
    public static final Random RANDOM_ENUM = new Random();

    public static <T extends Enum<?>> T randomEnum(Class<T> clazz) {
        int enumSize = RANDOM_ENUM.nextInt(clazz.getEnumConstants().length);
        return (T) clazz.getEnumConstants()[enumSize];
    }
}