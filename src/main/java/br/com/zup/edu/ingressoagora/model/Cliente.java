package br.com.zup.edu.ingressoagora.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String email;

    private LocalDate nascimento;

    private LocalDateTime incluidoEm;

    private LocalDateTime atualizadoEm;

    public Cliente() {
    }

    public Cliente(String nome, String email, LocalDate nascimento) {
        this.nome = nome;
        this.email = email;
        this.nascimento = nascimento;
        this.incluidoEm = LocalDateTime.now();
        this.atualizadoEm = LocalDateTime.now();
    }

    public Cliente(Long id, String nome, String email, LocalDate nascimento, LocalDateTime incluidoEm, LocalDateTime atualizadoEm) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.nascimento = nascimento;
        this.incluidoEm = incluidoEm;
        this.atualizadoEm = atualizadoEm;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }

    public LocalDateTime getIncluidoEm() {
        return incluidoEm;
    }

    public LocalDateTime getAtualizadoEm() {
        return atualizadoEm;
    }


    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", nascimento=" + nascimento +
                ", incluidoEm=" + incluidoEm +
                ", atualizadoEm=" + atualizadoEm +
                '}';
    }
}
