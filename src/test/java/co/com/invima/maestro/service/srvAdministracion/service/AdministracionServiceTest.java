package co.com.invima.maestro.service.srvAdministracion.service;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import co.com.invima.maestro.service.srvAdministracion.commons.ConfiguradorSpring;
import co.com.invima.maestro.service.srvAdministracion.commons.converter.AdministracionConverter;
import co.com.invima.maestro.service.srvAdministracion.web.api.rest.v1.AdministracionController;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ConfiguradorSpring.class})
@Slf4j
@AutoConfigureMockMvc
public class AdministracionServiceTest {

    @Autowired
    AdministracionController AdministracionController;

    @Autowired
    AdministracionConverter administracionConverter;

    @Before
    public void setUp(){
        try {
			MockitoAnnotations.openMocks(this).close();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    @Test
    public void testDefault() {
    	 assertEquals(Boolean.TRUE, Boolean.TRUE);
    }


}
