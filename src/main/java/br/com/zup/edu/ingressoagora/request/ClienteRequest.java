package br.com.zup.edu.ingressoagora.request;

import br.com.zup.edu.ingressoagora.model.Cliente;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class ClienteRequest {

    private String nome;

    private String email;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate nascimento;

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
    }

    public String getEmail() {
        return email;
    }

    public Cliente toModel() {
        return new Cliente(this.nome, this.email, this.nascimento);
    }
}
