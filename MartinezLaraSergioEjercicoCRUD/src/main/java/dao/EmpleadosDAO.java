/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import entidades.Empleado;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author smlar
 */
public class EmpleadosDAO {

    Connection conexion = null;

    public EmpleadosDAO() {
        try {
            conexion = DriverManager.getConnection("jdbc:mysql://localhost/jardineria", "root", "");
        } catch (SQLException ex) {
            System.err.println("Error en la Conexión: " + ex.getMessage());
        }
    }

    public Empleado read(Integer idEmpleado) {

        Empleado emp = null;
        PreparedStatement stm = null;
        String sql = "SELECT * FROM Empleados where CodigoEmpleado = ?";

        if (conexion == null || idEmpleado == null) {
            return emp;
        }

        try {
            stm = conexion.prepareStatement(sql);
            stm.setInt(1, idEmpleado);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                emp = new Empleado(
                        rs.getInt("CodigoEmpleado"),
                        rs.getString("Nombre"),
                        rs.getString("Apellido1"),
                        rs.getString("Apellido2"),
                        rs.getString("Extension"),
                        rs.getString("email"),
                        rs.getString("codigoOficina"),
                        rs.getInt("CodigoJefe"),
                        rs.getString("Puesto")
                );
            }

        } catch (SQLException ex) {
            System.err.println("Error en la lectura de empleado: " + ex.getMessage() + " " + stm.toString());
        }

        return emp;
    }

    public Integer update(Empleado empleado) {
        Integer resultado = 0;
        PreparedStatement stm = null;
        String sql = "UPDATE EMPLEADOS SET nombre = ?, apellido1 = ?, apellido2= ?  where codigoempleado = ?";
        if (conexion == null || empleado == null) {
            System.err.println("Error o la conexio ens nula o empleado es nulo");
            return 0;
        }
        try {
            stm = conexion.prepareStatement(sql);
            stm.setString(1, empleado.getNombre());
            stm.setString(2, empleado.getApellido1());
            stm.setString(3, empleado.getApellido2());
            stm.setInt(4, empleado.getCodigoEmpleado());
            resultado = stm.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Error en la actualización de empleado: " + ex.getMessage() + " " + stm.toString());

        }

        return resultado;
    }

    public Integer delete(Integer idEmpleado) {
        Integer resultado = 0;
        PreparedStatement stm = null;
        String sql = "DELETE FROM EMPLEADOS where codigoempleado = ?";
        if (conexion == null || idEmpleado == null) {
            System.err.println("Error o la conexio ens nula o empleado es nulo");
            return 0;
        }
        try {
            stm = conexion.prepareStatement(sql);
            stm.setInt(1, idEmpleado);
            resultado = stm.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Error en el borrado de empleado: " + ex.getMessage() + " " + stm.toString());

        }

        return resultado;
    }

    public Integer create(Empleado empleado) {
        Integer resultado = 0;
        PreparedStatement stm = null;
        String sql = "INSERT INTO EMPLEADOS "
                + "(CodigoEmpleado, Nombre, Apellido1, Apellido2, Extension, email, codigoOficina, CodigoJefe, Puesto) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        if (conexion == null || empleado == null) {
            System.err.println("Error o la conexio ens nula o empleado es nulo");
            return 0;
        }
        try {
            stm = conexion.prepareStatement(sql);
            stm.setInt(1, empleado.getCodigoEmpleado());
            stm.setString(2, empleado.getNombre());
            stm.setString(3, empleado.getApellido1());
            stm.setString(4, empleado.getApellido2());
            stm.setString(5, empleado.getExtension());
            stm.setString(6, empleado.getEmail());
            stm.setString(7, empleado.getCodigoOficina());
            stm.setInt(8, empleado.getCodigoJefe());
            stm.setString(9, empleado.getPuesto());
            resultado = stm.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Error en la actualización de empleado: " + ex.getMessage() + " " + stm.toString());

        }
        return resultado;
    }

    public ArrayList<Empleado> listAll() {

        ArrayList<Empleado> empList = null;
        PreparedStatement stm = null;
        String sql = "SELECT * FROM Empleados";

        if (conexion == null) {
            return empList;
        }

        try {
            stm = conexion.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            empList = new ArrayList<>();
            while (rs.next()) {
                empList.add(new Empleado(
                        rs.getInt("CodigoEmpleado"),
                        rs.getString("Nombre"),
                        rs.getString("Apellido1"),
                        rs.getString("Apellido2"),
                        rs.getString("Extension"),
                        rs.getString("email"),
                        rs.getString("codigoOficina"),
                        rs.getInt("CodigoJefe"),
                        rs.getString("Puesto")
                ));
            }

        } catch (SQLException ex) {
            System.err.println("Error en la lectura de empleado: " + ex.getMessage() + " " + stm.toString());
        }

        return empList;
    }

    public ArrayList<Empleado> listFiltered(Integer offset, Integer limit, String order, String filter) {
        ArrayList<Empleado> empList = null;
        PreparedStatement stm = null;

        // TODO hay que cambiar la select 

        String sql = "SELECT * FROM Empleados";

        if (conexion == null) {
            return empList;
        }

        try {
            stm = conexion.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            empList = new ArrayList<>();
            while (rs.next()) {
                empList.add(new Empleado(
                        rs.getInt("CodigoEmpleado"),
                        rs.getString("Nombre"),
                        rs.getString("Apellido1"),
                        rs.getString("Apellido2"),
                        rs.getString("Extension"),
                        rs.getString("email"),
                        rs.getString("codigoOficina"),
                        rs.getInt("CodigoJefe"),
                        rs.getString("Puesto")
                ));
            }

        } catch (SQLException ex) {
            System.err.println("Error en la lectura de empleado: " + ex.getMessage() + " " + stm.toString());
        }

        return empList;
    }

    
}
