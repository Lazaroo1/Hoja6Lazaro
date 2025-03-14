import java.util.Map;
import java.util.Scanner;
import org.apache.commons.lang3.StringUtils;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Seleccione el tipo de map:");
        System.out.println("1. HashMap");
        System.out.println("2. TreeMap");
        System.out.println("3. LinkedHashMap");
        int choice = scanner.nextInt();
        scanner.nextLine();

        Map<String, Pokemon> pokemonMap = PokemonFactory.createMap(choice);
        PokemonManager manager = new PokemonManager(pokemonMap);
        manager.loadPokemonData("out/production/HOJA6LazaroDiaz24713/pokemon_data_pokeapi.csv");

        while (true) {
            System.out.println("\nMenú:");
            System.out.println("1. Agregar pokémon a tu colección");
            System.out.println("2. Mostrar datos de un pokemon");
            System.out.println("3. Mostrar tu colección ordenada por tipo1");
            System.out.println("4. Mostrar todos los pokemon ordenados por tipo1");
            System.out.println("5. Buscar pokemon por habilidad");
            System.out.println("6. Salir");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    System.out.println("Ingrese el nombre del pokémon:");
                    String name = scanner.nextLine();
                    manager.addToColeccion(name);
                    break;
                case 2:
                    System.out.println("Ingrese el nombre del pokemon:");
                    String pokemonName = scanner.nextLine();
                    manager.showDatosPokemon(pokemonName);
                    break;
                case 3:
                    manager.showColeccionByType();
                    break;
                case 4:
                    manager.showAllByType();
                    break;
                case 5:
                    System.out.println("Ingrese la habilidad:");
                    String hability = scanner.nextLine();
                    manager.findByHability(hability);
                    break;
                case 6:
                    System.out.println("Saliendo...");
                    return;
                default:
                    System.out.println("Ingresa un numero valido");
            }
        }
    }
}