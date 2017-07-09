package com.gire.rapipago.repository;

import com.gire.rapipago.entity.Agente;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AgenteRepository extends CrudRepository<Agente, Long> {

    List<Agente> findAll();

    Agente findByNombre(String nombre);

    Agente findByCodigo(Integer codigo);

}
