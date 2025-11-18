public class Main {
    public static void main(String[] args) {
        
        SistemaBancarioLegado sistemaLegado = new SistemaBancarioLegado();
        ProcessadorTransacoes processador = new LegadoBancarioAdapter(sistemaLegado);

        System.out.println("--- Iniciando Transação 1 (BRL) ---");
        AutorizacaoResponse resp1 = processador.autorizar("1234-5678-9012-3456", 150.75, "BRL");
        System.out.println(resp1);
        
        System.out.println("\n--- Iniciando Transação 2 (USD) ---");
        AutorizacaoResponse resp2 = processador.autorizar("9876-5432-1098-7654", 99.00, "USD");
        System.out.println(resp2);
    }
}