public class EnergiaNaoRenovavel extends Usina {
    private String durabilidade;
    private double precoMWh;
    Combustivel combustivel;

    public EnergiaNaoRenovavel(String nome, double producaoMWh, double custoMWh, String comb, String durabilidade) {
        super(nome, producaoMWh, custoMWh);
        this.durabilidade = durabilidade;
        this.combustivel = Combustivel.valueOf(comb);
    }

    public void setDurabilidade (String durabilidade) {
        this.durabilidade = durabilidade;
    }

    public String getDurabilidade() {
        return durabilidade;
    }

    @Override
    public double calculaPrecoMWh() {
        double custo = getCustoMWh();
        if (combustivel == Combustivel.petroleo) {
            precoMWh = custo * 1.3;
        }
        else if (combustivel == Combustivel.carvao) {
            precoMWh = custo * 1.2;
        }
        else if (combustivel == Combustivel.nuclear) {
            precoMWh = custo * 1.1;
        }
        return precoMWh;
    }

    @Override
    public String geraResumo() {
        return (
            "\n" + "2" + ";" + getNome() + ";" + getProducaoMWh() + ";" + getCustoMWh() + ";"
             + combustivel
        );
    }
}
