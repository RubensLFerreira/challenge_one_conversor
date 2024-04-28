public class Validador {
  public static boolean validarEntrada(String input) {
    if (input.equalsIgnoreCase("usd") ||
        input.equalsIgnoreCase("ars") ||
        input.equalsIgnoreCase("brl") ||
        input.equalsIgnoreCase("cop") ||
        input.equalsIgnoreCase("cny") ||
        input.equalsIgnoreCase("jpy")) {
      return true;
    } else {
      return false;
    }
  }
}