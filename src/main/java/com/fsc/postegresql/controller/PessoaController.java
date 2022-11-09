package com.fsc.postegresql.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fsc.postegresql.model.Pessoa;
import com.fsc.postegresql.repository.PessoaRepository;

@CrossOrigin(origins = "http://localhost:8085")
@RestController
@RequestMapping("/api")
public class PessoaController {

	@Autowired
	PessoaRepository pessoaRepository;
	
	
	@GetMapping("/pessoa")
	public ResponseEntity<List<Pessoa>> getTodasPessoas(@RequestParam(required = false) String nome){
		try {
			
			List<Pessoa> pessoas = new ArrayList<>();
			
			if(nome == null) 
				pessoaRepository.findAll().forEach(pessoas::add);
			else
				pessoaRepository.findByNomeContaining(nome).forEach(pessoas::add);
				
			if (pessoas.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			
			return new ResponseEntity<>(pessoas, HttpStatus.OK);
			
			
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/pessoa/{id}")
	public ResponseEntity<Pessoa> ObterPorId(@PathVariable("id") Long id){
		Optional<Pessoa> pessoaId = pessoaRepository.findById(id);
		
		if(pessoaId.isPresent()) {
			return new ResponseEntity<>(pessoaId.get(), HttpStatus.OK);
		}else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	
	
	
	@PostMapping("/pessoa")
	public ResponseEntity<Pessoa> criarPessoa(@RequestBody Pessoa pessoa){
		try {
			
			Pessoa addPessoa = pessoaRepository
					.save(new Pessoa(pessoa.getNome(), pessoa.getIdade(), pessoa.getSalario(), false));
			return new ResponseEntity<>(addPessoa, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/pessoa/{id}")
	public ResponseEntity<Pessoa> atualizar(@PathVariable Long id, @RequestBody Pessoa pessoa){
		Optional<Pessoa> modPessoa = pessoaRepository.findById(id);
		if(modPessoa.isPresent()) {
			Pessoa addPessoa = modPessoa.get();
			
			addPessoa.setNome(pessoa.getNome());
			addPessoa.setIdade(pessoa.getIdade());
			addPessoa.setSalario(pessoa.getSalario());
			addPessoa.setCristao(pessoa.getCristao());
			return new ResponseEntity<>(pessoaRepository.save(addPessoa), HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
		@DeleteMapping("/pessoa")
		public ResponseEntity<HttpStatus> deletarTodos(){
			try {
				pessoaRepository.deleteAll();
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
				
			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			
		}
		
		@DeleteMapping("/pessoa/{id}")
		public ResponseEntity<HttpStatus> deletarPorId(@PathVariable("id") Long id){
			try {
				
				pessoaRepository.findById(id);
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
				
			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
				
		}
		
		@GetMapping("/pessoa/cristao")
		public ResponseEntity<List<Pessoa>>  listaCristao(){
			try {
				
				List<Pessoa> pessoas = pessoaRepository.findByCristao(true);
				if (pessoas.isEmpty()) {
					return new ResponseEntity<>(HttpStatus.NO_CONTENT);
				}
				return new ResponseEntity<>(pessoas, HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}
		
		
		
			
		}
	
	

