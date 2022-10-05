import java.util.Scanner;
import java.util.ArrayList;

public class ACMEEnergy {

    private Conglomerado conglomerado;

    FonteEnergia fonte;

    Combustivel combustivel;

    Scanner in = new Scanner (System.in);

    public ACMEEnergy() {
        conglomerado = new Conglomerado();
    }

	public void inicializa() {
       EnergiaNaoRenovavel eNaoRenovavel, eNaoRenovavel2;
       EnergiaRenovavel eRenovavel, eRenovavel2;
       eNaoRenovavel = new EnergiaNaoRenovavel("Cambu", 60.0000000, 3, "carvao", "50.000");
       conglomerado.cadastraUsina(eNaoRenovavel);
       eRenovavel = new EnergiaRenovavel("Itaipu", 70.000000, 4, "hidrica");
       conglomerado.cadastraUsina(eRenovavel);

        eNaoRenovavel2 = new EnergiaNaoRenovavel("Vale", 50.000000, 3, "petroleo", "40.000");
        conglomerado.cadastraUsina(eNaoRenovavel2);
        eRenovavel2 = new EnergiaRenovavel("Osorio", 50.000000, 3, "eolica");
        conglomerado.cadastraUsina(eRenovavel2); 
	} 

	public void executa() {
        int opcao;
        do {
            catalogoOpcoes();
            opcao = in.nextInt();
            in.nextLine();
            switch(opcao) {
                case 1:
                    cadastraUsina();
                    break;
                case 2:
                    pesquisaUsina();
                    break;
                case 3:
                    listaTodasUsinas();
                    break;
                case 4:
                    consultaPreco();
                    break;
                case 5:
                    salvaDadosArquivo();
                    break;
                case 0:
                    break;
            }
        }
        while(opcao != 0);
	}

    public void catalogoOpcoes() {
        System.out.println("========================================");
        System.out.println("Opcoes:");
        System.out.println("[1] Cadastrar usina");
        System.out.println("[2] Pesquisar usina");
        System.out.println("[3] Lista todas as usinas cadastradas");
        System.out.println("[4] Consulta o preço do MWh");
        System.out.println("[5] Salvar usinas cadastradas em arquivo");
        System.out.println("[0] Sair do sistema");
        System.out.println("========================================");
    }

    public void cadastraUsina() {
        Usina u = null;
        System.out.println("Insira o nome da usina: ");
        String nome = in.nextLine();
        if (conglomerado.pesquisaUsina(nome) != null) {
            System.out.println("Usina repetida");
        }
        else{
        System.out.println("Insira a produção MWh da usina: ");
        double precoMWh = in.nextDouble();
        System.out.println("Insira o custo por MWh da usina: ");
        double custoMWh = in.nextDouble();
        System.out.println("Tipo de energia: " + "\nDigite 1 para Renovável" + "\nDigite 2 para Não-Renovável");
        int tipo = in.nextInt();
        if (tipo == 1) {
            System.out.println("Você escolheu: Energia Renovável" + "\nAgora insira a fonte de energia da usina: ");
            in.nextLine();
            String font = in.nextLine();
            EnergiaRenovavel energiaRenovavel = new EnergiaRenovavel(nome, precoMWh, custoMWh, font);
            u = energiaRenovavel;
        }
        else if (tipo == 2) {
            System.out.println("Você escolheu: Energia não-renovável" + "\nAgora insira o combustível da usina: ");
            in.nextLine();
            String comb = in.nextLine();
            System.out.println("Por fim, insira a durablilidade do combustível: ");
            String durabilidade = in.nextLine();
            EnergiaNaoRenovavel energiaNaoRenovavel = new EnergiaNaoRenovavel(nome, precoMWh, custoMWh, comb, durabilidade);
            u = energiaNaoRenovavel;
        }
       else  System.out.println("Tipo de energia inválido");

        if (conglomerado.cadastraUsina(u)) {
            System.out.println("Usina cadastrada.");
        }
        else System.out.println("Usina não cadastrada");
    }
    }

    public void pesquisaUsina() {
        System.out.println("Insira o nome da usina: ");
        String nome = in.nextLine();
        Usina usina = conglomerado.pesquisaUsina(nome);
        if (usina == null) {
            System.out.println("Nenhuma usina localizada com este nome.");
        }
        System.out.println(usina.geraResumo());
        System.out.printf("Preço MWh: %.2f\n",  usina.calculaPrecoMWh());
    }

    public void listaTodasUsinas() {
        ArrayList<Usina> usinas = conglomerado.listaTodasUsinas();
        if(usinas.size() == 0) {
            System.out.println("Nenhuma usina cadastrada");
        }
        for (Usina u: usinas) {
            System.out.println(u.geraResumo());
            System.out.printf("Preço MWh: %.2f\n",  u.calculaPrecoMWh());
        }
    }

    public void consultaPreco() {
        System.out.println("Insira o nome da usina: ");
        String nome = in.nextLine();
        Usina usina = conglomerado.pesquisaUsina(nome);
        if (usina == null) {
            System.out.println("Nenhuma usina localizada com este nome.");
        }
        System.out.printf("Preço MWh: %.2f\n", usina.calculaPrecoMWh());
    }

    public void salvaDadosArquivo () {
       System.out.println("Insira o nome do arquivo: ");
       String nome = in.nextLine();
       conglomerado.salvaDadosArquivo(nome);
    }

}
