package br.com.zup.edu.ingressoagora.response;

import br.com.zup.edu.ingressoagora.model.EstadoIngresso;
import br.com.zup.edu.ingressoagora.model.Ingresso;

import java.time.LocalDateTime;

public class IngressoResponse {

    private Long id;

    private EstadoIngresso estado;

    private LocalDateTime compradoEm;

    private Long idEvento;

    private Long idCliente;


    public Long getId() {
        return id;
    }

    public EstadoIngresso getEstado() {
        return estado;
    }

    public LocalDateTime getCompradoEm() {
        return compradoEm;
    }

    public Long getIdEvento() {
        return idEvento;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public IngressoResponse(Long id, EstadoIngresso estado, LocalDateTime compradoEm, Long idEvento, Long idCliente) {
        this.id = id;
        this.estado = estado;
        this.compradoEm = compradoEm;
        this.idEvento = idEvento;
        this.idCliente = idCliente;
    }

    public static IngressoResponse build(Ingresso ingresso) {
        return new IngressoResponse(ingresso.getId(),
                ingresso.getEstado(), ingresso.getCompradoEm(),
                ingresso.getCliente().getId(), ingresso.getEvento().getId());
    }
}
