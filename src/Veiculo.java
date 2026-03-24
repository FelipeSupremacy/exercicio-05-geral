public class Veiculo {
    String placa;
    String modelo;
    String marca;
    Proprietario proprietario;

    public Veiculo(String placa, String modelo, String marca, Proprietario proprietario) {
        this.placa = placa;
        this.modelo = modelo;
        this.marca = marca;
        this.proprietario = proprietario;
    }
}