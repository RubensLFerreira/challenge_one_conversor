import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner prompt = new Scanner(System.in);

    System.out.println("Seja bem-vindo(a) ao Conversor de Moeda\n");
    String sair = "";

    while (!sair.equalsIgnoreCase("sair")) {
      System.out.println("Digite o código da moeda que deseja converte\n");
      System.out.println("""
          1) Dólar Americano (USD)
          2) Peso Argentino (ARS)
          3) Real brasileiro (RBL)
          4) Peso colombiano (COP)
          5) Yuan Chinesa (CNY)
          6) Iene Japones (JPY)
          7) Sair          
          """);

      System.out.println("Digite o código de qual moeda deseja converter: ");
      String numberOne = prompt.nextLine();

      if (numberOne.equalsIgnoreCase("7")) {
        break;
      }

      System.out.println("\nDigite o valor que deseja converter: ");
      String value = prompt.nextLine();

      System.out.println("\nDigite o código para qual moeda deseja converter: ");
      String numberTwo = prompt.nextLine();

      if (numberTwo.equalsIgnoreCase("7")) {
        break;
      }

      System.out.println("Primeiro código: " + numberOne + ", Valor: " + value + " Segundo código: " + numberTwo);
    }
  }
}