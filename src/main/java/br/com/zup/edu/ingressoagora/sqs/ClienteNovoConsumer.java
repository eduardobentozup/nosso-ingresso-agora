package br.com.zup.edu.ingressoagora.sqs;

import br.com.zup.edu.ingressoagora.model.Cliente;
import br.com.zup.edu.ingressoagora.repository.ClienteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Component;

@Component
public class ClienteNovoConsumer {

    Logger logger = LoggerFactory.getLogger(ClienteNovoConsumer.class);

    @Autowired
    private ClienteRepository clienteRepository;


    @SqsListener(value = "${cloud.aws.queue.name}", deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
    public void send(ClienteNovo clienteNovo) {
        logger.info("Mensagem Recebida : {}", clienteNovo.toString());

        Cliente cliente = new Cliente(clienteNovo.getId(), clienteNovo.getNome(),
                clienteNovo.getEmail(), clienteNovo.getNascimento(),
                clienteNovo.getIncluidoEm(), clienteNovo.getAtualizadoEm());

        clienteRepository.save(cliente);

        logger.info("Cliente cadastrado pelo Listerner : {}", cliente.toString());
    }

}
