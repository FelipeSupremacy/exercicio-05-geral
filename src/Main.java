import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static Veiculo[] veiculo = new Veiculo[5];
    static int indexVeiculo;
    static Registro[] registro = new Registro[20];
    static int indexRegistro;

    public static void main(String[] args) {
        int opcao;

        do {
            System.out.println("#### Estacionamento ParkEasy ####");
            System.out.println("[1] Entrada de veículo");
            System.out.println("[2] Saída de veículo");
            System.out.println("[3] Imprimir veículos estacionados");
            System.out.println("[4] Imprimir a receita");
            System.out.println("[5] Finalizar");
            opcao = sc.nextInt();

            switch(opcao) {
                case 1 -> registrarEntrada();
                case 2 -> RegistrarSaida();
                case 3 -> estacionados();
                case 4 -> imprimirReceita();
                case 5 -> System.out.println("ParkEasy agradece");
                default -> System.out.println("Opção inválida");
            }
            System.out.println();
        } while(opcao != 5);

    }

    private static void imprimirReceita() {
        double valor = 0;
        for (int i = 0; i < indexRegistro; i++) {
            if (registro[i].horaSaida != null) {
                valor += registro[i].calcularValor();
            }
        }
        System.out.println("Receita Total R$ " + valor);;
    }

    private static void RegistrarSaida() {
        String horaSaida;
        double valor;
        Registro registro = pesquisarRegistro();
        if (registro != null) {
            System.out.print("Hora de saída (hh:mm) --> ");
            horaSaida = sc.next();
            registro.horaSaida = horaSaida;
            valor = registro.calcularValor();
            System.out.println("Valor total a pagar R$ " + valor);
        }
    }

    private static Registro pesquisarRegistro() {
            String placa;
            System.out.print("Placa para pesquisa --> ");
            placa = sc.next().toUpperCase();
            for(int i = 0; i < indexRegistro; i++) {
                if(registro[i].veiculo.placa.equals(placa)) {
                    return registro[i];
                }
            }
            System.out.println("Veículo não encontrado");
            return null;
        }



    private static void estacionados() {
        for(int i = 0; i < indexRegistro; i++) {
            if (registro[i].horaSaida == null)
            System.out.println(registro[i].veiculo.placa);
        }
    }

    private static void registrarEntrada() {
        String nome;
        String marca, modelo, placa;
        long cpf;
        String horaEntrada;

        Veiculo veiculoEncontrado = pesquisar();

        if(veiculoEncontrado == null) {
            System.out.print("Nome do proprietário --> ");
            nome = sc.next();
            System.out.print("CPF --> ");
            cpf = sc.nextLong();
            System.out.print("Placa --> ");
            placa = sc.next().toUpperCase();
            System.out.print("Marca --> ");
            marca = sc.next();
            System.out.print("Modelo --> ");
            modelo = sc.next();
            Proprietario proprietario = new Proprietario(nome, cpf);

            veiculo[indexVeiculo] = new Veiculo(placa, modelo, marca, proprietario);
            veiculoEncontrado = veiculo[indexRegistro];
            indexVeiculo++;
        }
        System.out.print("Hora de entrada (hh:mm) --> ");
        horaEntrada = sc.next();
        registro[indexRegistro] = new Registro(veiculoEncontrado, horaEntrada);
        indexRegistro++;



    }

    private static Veiculo pesquisar() {
        String placa;
        System.out.print("Placa para pesquisa --> ");
        placa = sc.next().toUpperCase();
        for(int i = 0; i < indexVeiculo; i++) {
            if(veiculo[i].placa.equals(placa)) {
                return veiculo[i];
            }
        }
        System.out.println("Veículo não encontrado");
        return null;
    }
}
