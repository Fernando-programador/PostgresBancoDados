package com.fsc.postegresql.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fsc.postegresql.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long>{

	
	List<Pessoa> findByCristao(Boolean crsitao);
	
	List<Pessoa> findByNomeContaining(String nome);
}
