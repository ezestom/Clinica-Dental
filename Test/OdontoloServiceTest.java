import com.dh.clinica.dao.impl.OdontologoDaoH2;
import com.dh.clinica.model.Odontologo;
import com.dh.clinica.service.OdontoloService;
import junit.framework.TestCase;
import org.junit.Assert;

import java.util.List;

public class OdontoloServiceTest extends TestCase {
  
  public void testGuardar() {
    Odontologo odontologo = new Odontologo("M-135", "Juan", "Perez");
    OdontoloService odontoloService = new OdontoloService(new OdontologoDaoH2());
    
    Odontologo odontologoGuardado = odontoloService.guardar(odontologo);
  
    Assert.assertNotNull(odontoloService.buscar(odontologoGuardado.getId()));
  }
  
  public void testBuscar() {
    OdontoloService odontoloService = new OdontoloService(new OdontologoDaoH2());
    Odontologo odontologo = odontoloService.buscar(2);
    Odontologo odo2 = odontoloService.buscar(33);
    // System.out.println(odontologo);
    
    assertEquals(2, odontologo.getId().intValue());
    assertNull(odo2);
  }
  
  public void testEliminar() {
    OdontoloService odontoloService = new OdontoloService(new OdontologoDaoH2());
    odontoloService.eliminar(1);
    
    Odontologo odontologo = odontoloService.buscar(1);
    
    assertNull(odontologo);
  }
  
  public void testBuscarTodos() {
    OdontoloService odontoloService = new OdontoloService(new OdontologoDaoH2());
    List<Odontologo> odontologos = odontoloService.buscarTodos();
  
    Assert.assertTrue(!odontologos.isEmpty());
    Assert.assertTrue(odontologos.size() > 0);
  }
  
  public void testActualizar() {
    OdontoloService odontoloService = new OdontoloService(new OdontologoDaoH2());
    Odontologo odontologo = odontoloService.buscar(2);
    odontologo.setNumeroMatricula("M-999");
    odontologo.setNombre("JuanActualizado");
    odontologo.setApellido("PerezActualizado");
    odontoloService.actualizar(odontologo);

    Odontologo odontologoActualizado = odontoloService.buscar(2);
  
    assertEquals(odontologo.getNumeroMatricula(), odontologoActualizado.getNumeroMatricula());
    assertEquals(odontologo.getNombre(), odontologoActualizado.getNombre());
    assertEquals(odontologo.getApellido(), odontologoActualizado.getApellido());
  }
}