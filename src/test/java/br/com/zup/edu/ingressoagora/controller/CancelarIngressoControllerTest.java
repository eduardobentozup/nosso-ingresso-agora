package br.com.zup.edu.ingressoagora.controller;

import br.com.zup.edu.ingressoagora.model.EstadoIngresso;
import br.com.zup.edu.ingressoagora.model.Evento;
import br.com.zup.edu.ingressoagora.model.Ingresso;
import br.com.zup.edu.ingressoagora.repository.EventoRepository;
import br.com.zup.edu.ingressoagora.repository.IngressoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(printOnlyOnFailure = false)
class CancelarIngressoControllerTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private IngressoRepository ingressoRepository;

	@Autowired
	private EventoRepository eventoRepository;

	private final String URL = "/ingressos/{id}/cancelamento";

	@BeforeEach
	void setUp() {
		ingressoRepository.deleteAll();
		eventoRepository.deleteAll();
	}

	@Test
	@DisplayName("Should cancel a ticket")
	void shouldCancelTicket() throws Exception {

		Evento devConf = new Evento(
			"TheDevConf",
			LocalDate.now().plusDays(2),
			new BigDecimal(100)
		);

		eventoRepository.save(devConf);

		Ingresso ticket = new Ingresso(devConf);
		ingressoRepository.save(ticket);

		MockHttpServletRequestBuilder request = patch(URL, ticket.getId())
			.contentType(MediaType.APPLICATION_JSON);

		mvc.perform(request)
			.andExpect(status().isNoContent());

		Optional<Ingresso> possibleUpdatedTicket = ingressoRepository.findById(ticket.getId());

		assertTrue(possibleUpdatedTicket.isPresent());
		assertEquals(EstadoIngresso.CANCELADO, possibleUpdatedTicket.get().getEstado());
	}

	@Test
	@DisplayName("Should not cancel when the ticket does not exist")
	void shouldNotCancelWhenTicketDoesNotExist() throws Exception {

		MockHttpServletRequestBuilder request = patch(URL, Integer.MAX_VALUE)
			.contentType(MediaType.APPLICATION_JSON);

		Exception exception = mvc.perform(request)
			.andExpect(status().isNotFound())
			.andReturn()
			.getResolvedException();

		assertNotNull(exception);
		assertTrue(exception instanceof ResponseStatusException);
		assertEquals("Este ingresso não existe.", ((ResponseStatusException) exception).getReason());

	}

	@Test
	@DisplayName("Should not cancel by invalid date ")
	void shouldNotCancelByInvalidDate() throws Exception {

		Evento devConf = new Evento(
			"TheDevConf",
			LocalDate.now(),
			new BigDecimal(100)
		);
		eventoRepository.save(devConf);

		Ingresso ticket = new Ingresso(devConf);
		ingressoRepository.save(ticket);

		MockHttpServletRequestBuilder request = patch(URL, ticket.getId())
			.contentType(MediaType.APPLICATION_JSON);

		Exception exception = mvc.perform(request)
			.andExpect(status().isUnprocessableEntity())
			.andReturn()
			.getResolvedException();

		assertNotNull(exception);
		assertTrue(exception instanceof ResponseStatusException);
		assertEquals(
			"Não é possivel cancelar faltando menos de 1 dia para data do evento",
			((ResponseStatusException) exception).getReason()
		);

		Optional<Ingresso> possibleTicket = ingressoRepository.findById(ticket.getId());

		assertTrue(possibleTicket.isPresent());
		assertEquals(EstadoIngresso.NAOCONSUMIDO, possibleTicket.get().getEstado());
	}

	@Test
	@DisplayName("Should not cancel when ticket already canceled ")
	void shouldNotCancelWhenTicketAlreadyCanceled() throws Exception {

		Evento devConf = new Evento(
			"TheDevConf",
			LocalDate.now().plusDays(2),
			new BigDecimal(100)
		);
		eventoRepository.save(devConf);

		Ingresso ticket = new Ingresso(devConf);
		ticket.consumir();

		ingressoRepository.save(ticket);

		MockHttpServletRequestBuilder request = patch(URL, ticket.getId())
			.contentType(MediaType.APPLICATION_JSON);

		Exception exception = mvc.perform(request)
			.andExpect(status().isUnprocessableEntity())
			.andReturn()
			.getResolvedException();

		assertNotNull(exception);
		assertTrue(exception instanceof ResponseStatusException);
		assertEquals(
			"Impossivel cancelar um Ingresso já consumido.",
			((ResponseStatusException) exception).getReason()
		);
	}
}