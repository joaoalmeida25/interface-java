package service;

import model.AluguelCarro;
import model.Fatura;

public class AluguelService {

    private Double precoPorDia;
    private Double precoPorHora;

    private TaxaService taxaService;

    public AluguelService(Double precoPorDia, Double precoPorHora, TaxaService taxaService) {
        this.precoPorDia = precoPorDia;
        this.precoPorHora = precoPorHora;
        this.taxaService = taxaService;
    }

    public void processarFatura(AluguelCarro aluguelCarro) {
        long inicio = aluguelCarro.getInicio().getTime();
        long fim = aluguelCarro.getFim().getTime();
        double horas = (double) (fim - inicio) / 1000 / 60 / 60;

        double pagamentoBasico;

        if (horas <= 12.0) {
//            Math.ceil(x) retorna o menor número inteiro maior ou igual a "x"
            pagamentoBasico = precoPorHora * Math.ceil(horas);
        } else {
            pagamentoBasico = precoPorDia * Math.ceil(horas / 24);
        }

        double taxa = taxaService.taxa(pagamentoBasico);

        aluguelCarro.setFatura(new Fatura(pagamentoBasico, taxa));
    }
}
