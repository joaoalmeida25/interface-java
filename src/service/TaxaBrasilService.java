package service;

public class TaxaBrasilService implements TaxaService {

    public double taxa(double valor) {
        if (valor <= 100.0) {
            return valor * 0.2;
        } else {
            return valor * 0.15;
        }
    }
}
