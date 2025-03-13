import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class PokemonManager {
    private Map<String, Pokemon> pokemonMap;
    private Set<Pokemon> userCollection;


    /**
     * Elegi usar un hashset porque asi el usuario no puede agregar el mismo Pokémon más de una vez y
     * si intenta agregar un Pokémon que ya está en la colección, el HashSet simplemente no lo agregará
     * tambien porque es muy facil el borrar, agregar, ver si contiene tal cosa
     * ademas que ofrece un tiempo de acceso constante para verificar si un Pokémon ya está en la colección (o(1))
     * y pues no necesito tener ordenados los pokemones pero si en caso lo necesitara nomas tendría que convertirla
     * en una lista y ordenarla, segun ví en:
     * https://www.w3schools.com/java/java_hashset.asp
     */

    public PokemonManager(Map<String, Pokemon> pokemonMap) {
        this.pokemonMap = pokemonMap;
        this.userCollection = new HashSet<>();
    }



    /**
     * Lee el archivo y almacena la info en un map
     * Cada línea del archivo CSV representa un Pokémon, y las columnas contienen sus atributos.
     * @throws IOException Si ocurre un error al leer el archivo.
     * @throws CsvValidationException Si el archivo CSV no tiene un formato válido.
     */

    public void loadPokemonData(String filePath) {
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            String[] nextLine;

            boolean isFirstLine = true;//es como nuestro indicador para saber si se está leyendo la primera linea o no

            while ((nextLine = reader.readNext()) != null) {
                // Saltamos la primera línea de los encabezados
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }
// [n] siendo n el numero de la columna en la que se encuentra el dato requerido
                String name = nextLine[0];
                String type1 = nextLine[2];
                String type2 = nextLine[3];
                String habilities = nextLine[7];

                Pokemon pokemon = new Pokemon(name, type1, type2, habilities);
                pokemonMap.put(name, pokemon);
            }
        } catch (IOException | CsvValidationException e) {
            System.out.println("Error al leer el CSV " + e.getMessage());
        }
    }

    public void addToColeccion(String pokemonName) {
        if (!pokemonMap.containsKey(pokemonName)) {
            System.out.println("Error: " + pokemonName + " no está en el archivo");
        } else if (userCollection.contains(pokemonMap.get(pokemonName))) {
            System.out.println("Error: " + pokemonName + " ya está en tu colección");
        } else {
            userCollection.add(pokemonMap.get(pokemonName));
            System.out.println(pokemonName + " se añadio a tu coleecion");
        }
    }

    public void showDatosPokemon(String pokemonName) {
        if (!pokemonMap.containsKey(pokemonName)) {
            System.out.println("Error: " + pokemonName + " no existe");
        } else {
            Pokemon pokemon = pokemonMap.get(pokemonName);
            System.out.println("Nombre: " + pokemon.getName());
            System.out.println("Tipo 1: " + pokemon.getType1());
            System.out.println("Tipo 2: " + pokemon.getType2());
            System.out.println("Habilidades: " + pokemon.getHabilities());
        }
    }


}