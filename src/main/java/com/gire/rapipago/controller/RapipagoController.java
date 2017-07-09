package com.gire.rapipago.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gire.rapipago.model.AgenteDTO;
import com.gire.rapipago.model.ResponseCode;
import com.gire.rapipago.model.ResponseDTO;
import com.gire.rapipago.service.AgenteService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/rapipago")
@Log
public class RapipagoController {

    ObjectMapper oMapper = new ObjectMapper();

    private AgenteService agenteService;

    @Autowired
    public RapipagoController(AgenteService agenteService) {
        this.agenteService = agenteService;
    }

    @RequestMapping(value = "/agentes", method = RequestMethod.GET)
    public ResponseDTO getAllAgentes() {
        log.info("Request para getAllAgentes");
        try {
            return new ResponseDTO(
                    ResponseCode.OK,
                    oMapper.writeValueAsString(agenteService.getAllAgentes()),
                    null
            );
        } catch (JsonProcessingException e) {
            log.severe("Error parseando respuesta");
            return new ResponseDTO(
                    ResponseCode.ERROR_GENERICO,
                    "",
                    "Error remoto"
            );
        }
    }

    @RequestMapping(value = "/agente/{id}", method = RequestMethod.GET)
    public ResponseDTO getAgente(@PathVariable String id) {
        log.info("Request para getAgente con id:" + id);
        AgenteDTO agente = agenteService.getAgente(Long.valueOf(id));
        if (agente != null) {
            try {
                return new ResponseDTO(ResponseCode.OK, oMapper.writeValueAsString(agente), "");
            } catch (JsonProcessingException e) {
                return new ResponseDTO(ResponseCode.ERROR_GENERICO, "", "Error remoto");
            }
        } else {
            return new ResponseDTO(ResponseCode.OK, "", "");
        }
    }

    @RequestMapping(value = "/agente/", method = RequestMethod.POST, consumes = "application/json")
    public AgenteDTO saveAgente(@RequestBody AgenteDTO agenteDTO) {
        return agenteService.saveAgente(agenteDTO);
    }

    @RequestMapping(value = "/agente/{id}", method = RequestMethod.DELETE)
    public boolean deleteAgente(@PathVariable Long id) {
        return agenteService.deleteAgente(id);
    }


}
