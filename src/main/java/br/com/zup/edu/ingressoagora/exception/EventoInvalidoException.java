package br.com.zup.edu.ingressoagora.exception;

public class EventoInvalidoException extends RuntimeException{
    public EventoInvalidoException() {
        super("Não foi possível encontrar o evento informado");
    }
}
