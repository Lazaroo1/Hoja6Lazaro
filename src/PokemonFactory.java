import java.util.Map;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.LinkedHashMap;
import org.apache.commons.lang3.StringUtils;

public class PokemonFactory {
    public static Map<String, Pokemon> createMap(int choice) {
        switch (choice) {
            case 1:
                return new HashMap<>();
            case 2:
                return new TreeMap<>();
            case 3:
                return new LinkedHashMap<>();
            default:
                throw new IllegalArgumentException("Intenta otra vez, con un num del 1 al 3");
        }
    }
}