package com.gire.rapipago.service;

import com.gire.rapipago.entity.Agente;
import com.gire.rapipago.model.AgenteDTO;
import com.gire.rapipago.repository.AgenteRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Log
public class AgenteService {

    private AgenteRepository agenteRepo;

    @Autowired
    public AgenteService(AgenteRepository agenteRepo) {
        this.agenteRepo = agenteRepo;
    }

    public List<AgenteDTO> getAllAgentes() {
        log.info("Llamado a getAllAgentes");
        List<Agente> agentes = agenteRepo.findAll();
        List<AgenteDTO> agenteDTOs = null;
        if (agentes != null && !agentes.isEmpty()) {
            agenteDTOs = new ArrayList<>();
            AgenteDTO agenteDTO = null;
            for (Agente agente : agentes) {
                agenteDTO = new AgenteDTO();
                agenteDTO.setId(agente.getId());
                agenteDTO.setCodigo(agente.getCodigo());
                agenteDTO.setNombre(agente.getNombre());
                agenteDTOs.add(agenteDTO);
            }
        }
        return agenteDTOs;
    }

    public AgenteDTO getAgente(Long id) {
        Agente agente = agenteRepo.findOne(id);
        if (agente == null) {
            return null;
        }
        AgenteDTO agenteDTO = null;
        agenteDTO = new AgenteDTO();
        agenteDTO.setId(agente.getId());
        agenteDTO.setCodigo(agente.getCodigo());
        agenteDTO.setNombre(agente.getNombre());
        return agenteDTO;
    }

    public AgenteDTO getAgente(String nombre) {
        Agente agente = agenteRepo.findByNombre(nombre);
        if (agente == null) {
            return null;
        }
        AgenteDTO agenteDTO = null;
        agenteDTO = new AgenteDTO();
        agenteDTO.setId(agente.getId());
        agenteDTO.setCodigo(agente.getCodigo());
        agenteDTO.setNombre(agente.getNombre());
        return agenteDTO;
    }

    public AgenteDTO getAgente(Integer codigo) {
        Agente agente = agenteRepo.findByCodigo(codigo);
        if (agente == null) {
            return null;
        }
        AgenteDTO agenteDTO = null;
        agenteDTO = new AgenteDTO();
        agenteDTO.setId(agente.getId());
        agenteDTO.setCodigo(agente.getCodigo());
        agenteDTO.setNombre(agente.getNombre());
        return agenteDTO;
    }


    public AgenteDTO saveAgente(AgenteDTO agenteDTO) {
        Agente agente = new Agente();
        agente.setNombre(agenteDTO.getNombre());
        agente.setCodigo(agenteDTO.getCodigo());
        Agente savedAgente = agenteRepo.save(agente);
        agenteDTO.setId(agente.getId());
        return agenteDTO;
    }

    public boolean deleteAgente(Long id) {
        agenteRepo.delete(id);
        Agente deletedAgente = agenteRepo.findOne(id);
        return deletedAgente == null;
    }

}
