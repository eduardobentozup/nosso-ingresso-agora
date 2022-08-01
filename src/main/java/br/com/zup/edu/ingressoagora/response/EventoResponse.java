package br.com.zup.edu.ingressoagora.response;

import br.com.zup.edu.ingressoagora.model.Evento;

import java.math.BigDecimal;
import java.time.LocalDate;

public class EventoResponse {

    private Long id;

    private String titulo;

    private LocalDate data;

    private BigDecimal preco;

    public EventoResponse(Long id, String titulo, LocalDate data, BigDecimal preco) {
        this.id = id;
        this.titulo = titulo;
        this.data = data;
        this.preco = preco;
    }

    public static EventoResponse build(Evento evento) {
        return new EventoResponse(evento.getId(), evento.getTitulo(),
                evento.getData(), evento.getPreco());
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public LocalDate getData() {
        return data;
    }

    public BigDecimal getPreco() {
        return preco;
    }
}
