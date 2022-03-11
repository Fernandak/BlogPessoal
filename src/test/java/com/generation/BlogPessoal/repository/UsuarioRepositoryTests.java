package com.generation.BlogPessoal.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.generation.BlogPessoal.model.Usuario;
import com.generation.BlogPessoal.service.UsuarioService;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuarioRepositoryTests {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private UsuarioRepository usuarioRepository;

	
	// aqui esta criando usuario
	@BeforeAll
	void start() {

		usuarioRepository.save(new Usuario (0L, "Ramon Daniel Santos","ramonzito@lovis.com","123456789"));

		usuarioRepository.save(new Usuario (0L, "Ene Biachi Santos","BiachiEne@lovis.com","12345mm"));

		usuarioRepository.save(new Usuario (0L, "Paula pincel Santos","pincel@lovis.com","senha6789"));

	}

	@Test
	@DisplayName("Retorna apenas um usuario")
	public void deveRetornarUmUsuario() {
		
		Optional<Usuario> usuario = usuarioRepository.findByUsuario("BiachiEne@lovis.com");
		assertTrue (usuario.get().getUsuario().equals("BiachiEne@lovis.com"));


	}
	@Test
	@DisplayName("Deve retornar 3 usuarios")
	public void deveRetornarTresUsuarios() {

		List<Usuario> listaDeUsuarios = usuarioRepository.findAllByNomeContainingIgnoreCase("Santos");
		assertEquals(3, listaDeUsuarios.size());
		assertTrue (listaDeUsuarios.get(0).getNome().equals("Ramon Daniel Santos"));
		assertTrue (listaDeUsuarios.get(1).getNome().equals("Ene Bianchi Santos"));
		assertTrue (listaDeUsuarios.get(2).getNome().equals("Paula pincel Santos"));
	}
	@AfterAll
	public void end() {
		usuarioRepository.deleteAll();
	}
	
}
