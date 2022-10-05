public class EnergiaRenovavel extends Usina {
    private double precoMWh;
    FonteEnergia fonte;

    public EnergiaRenovavel(String nome, double producaoMWh, double custoMWh, String font) {
        super(nome, producaoMWh, custoMWh);
        this.fonte = FonteEnergia.valueOf(font);
        
    }

    @Override
    public double calculaPrecoMWh() {
        double custo = getCustoMWh();
        if (fonte == FonteEnergia.solar) {
            precoMWh = custo * 1.25;
        }
        else if (fonte == FonteEnergia.eolica) {
            precoMWh = custo * 1.15;
        }
        else if (fonte == FonteEnergia.hidrica) {
            precoMWh = custo * 1.05;
        }
        return precoMWh;
    } 
 
    @Override
    public String geraResumo() {
        return (
            "\n" + "1" + ";" + getNome() + ";" + getProducaoMWh() + ";" + getCustoMWh() + ";" + 
            fonte 
        );
    }
    
}
