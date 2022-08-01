package br.com.zup.edu.ingressoagora.request;

import br.com.zup.edu.ingressoagora.model.Evento;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.time.LocalDate;

public class EventoRequest {

    private String titulo;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate data;

    private BigDecimal preco;

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public String getTitulo() {
        return titulo;
    }

    public LocalDate getData() {
        return data;
    }

    public Evento toModel() {
        return new Evento(this.titulo, this.data, this.preco);
    }
}
