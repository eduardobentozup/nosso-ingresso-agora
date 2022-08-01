package br.com.zup.edu.ingressoagora.exception;

public class EventoExistenteException extends RuntimeException{
    public EventoExistenteException() {
        super("Já existe em nossa base da dos um evento cadastrado com mesmo nome na mesma data");
    }
}
