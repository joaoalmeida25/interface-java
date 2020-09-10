package app;

import model.AluguelCarro;
import model.Veiculo;
import service.AluguelService;
import service.TaxaBrasilService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class App {

    public static void main(String[] args) throws ParseException {

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        System.out.println("Entre com os dados do aluguel");
        System.out.print("Modelo do carro: ");
        String modelo = sc.nextLine();
        System.out.print("Data de retirada(dd/MM/yyyy HH:mm): ");
        Date retirada = sdf.parse(sc.nextLine());
        System.out.print("Data de retorno(dd/MM/yyyy HH:mm): ");
        Date retorno = sdf.parse(sc.nextLine());

        AluguelCarro aluguelCarro = new AluguelCarro(retirada, retorno, new Veiculo(modelo));

        System.out.print("Insira o preço por hora: ");
        double precoPorHora = sc.nextDouble();
        System.out.print("Insira o preço por dia: ");
        double precoPorDia = sc.nextDouble();

        AluguelService aluguelService = new AluguelService(precoPorDia, precoPorHora, new TaxaBrasilService());
        aluguelService.processarFatura(aluguelCarro);

        System.out.println();
        System.out.println("--------------------------------------------");
        System.out.println("                    FATURA                  ");
        System.out.println("Veiculo: " + aluguelCarro.getVeiculo().getModelo());
        System.out.println("Pagamento Basico: " + String.format("%.2f", aluguelCarro.getFatura().getPagamentoBasico()));
        System.out.println("Taxa: " + String.format("%.2f", aluguelCarro.getFatura().getTaxa()));
        System.out.println("Pagamento total: " + String.format("%.2f", aluguelCarro.getFatura().getPagamentoTotal()));
        System.out.println("--------------------------------------------");

        sc.close();
    }
}
