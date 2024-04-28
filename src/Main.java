import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    pegarDados();
  }

  public static void pegarDados() {
    Scanner prompt = new Scanner(System.in);
    String primeiroCodigo;
    String segundoCodigo;
    String valor;
    ArrayList<String> lista = new ArrayList<>();

    System.out.println("\nSeja bem-vindo(a) ao Conversor de Moedas");

    while (true) {
      System.out.println("+================================================+");
      System.out.println("Digite o CÓDIGO da moeda que deseja converter:\n");
      System.out.println("[USD] Dólar Americano");
      System.out.println("[ARS] Peso Argentino");
      System.out.println("[BRL] Real brasileiro");
      System.out.println("[COP] Peso colombiano");
      System.out.println("[CNY] Yuan Chinesa");
      System.out.println("[JPY] Iene Japonês");
      System.out.println("\nDigite 'sair' para sair");
      System.out.println("+================================================+");

      primeiroCodigo = prompt.nextLine();

      if (primeiroCodigo.equalsIgnoreCase("sair")) {
        break;
      } else if (!Validador.validarEntrada(primeiroCodigo)) {
        System.out.println("\n[ERRO] Código inserido é inválido, tente novamente!\n");
        continue;
      }

      System.out.println("\nDigite o valor que deseja converter:");
      valor = prompt.nextLine();

      System.out.println("\nDigite o código para qual moeda deseja converter:");
      segundoCodigo = prompt.nextLine();

      if (segundoCodigo.equalsIgnoreCase("sair")) {
        break;
      } else if (!Validador.validarEntrada(segundoCodigo)) {
        System.out.println("\n[ERRO] Código inserido é inválido, tente novamente!\n");
        continue;
      }

      lista.add(primeiroCodigo);
      lista.add(segundoCodigo);
      lista.add(valor);
      break;
    }
    requisicaoParaApi(lista);
  }

  public static void requisicaoParaApi(ArrayList<String> lista) {
    Scanner prompt = new Scanner(System.in);

    String endereco = "https://v6.exchangerate-api.com/v6/d75c9e7c28d65120f30ac3f4/pair/" +
        lista.get(0) + "/" + lista.get(1) + "/" + lista.get(2);

    try {
      HttpClient client = HttpClient.newHttpClient();
      HttpRequest request = HttpRequest.newBuilder()
          .uri(URI.create(endereco))
          .build();

      HttpResponse<String> response = client
          .send(request, HttpResponse.BodyHandlers.ofString());

      String json = response.body();
      JsonObject cotacoes = JsonParser.parseString(json).getAsJsonObject();
      double cotacao = cotacoes.get("conversion_result").getAsDouble();

      System.out.println("+================================================+");
      System.out.println("O valor de " + lista.get(2) + "$ em " + lista.get(0).toUpperCase() +
          " equivale a " + cotacao + " em " + lista.get(1).toUpperCase());
      System.out.println("+================================================+");

      System.out.println("\nDeseja fazer uma nova consulta? (s/n)");
      String continua = prompt.nextLine();

      if (continua.equalsIgnoreCase("s")) {
        pegarDados();
      }
    } catch (Exception e) {
      System.out.println("Houve um erro na requisição!");
      System.out.println(e.getMessage());
    }
  }
}
