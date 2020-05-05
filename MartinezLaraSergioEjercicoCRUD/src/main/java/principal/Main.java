/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;

import dao.EmpleadosDAO;
import entidades.Empleado;

/**
 *
 * @author smlar
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EmpleadosDAO empleados = null;
        Empleado miEmpleado = null;

        empleados = new EmpleadosDAO();

        miEmpleado = empleados.read(1);
        if (miEmpleado != null) {
            System.out.println(miEmpleado.toString());
            
            miEmpleado.setApellido1("Jimenez");
            empleados.update(miEmpleado);
            System.out.println(miEmpleado);

        } else {
            System.exit(0);
        }

    }

}
