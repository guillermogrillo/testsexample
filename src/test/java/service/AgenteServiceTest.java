package service;

import com.gire.rapipago.entity.Agente;
import com.gire.rapipago.model.AgenteDTO;
import com.gire.rapipago.repository.AgenteRepository;
import com.gire.rapipago.service.AgenteService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;


public class AgenteServiceTest {


    AgenteRepository agenteRepo = Mockito.mock(AgenteRepository.class);

    AgenteService agenteService = new AgenteService(agenteRepo);

    List<Agente> agentes = null;

    @Before
    public void beforeTests() {
        agentes = new ArrayList<>();
        agentes.add(new Agente(1L, "agente1", 1));
        agentes.add(new Agente(2L, "agente2", 2));
        agentes.add(new Agente(3L, "agente3", 3));
        agentes.add(new Agente(4L, "agente4", 4));
    }


    @Test
    public void testGetAllAgentesOK() {
        Mockito.when(agenteRepo.findAll()).thenReturn(agentes);
        List<AgenteDTO> allAgentes = agenteService.getAllAgentes();
        Assert.assertNotNull(allAgentes);
        Assert.assertTrue(allAgentes.size() == 4);
    }

    @Test
    public void testGetAllAgentesSinDatos() {
        Mockito.when(agenteRepo.findAll()).thenReturn(null);
        List<AgenteDTO> allAgentes = agenteService.getAllAgentes();
        Assert.assertNull(allAgentes);
    }

    @Test
    public void testGetAgenteByIdOK() {
        Mockito.when(agenteRepo.findOne(1L)).thenReturn(new Agente(1L, "agente1", 1));
        AgenteDTO agenteEncontrado = agenteService.getAgente(1L);
        Assert.assertNotNull(agenteEncontrado);
        Assert.assertTrue(agenteEncontrado.getCodigo()==1);
    }

    @Test
    public void testGetAgenteByNombreOK() {
        Mockito.when(agenteRepo.findByNombre("agente1")).thenReturn(new Agente(1L, "agente1", 1));
        AgenteDTO agenteEncontrado = agenteService.getAgente("agente1");
        Assert.assertNotNull(agenteEncontrado);
        Assert.assertTrue(agenteEncontrado.getCodigo()==1);
    }

    @Test
    public void testGetAgenteByCodigoOK() {
        Mockito.when(agenteRepo.findByCodigo(1)).thenReturn(new Agente(1L, "agente1", 1));
        AgenteDTO agenteEncontrado = agenteService.getAgente(1);
        Assert.assertNotNull(agenteEncontrado);
        Assert.assertTrue(agenteEncontrado.getCodigo()==1);
    }


    @Test
    public void testGetAgenteByIdNoEncontrado() {
        AgenteDTO agenteEncontrado = agenteService.getAgente(1500L);
        Assert.assertNull(agenteEncontrado);
    }

    @Test
    public void testGetAgenteByNombreNoEncontrado() {
        AgenteDTO agenteEncontrado = agenteService.getAgente("inexistente");
        Assert.assertNull(agenteEncontrado);
    }

    @Test
    public void testGetAgenteByCodigoNoEncontrado() {
        AgenteDTO agenteEncontrado = agenteService.getAgente(1500);
        Assert.assertNull(agenteEncontrado);
    }


    @Test
    public void testSaveAgenteOK() {
        AgenteDTO agenteTest = new AgenteDTO();
        agenteTest.setNombre("Test");
        agenteTest.setCodigo(200);
        AgenteDTO agenteGuardado = agenteService.saveAgente(agenteTest);
        Assert.assertNotNull(agenteGuardado);
    }

    @Test
    public void testDeleteAgenteOK() {
        Assert.assertTrue(agenteService.deleteAgente(4L));
    }

    @Test
    public void testDeleteAgenteConError() {
        Assert.assertTrue(agenteService.deleteAgente(2000L));
    }

}
