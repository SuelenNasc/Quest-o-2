import java.util.HashMap;

public class SistemaBancarioLegado {

    public LegacyResponse processarTransacao(HashMap<String, Object> parametros) {
        
        System.out.println("[LEGADO] Processando transação com parâmetros:");
        if (!parametros.containsKey("CARD_NUM") || 
            !parametros.containsKey("TX_AMOUNT") || 
            !parametros.containsKey("CURRENCY_CODE") ||
            !parametros.containsKey("TERMINAL_ID")) { 
            
            System.out.println("[LEGADO] ERRO: Parâmetros obrigatórios ausentes.");
            return new LegacyResponse(501, null); // 501 = Erro de parâmetros
        }

        String cartao = (String) parametros.get("CARD_NUM");
        double valor = (Double) parametros.get("TX_AMOUNT");
        int moeda = (Integer) parametros.get("CURRENCY_CODE");
        
        System.out.printf("[LEGADO] Autorizando Cartão: %s, Valor: %.2f, Moeda Código: %d\n", cartao, valor, moeda);
        
        String confirmationId = "LEGACY_ID_" + new java.util.Random().nextInt(10000);
        return new LegacyResponse(200, confirmationId); // 200 = OK
    }
}

class LegacyResponse {
    int status_code;
    String confirmation_id;

    public LegacyResponse(int status_code, String confirmation_id) {
        this.status_code = status_code;
        this.confirmation_id = confirmation_id;
    }
}