package br.com.zup.edu.ingressoagora.controller;

import br.com.zup.edu.ingressoagora.exception.EventoExistenteException;
import br.com.zup.edu.ingressoagora.model.Evento;
import br.com.zup.edu.ingressoagora.repository.EventoRepository;
import br.com.zup.edu.ingressoagora.request.EventoRequest;
import br.com.zup.edu.ingressoagora.response.EventoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/eventos")
public class EventoController {

    @Autowired
    private EventoRepository eventoRepository;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public EventoResponse inserir(@RequestBody EventoRequest request){

        eventoRepository.findByTituloAndData(request.getTitulo(), request.getData())
                .ifPresent(f -> {
                    throw new EventoExistenteException();
                });

        Evento evento = request.toModel();

        eventoRepository.save(evento);

        return EventoResponse.build(evento);

    }
}
