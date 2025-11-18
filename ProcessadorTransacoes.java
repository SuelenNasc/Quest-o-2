public interface ProcessadorTransacoes {
    AutorizacaoResponse autorizar(String cartao, double valor, String moeda);
}

class AutorizacaoResponse {
    boolean sucesso;
    String codigoAutorizacao;
    String mensagemErro;

    public AutorizacaoResponse(boolean sucesso, String codigoAutorizacao, String mensagemErro) {
        this.sucesso = sucesso;
        this.codigoAutorizacao = codigoAutorizacao;
        this.mensagemErro = mensagemErro;
    }

    @Override
    public String toString() {
        if (sucesso) {
            return "Resposta Moderna [Sucesso: " + sucesso + ", Código: " + codigoAutorizacao + "]";
        } else {
            return "Resposta Moderna [Sucesso: " + sucesso + ", Erro: " + mensagemErro + "]";
        }
    }
}