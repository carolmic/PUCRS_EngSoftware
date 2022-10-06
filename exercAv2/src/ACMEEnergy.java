import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;

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
        boolean ok;
        int opcao = 0;
        do {
        do {
            ok = true;
            catalogoOpcoes();
            try {
            opcao = in.nextInt();
            } catch (InputMismatchException e) {
                in.nextLine();
                ok = false;
                System.out.println("Tipo incorreto. Redigite.");
            } catch (Exception e1) {
                in.nextLine();
                ok = false;
                e1.printStackTrace();
                System.out.println("Redigite.");
            }
       } while (!ok);
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
    } while (true);
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
        boolean ok;
        Usina u = null;

        System.out.println("Insira o nome da usina: ");
        String nome = in.nextLine();
        if (conglomerado.pesquisaUsina(nome) != null) {
            System.out.println("Usina repetida");
            return;
        }
        else{

        System.out.println("Insira a produção MWh da usina: ");
        double precoMWh = 0;
        do {
            ok = true;
            try {
                precoMWh = in.nextDouble();
            } catch (InputMismatchException e3) {
                in.nextLine();
                ok = false;
                System.out.println("Tipo incorreto. Redigite");
            } catch (Exception e4) {
                in.nextLine();
                ok = false;
                e4.printStackTrace();
                System.out.println("Redigite.");
            }
        } while (!ok);
        in.nextLine();

        System.out.println("Insira o custo por MWh da usina: ");
        double custoMWh = 0;
        do {
            ok = true;
            try {
                custoMWh = in.nextDouble();
            } catch (InputMismatchException e5) {
                in.nextLine();
                ok = false;
                System.out.println("Tipo incorreto. Redigite");
            } catch (Exception e6) {
                in.nextLine();
                ok = false;
                e6.printStackTrace();
                System.out.println("Redigite.");
            }
        } while (!ok);
        in.nextLine();
        
        System.out.println("Tipo de energia: " + "\nDigite 1 para Renovável" + "\nDigite 2 para Não-Renovável");
        int tipo = 0;
        do {
            ok = true;
            try {
                tipo = in.nextInt();
            } catch (InputMismatchException e7) {
                in.nextLine();
                ok = false;
                System.out.println("Tipo incorreto. Redigite.");
            } catch (Exception e8) {
                in.nextLine();
                ok = false;
                e8.printStackTrace();
                System.out.println("Redigite");
            }
        } while (!ok);
        in.nextLine();
        
        if (tipo == 1) {
            System.out.println("Você escolheu: Energia Renovável" + "\nAgora insira a fonte de energia da usina, que pode ser: solar, eolica ou hidrica ");
            String font = "";
            EnergiaRenovavel energiaRenovavel = null;;
            do {
                ok = true;
                font = in.nextLine();
                try {
                    energiaRenovavel = new EnergiaRenovavel(nome, precoMWh, custoMWh, font);
                } catch (IllegalArgumentException e9) {
                    ok = false;
                    System.out.println("Fonte inexistente. Redigite.");
                } catch (Exception e10) {
                    ok = false;
                    e10.printStackTrace();
                    System.out.println("Redigite.");
                }
            } while (!ok);
            u = energiaRenovavel;
        }
        else if (tipo == 2) {
            System.out.println("Você escolheu: Energia não-renovável" + "\nAgora insira o combustível da usina, que pode ser: petroleo, carvao ou nuclear ");
            String comb = "";
            EnergiaNaoRenovavel energiaNaoRenovavel= null;
            do {
                ok = true;
                comb = in.nextLine();
                System.out.println("Por fim, insira a durablilidade do combustível: ");
                String durabilidade = in.nextLine();
                try {
                  energiaNaoRenovavel = new EnergiaNaoRenovavel(nome, precoMWh, custoMWh, comb, durabilidade);
                } catch (IllegalArgumentException e11) {
                    ok = false;
                    System.out.println("Combustível inexistente. Redigite.");
                } catch (Exception e12) {
                    ok = false;
                    e12.printStackTrace();
                    System.out.println("Redigite");
                }
            } while (!ok);
            u = energiaNaoRenovavel;
        }
       else { 
            System.out.println("Tipo de energia inválido"); 
            return;
        }

        if (conglomerado.cadastraUsina(u)) {
            System.out.println("Usina cadastrada.");
        }
        else {
            System.out.println("Usina não cadastrada");
            return; 
        }
    }
    }

    public void pesquisaUsina() {
        System.out.println("Insira o nome da usina: ");
        String nome = in.nextLine();
        if(conglomerado.pesquisaUsina(nome) == null) {
            System.out.println("Nenhuma usina localizada com este nome.");
            return;
        }
        Usina usina = conglomerado.pesquisaUsina(nome);
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
        if(conglomerado.pesquisaUsina(nome) == null) {
            System.out.println("Nenhuma usina localizada com este nome.");
            return;
        }
        Usina usina = conglomerado.pesquisaUsina(nome);
        System.out.printf("Preço MWh: %.2f\n", usina.calculaPrecoMWh());
    }

    public void salvaDadosArquivo () {
       System.out.println("Insira o nome do arquivo: ");
       String nome = in.nextLine();
       conglomerado.salvaDadosArquivo(nome);
    }

}
