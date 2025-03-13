import java.util.Map;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.LinkedHashMap;

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
                throw new IllegalArgumentException("Opción no válida.");
        }
    }
}