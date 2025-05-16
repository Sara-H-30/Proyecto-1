package atraccion.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import atracciones.AtraccionCultural;
import usuario.Empleado;

public class AtraccionCulturalTest {
	
	private AtraccionCultural atraccion;
    private Empleado emp1, emp2;

    @BeforeEach
    public void setUp() {
        atraccion = new AtraccionCultural("Castillo del Terror", 30, 2, "Zona A", 2, "Tormenta", 12);
        emp1 = new Empleado("Pedro", "123","pedro@atraccion.com" ,"Operador");
        emp2 = new Empleado("Ana", "456","ana@atraccion.com", "Operador");
    }

    @Test
    public void testAsignarYDesasignarEmpleado() {
        atraccion.asignarEmpleado(emp1);
        assertEquals(1, atraccion.getEmpleadosAsignados().size());

        atraccion.desasignarEmpleado(emp1);
        assertEquals(0, atraccion.getEmpleadosAsignados().size());
    }

    @Test
    public void testTienePersonalSuficiente() {
        assertFalse(atraccion.tienePersonalSuficiente());
        atraccion.asignarEmpleado(emp1);
        assertFalse(atraccion.tienePersonalSuficiente());
        atraccion.asignarEmpleado(emp2);
        assertTrue(atraccion.tienePersonalSuficiente());
    }

    @Test
    public void testEstaOperativa_ClimaCorrectoYPersonal() {
        atraccion.asignarEmpleado(emp1);
        atraccion.asignarEmpleado(emp2);
        LocalDate fecha = LocalDate.of(2025, 6, 1);
        assertTrue(atraccion.estaOperativa(fecha, "Soleado"));
    }

    @Test
    public void testEstaOperativa_CondicionClimaticaIncorrecta() {
        atraccion.asignarEmpleado(emp1);
        atraccion.asignarEmpleado(emp2);
        LocalDate fecha = LocalDate.of(2025, 6, 1);
        assertFalse(atraccion.estaOperativa(fecha, "Tormenta"));
    }
}


}
