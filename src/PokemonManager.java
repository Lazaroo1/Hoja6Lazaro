import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * esta es la clase que gestiona la información de los pokemon, permitiendo cargar datos desde un archivo CSV,
 * agregar pokemon a la coleccion del user y realizar búsquedas
 */
public class PokemonManager {
    private Map<String, Pokemon> pokemonMap;
    Set<Pokemon> userCollection;

    public PokemonManager(Map<String, Pokemon> pokemonMap) {
        this.pokemonMap = pokemonMap;
        this.userCollection = new HashSet<>();
    }


    public void loadPokemonData(String filePath) {
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            String[] headers = reader.readNext();
            if (headers == null) {
                throw new IOException("El archivo CSV está vacío o no tiene encabezados");
            }

            /**
             * el mapa  que almacenará los índices de las columnas en el CSV,
             * usando como clave el nombre de la columna en minúsculas y sin espacios
             */
            Map<String, Integer> columnIndex = new HashMap<>();
            for (int i = 0; i < headers.length; i++) {
                columnIndex.put(headers[i].trim().toLowerCase(), i);
            }

            String[] nextLine; // este es el array que almacenará cada línea del CSV
            while ((nextLine = reader.readNext()) != null) {
                /**
                 * Se obtiene el nombredel pokemon,
                 * sin espacios y convirtiéndolo en minúsculas
                 */
                String name = nextLine[columnIndex.get("name")].trim().toLowerCase();
                String type1 = nextLine[columnIndex.get("type1")].trim();

                /**
                 * Se obtiene el segundo tipo de pokemon si existe y si no existe sale "N/A".
                 */
                String type2 = columnIndex.containsKey("type2") ? nextLine[columnIndex.get("type2")].trim() : "N/A";
                String habilities = columnIndex.containsKey("abilities") ? nextLine[columnIndex.get("abilities")].trim() : "N/A";

                //aqui solo creamos un objeto nuevo "pokemon" con sus atributos y los guardamos en el mapa
                Pokemon pokemon = new Pokemon(name, type1, type2, habilities);
                pokemonMap.put(name, pokemon);
            }
        } catch (IOException | CsvValidationException | NullPointerException | IndexOutOfBoundsException e) {
            System.out.println("Error al leer el  CSV " + e.getMessage());
        }
    }

    /**
     * Elegi usar un hashset porque asi el usuario no puede agregar el mismo pokemon más de una vez y
     * si intenta agregar un pokemon que ya está en la colección, el HashSet simplemente no lo agregará
     * tambien porque es muy facil el borrar, agregar, ver si contiene tal cosa
     * ademas que ofrece un tiempo de acceso constante para verificar si un pokemon ya está en la colección (o(1))
     * y pues no necesito tener ordenados los pokemones pero si en caso lo necesitara nomas tendría que convertirla
     * en una lista y ordenarla, segun ví en:
     * https://www.w3schools.com/java/java_hashset.asp
     */
    public void addToColeccion(String pokemonName) {
        String lowerCaseName = pokemonName.toLowerCase();
        if (!pokemonMap.containsKey(lowerCaseName)) {
            System.out.println("Error: " + pokemonName + " no existe");
        } else if (!userCollection.add(pokemonMap.get(lowerCaseName))) {
            System.out.println("Error: " + pokemonName + " ya está en tu colección");
        } else {
            System.out.println(pokemonName + " se agregó a tu coleccion");
        }
    }

    public void showDatosPokemon(String pokemonName) {
        Pokemon pokemon = pokemonMap.get(pokemonName.toLowerCase());
        if (pokemon == null) {
            System.out.println("Error: " + pokemonName + " no existe");
            return;
        }
        System.out.println("Nombre: " + pokemon.getName());
        System.out.println("Tipo 1: " + pokemon.getType1());
        System.out.println("Tipo 2: " + pokemon.getType2());
        System.out.println("Habilidades: " + pokemon.getHabilities());
    }

    public void showColeccionByType() {
        List<Pokemon> pokemonList = new ArrayList<>(userCollection);

        pokemonList.sort(new Comparator<Pokemon>() {
            @Override
            public int compare(Pokemon p1, Pokemon p2) {
                return p1.getType1().compareTo(p2.getType1());
            }
        });

        for (Pokemon p : pokemonList) {
            System.out.println(p.getName() + " - " + p.getType1());
        }
    }

    public void showAllByType() {
        List<Pokemon> pokemonList = new ArrayList<>(pokemonMap.values());

        pokemonList.sort(new Comparator<Pokemon>() {
            @Override
            public int compare(Pokemon p1, Pokemon p2) {
                return p1.getType1().compareTo(p2.getType1());
            }
        });

        for (Pokemon p : pokemonList) {
            System.out.println(p.getName() + " - " + p.getType1());
        }
    }
    public void findByHability(String ability) {
        String lowerCaseAbility = ability.toLowerCase();
        boolean found = false;
        for (Pokemon pokemon : pokemonMap.values()) {
            if (pokemon.getHabilities().toLowerCase().contains(lowerCaseAbility)) {
                System.out.println(pokemon.getName() + " tiene la habilidad " + ability);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No se encontraron pokemon con la habilidad: " + ability);
        }
    }
}
