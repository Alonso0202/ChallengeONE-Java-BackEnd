import java.io.IOException;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ConsultaMoneda consultamoneda = new ConsultaMoneda();

        while (true) {
            System.out.println("******************************************");
            System.out.println("Sea bienvenido/a al Conversor de Moneda =)");
            System.out.println("1) Dólar => Peso Argentino");
            System.out.println("2) Peso Argentino => Dólar");
            System.out.println("3) Dólar => Real Brasileño");
            System.out.println("4) Real Brasileño => Dólar");
            System.out.println("5) Dólar => Peso Colombiano");
            System.out.println("6) Peso Colombiano => Dólar");
            System.out.println("7) Salir");
            System.out.println("Elija una opción válida: ");
            System.out.println("******************************************");


            int opcion = scanner.nextInt();
            if (opcion == 7) {
                break;
            }

            // Variables para monedas de origen y destino
            String monedaOrigen = "";
            String monedaDestino = "";

            switch (opcion) {
                case 1:
                    monedaOrigen = "USD";
                    monedaDestino = "ARS";
                    break;
                case 2:
                    monedaOrigen = "ARS";
                    monedaDestino = "USD";
                    break;
                case 3:
                    monedaOrigen = "USD";
                    monedaDestino = "BRL";
                    break;
                case 4:
                    monedaOrigen = "BRL";
                    monedaDestino = "USD";
                    break;
                case 5:
                    monedaOrigen = "USD";
                    monedaDestino = "COP";
                    break;
                case 6:
                    monedaOrigen = "COP";
                    monedaDestino = "USD";
                    break;
                default:

                    System.out.println("Opción no válida");
                    continue; // Volver a mostrar el menú
            }

            // Pedir al usuario que ingrese la cantidad de la moneda origen
            System.out.print("Ingrese la cantidad de " + monedaOrigen + " que deseas convertir: ");
            double cantidad = scanner.nextDouble();

            try {
                // Pasar las monedas seleccionadas a la consulta de tasas
                ApiResponse response = consultamoneda.obtenerTasas(monedaOrigen, monedaDestino);

                // Calcular la cantidad convertida
                double resultado = cantidad * response.getConversion_rate();
                System.out.println("La conversión de " + cantidad + " " + monedaOrigen + " es = " + resultado + " " + monedaDestino);
            } catch (IOException e) {
                System.out.println("Ocurrió un error: " + e.getMessage());
            }
        }

        scanner.close();
    }
}
