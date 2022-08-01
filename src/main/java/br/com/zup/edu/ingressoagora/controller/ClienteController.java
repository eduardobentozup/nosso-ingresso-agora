package br.com.zup.edu.ingressoagora.controller;

import br.com.zup.edu.ingressoagora.exception.ClienteExistenteException;
import br.com.zup.edu.ingressoagora.model.Cliente;
import br.com.zup.edu.ingressoagora.repository.ClienteRepository;
import br.com.zup.edu.ingressoagora.request.ClienteRequest;
import br.com.zup.edu.ingressoagora.response.ClienteResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ClienteResponse inserir(@RequestBody ClienteRequest request){
        clienteRepository.findByEmail(request.getEmail())
                .ifPresent(f -> {
                    throw new ClienteExistenteException();});

        Cliente cliente = request.toModel();

        clienteRepository.save(cliente);

        return ClienteResponse.build(cliente);
    }

}
