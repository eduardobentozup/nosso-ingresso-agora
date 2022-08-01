package br.com.zup.edu.ingressoagora.controller;

import br.com.zup.edu.ingressoagora.exception.ClienteInvalidoException;
import br.com.zup.edu.ingressoagora.exception.EventoInvalidoException;
import br.com.zup.edu.ingressoagora.model.Cliente;
import br.com.zup.edu.ingressoagora.model.Evento;
import br.com.zup.edu.ingressoagora.model.Ingresso;
import br.com.zup.edu.ingressoagora.repository.ClienteRepository;
import br.com.zup.edu.ingressoagora.repository.EventoRepository;
import br.com.zup.edu.ingressoagora.repository.IngressoRepository;
import br.com.zup.edu.ingressoagora.request.IngressoRequest;
import br.com.zup.edu.ingressoagora.response.IngressoResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/ingressos")
public class IngressoController {

    Logger logger = LoggerFactory.getLogger(IngressoController.class);

    @Autowired
    private IngressoRepository ingressoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EventoRepository eventoRepository;


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public IngressoResponse inserir(@RequestBody IngressoRequest request){
        Cliente cliente = clienteRepository.findById(request.getIdCliente())
                .orElseThrow(ClienteInvalidoException::new);

        Evento evento = eventoRepository.findById(request.getIdEvento())
                .orElseThrow(EventoInvalidoException::new);

        Ingresso ingresso = new Ingresso(evento, cliente);
        ingressoRepository.save(ingresso);

        return IngressoResponse.build(ingresso);
    }

}
