import java.util.HashMap;

public class LegadoBancarioAdapter implements ProcessadorTransacoes {

    private final SistemaBancarioLegado legado;
    private static final String DEFAULT_TERMINAL_ID = "WEB_CLIENT_01";

    public LegadoBancarioAdapter(SistemaBancarioLegado legado) {
        this.legado = legado;
    }

    @Override
    public AutorizacaoResponse autorizar(String cartao, double valor, String moeda) {
        System.out.println("[ADAPTER] Recebida chamada moderna. Traduzindo para o legado...");

        HashMap<String, Object> parametrosLegados = new HashMap<>();
        parametrosLegados.put("CARD_NUM", cartao);
        parametrosLegados.put("TX_AMOUNT", valor);
        parametrosLegados.put("CURRENCY_CODE", converterMoedaParaCodigo(moeda));
        parametrosLegados.put("TERMINAL_ID", DEFAULT_TERMINAL_ID);

        LegacyResponse respostaLegada = legado.processarTransacao(parametrosLegados);

        return converterRespostaLegada(respostaLegada);
    }

    private int converterMoedaParaCodigo(String moeda) {
        switch (moeda.toUpperCase()) {
            case "USD": return 1;
            case "EUR": return 2;
            case "BRL": return 3;
            default: return 0;
        }
    }

    private AutorizacaoResponse converterRespostaLegada(LegacyResponse respostaLegada) {
        if (respostaLegada.status_code == 200) {
            System.out.println("[ADAPTER] Traduzindo resposta de sucesso (200) para o formato moderno.");
            return new AutorizacaoResponse(true, respostaLegada.confirmation_id, null);
        } else {
            System.out.println("[ADAPTER] Traduzindo resposta de erro (" + respostaLegada.status_code + ") para o formato moderno.");
            return new AutorizacaoResponse(false, null, "Erro no sistema legado: " + respostaLegada.status_code);
        }
    }
}