package br.com.zup.edu.ingressoagora.repository;

import br.com.zup.edu.ingressoagora.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface EventoRepository extends JpaRepository<Evento,Long> {

    Optional<Evento> findByTituloAndData(String Titulo, LocalDate data);
}
