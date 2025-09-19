package com.agenda.senai.Spring.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.agenda.senai.Spring.Model.Contato;

@Repository
public interface ContatoRepository extends CrudRepository <Contato,Long> {

}
