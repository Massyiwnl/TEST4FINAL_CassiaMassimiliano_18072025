import java.util.ArrayList;
import java.util.Scanner;

// Classe base: Veicolo (incapsulamento)
abstract class Veicolo {
    private String marca;
    private int anno;

    public Veicolo(String marca, int anno) {
        this.marca = marca;
        this.anno = anno;
    }

    public String getMarca() {
        return marca;
    }
    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getAnno() {
        return anno;
    }
    public void setAnno(int anno) {
        this.anno = anno;
    }

    // Metodo polimorfico
    public abstract String dettagli();
}

// Sottoclasse Auto
class Auto extends Veicolo {
    private int porte;

    public Auto(String marca, int anno, int porte) {
        super(marca, anno);
        this.porte = porte;
    }

    public int getPorte() {
        return porte;
    }
    public void setPorte(int porte) {
        this.porte = porte;
    }

    @Override
    public String dettagli() {
        return "Auto - Marca: " + getMarca() + ", Anno: " + getAnno() + ", Porte: " + porte;
    }
}
class Moto extends Veicolo {
    private int cilindrata; 

    public Moto(String marca, int anno, int cilindrata) {
        super(marca, anno);
        this.cilindrata = cilindrata;
    }

    public int getCilindrata() {
        return cilindrata;
    }
    public void setCilindrata(int cilindrata) {
        this.cilindrata = cilindrata;
    }

    @Override
    public String dettagli() {
        return "Moto - Marca: " + getMarca() + ", Anno: " + getAnno() + ", Cilindrata: " + cilindrata + "cc";
    }
}

public class Esercizio1 {
    public static void main(String[] args) {
        ArrayList<Veicolo> veicoli = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        int scelta;

        do {
            System.out.println("\nMENU GESTIONE VEICOLI");
            System.out.println("1. Aggiungi Auto");
            System.out.println("2. Aggiungi Moto");
            System.out.println("3. Visualizza tutti i veicoli");
            System.out.println("0. Esci");
            System.out.print("Scegli un'opzione: ");
            scelta = scanner.nextInt();
            scanner.nextLine(); // Consuma invio

            switch (scelta) {
                case 1:
                    System.out.print("Marca: ");
                    String marcaA = scanner.nextLine();
                    System.out.print("Anno: ");
                    int annoA = scanner.nextInt();
                    System.out.print("Numero porte: ");
                    int porte = scanner.nextInt();
                    veicoli.add(new Auto(marcaA, annoA, porte));
                    System.out.println("Auto aggiunta.");
                    break;
                case 2:
                    System.out.print("Marca: ");
                    String marcaM = scanner.nextLine();
                    System.out.print("Anno: ");
                    int annoM = scanner.nextInt();
                    System.out.print("Cilindrata (cc): ");
                    int cilindrata = scanner.nextInt();
                    veicoli.add(new Moto(marcaM, annoM, cilindrata));
                    System.out.println("Moto aggiunta.");
                    scanner.nextLine(); // Consuma invio rimasto
                    break;
                case 3:
                    System.out.println("\nDettagli Veicoli");
                    if (veicoli.isEmpty()) {
                        System.out.println("Nessun veicolo presente.");
                    } else {
                        for (Veicolo v : veicoli) {
                            System.out.println(v.dettagli());
                        }
                    }
                    break;
                case 0:
                    System.out.println("Arrivederci!");
                    break;
                default:
                    System.out.println("Opzione non valida.");
            }
        } while (scelta != 0);

        scanner.close();
    }
}
