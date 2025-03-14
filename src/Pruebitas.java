
import java.util.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class Pruebitas {
    /**
     * Prueba para verificar que un Pokémon se agrega correctamente a la colección del usuario.
     */
    @Test
    void testAgregarPokemonAcoleccion() {
        Map<String, Pokemon> pokemonMap = new HashMap<>();
        PokemonManager manager = new PokemonManager(pokemonMap);
        pokemonMap.put("pikachu", new Pokemon("pikachu", "electric", "N/A", "static"));

        manager.addToColeccion("pikachu");
        assertTrue(manager.userCollection.contains(pokemonMap.get("pikachu")));
    }

    /**
     * Prueba para verificar que la búsqueda de un pokemon que no existe devuelve un error
     */
    @Test
    void testBuscarPokemonInexistente() {
        Map<String, Pokemon> pokemonMap = new HashMap<>();
        PokemonManager manager = new PokemonManager(pokemonMap);

        assertFalse(pokemonMap.containsKey("charizard"));
    }

    /**
     * Prueba para verificar que los pokemon no se pueden agregar 2 veces a la coleccion
     */
    @Test
    void testNoAgregarPokemonDuplicado() {
        Map<String, Pokemon> pokemonMap = new HashMap<>();
        PokemonManager manager = new PokemonManager(pokemonMap);
        pokemonMap.put("eevee", new Pokemon("eevee", "normal", "N/A", "run away"));

        manager.addToColeccion("eevee");
        manager.addToColeccion("eevee");

        assertEquals(1, manager.userCollection.size());
    }
}
