import com.dh.clinica.dao.impl.PacienteDaoH2;
import com.dh.clinica.model.Domicilio;
import com.dh.clinica.model.Paciente;
import com.dh.clinica.service.PacienteService;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.runners.MethodSorters;

import java.time.LocalDate;
import java.util.List;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(JUnit4.class)
public class PacienteServiceTest {

    private static PacienteService pacienteService = new PacienteService(new PacienteDaoH2());

    @BeforeClass
    public static void cargarDataSet() {
        Domicilio domicilio = new Domicilio("Av Santa fe", "444", "CABA", "Buenos Aires");
        Paciente p = pacienteService.guardar(new Paciente("Santiago", "Paz", "88888888", LocalDate.now(), domicilio));
        Domicilio domicilio1 = new Domicilio("Av Avellaneda", "333", "CABA", "Buenos Aires");
        Paciente p1 = pacienteService.guardar(new Paciente("Micaela", "Perez", "99999999", LocalDate.now(), domicilio1));

    }

    @Test
    public void agregarYBuscarPacienteTest() {
        Domicilio domicilio = new Domicilio("Calle", "123", "Temperley", "Buenos Aires");
        Paciente p = pacienteService.guardar(new Paciente("Tomas", "Pereyra", "12345678", LocalDate.now(), domicilio));

        Assert.assertNotNull(pacienteService.buscar(p.getId()));
    }

    @Test
    public void eliminarPacienteTest() {
        pacienteService.eliminar(3);
        Assert.assertTrue(pacienteService.buscar(3) == null);

    }

    @Test
    public void traerTodos() {
        List<Paciente> pacientes = pacienteService.buscarTodos();
        Assert.assertTrue(!pacientes.isEmpty());
        Assert.assertTrue(pacientes.size() > 0);
        System.out.println(pacientes);
    }
    
    
    @Test
    public void actualizarPacienteTest() {
        Paciente p = pacienteService.buscar(1);
        p.setNombre("SantiagoActualizado");
        p.setApellido("PazActualizado");
        p.setDni("111Actualizado");
        p.setFechaIngreso(LocalDate.now());
        p.getDomicilio().setCalle("CalleActualizada");
        p.getDomicilio().setNumero("123Actualizado");
        p.getDomicilio().setLocalidad("TemperleyActualizado");
        p.getDomicilio().setProvincia("Buenos AiresActualizado");
        pacienteService.actualizar(p);
        Paciente p1 = pacienteService.buscar(1);
        Assert.assertEquals(p.getNombre(), p1.getNombre());
        Assert.assertEquals(p.getApellido(), p1.getApellido());
        Assert.assertEquals(p.getDni(), p1.getDni());
        Assert.assertEquals(p.getFechaIngreso(), p1.getFechaIngreso());
        Assert.assertEquals(p.getDomicilio().getCalle(), p1.getDomicilio().getCalle());
        Assert.assertEquals(p.getDomicilio().getNumero(), p1.getDomicilio().getNumero());
        Assert.assertEquals(p.getDomicilio().getLocalidad(), p1.getDomicilio().getLocalidad());
        Assert.assertEquals(p.getDomicilio().getProvincia(), p1.getDomicilio().getProvincia());
    }
}
