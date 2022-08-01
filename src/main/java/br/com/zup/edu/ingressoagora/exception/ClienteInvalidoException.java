package br.com.zup.edu.ingressoagora.exception;

public class ClienteInvalidoException extends RuntimeException{

    public ClienteInvalidoException() {
        super("Não foi possível encontrar o Cliente informado");
    }
}
