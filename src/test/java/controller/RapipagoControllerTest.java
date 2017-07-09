package controller;

import com.gire.rapipago.RapipagoApp;
import com.gire.rapipago.controller.RapipagoController;
import com.gire.rapipago.model.ResponseDTO;
import com.gire.rapipago.service.AgenteService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = RapipagoApp.class)
@TestPropertySource(locations = "classpath:application.properties")
@RunWith(SpringJUnit4ClassRunner.class)
public class RapipagoControllerTest {

    @LocalServerPort
    private int port;

    @MockBean
    private AgenteService agenteService;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testGetAllAgentes() {
        String baseUrl = "http://localhost:"+port;
        ResponseEntity<ResponseDTO> response = this.restTemplate.getForEntity(
                baseUrl + "/agentes/", ResponseDTO.class);
        Assert.assertNotNull(response);
        Assert.assertEquals(response.getStatusCode().value(), 200);
    }


}
