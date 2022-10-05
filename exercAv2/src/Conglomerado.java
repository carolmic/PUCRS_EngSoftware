import java.nio.charset.Charset;
import java.nio.file.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.ArrayList;

public class Conglomerado {
    private ArrayList<Usina> usinas;
    private double precoMWh;
    

    public Conglomerado() {
        usinas = new ArrayList<Usina>();
    }
    
	public boolean cadastraUsina(Usina usina) {
        for (Usina usinaCad: usinas) {
            if (usinaCad.getNome().equals(usina.getNome())) {
                return false;
            }
        }
        usinas.add(usina);
		return true;
	}

	public Usina pesquisaUsina(String nome) {
        for (Usina usinaCad: usinas) {
            if (usinaCad.getNome().equals(nome)) {
                return usinaCad;
            }
        }
		return null;
	}

	public ArrayList<Usina> listaTodasUsinas() {
        if (isEmpty()) {
            return null;
        }
        return usinas;
		//return (ArrayList<Usina>)usinas.clone();
	}

	public double consultaPreco(String nome) {
        Usina usina = pesquisaUsina(nome);
        if (pesquisaUsina(nome) == null) {
            return -1;
        }
        precoMWh = usina.calculaPrecoMWh();
        return precoMWh;
	}

	public boolean salvaDadosArquivo(String nomeArquivo) {
       Path path = Paths.get(nomeArquivo);
       try(PrintWriter writer = new PrintWriter(Files.newBufferedWriter(path, Charset.defaultCharset()))) {
            for (Usina u: usinas) {
                writer.format(u.geraResumo());
            }
        }
        catch (IOException e) {
          System.err.format("Erro de E/S: %s%n", e);
          return false;
        }
        System.out.println("Arquivo gravado!");
		return true;
	}

    public boolean isEmpty() {
        if (usinas == null) {
            return true;
        }
        return false;
    }
   
}
