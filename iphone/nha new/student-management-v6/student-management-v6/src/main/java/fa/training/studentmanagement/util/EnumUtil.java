package fa.training.studentmanagement.util;

import java.util.Arrays;

public class EnumUtil {
    public static <T> T getEnumByName(String name, Class<T> classType) {
        return Arrays.stream(classType.getEnumConstants())
                .filter(t -> t.toString().equalsIgnoreCase(name))
                .findFirst().orElse(null);
    }
}
