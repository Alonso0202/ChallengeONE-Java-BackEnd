import java.io.IOException;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ConsultaMoneda consultamoneda = new ConsultaMoneda();

        while (true) {
            System.out.println("Sea bienvenido/a al Conversor de Moneda =)");
            System.out.println("1) Dólar => Peso Argentino");
            System.out.println("2) Peso Argentino => Dólar");
            System.out.println("3) Dólar => Real brasileño");
            System.out.println("4) Real Brasileño => Dólar");
            System.out.println("5) Dólar => Peso Colombiano");
            System.out.println("6) Peso Colombiano => Dólar");
            System.out.println("7) Salir");
            System.out.print("Elija una opción válida: ");

            int opcion = scanner.nextInt();
            if (opcion == 7) {
                break;
            }

            try {
                ApiResponse response = consultamoneda.obtenerTasas();
                switch (opcion) {
                    case 1:
                        System.out.println("Tasa de conversión Dólar a Peso Argentino: " + response.getConversion_rate());
                        break;
                    case 2:
                        System.out.println("Tasa de conversión Peso Argentino a Dólar: " + (1 / response.getConversion_rate()));
                        break;
                    // Añadir más casos para las otras conversiones...
                    default:
                        System.out.println("Opción no válida");
                }
            } catch (IOException e) {
                System.out.println("Ocurrió un error: " + e.getMessage());
            }
        }

        scanner.close();
    }
}
