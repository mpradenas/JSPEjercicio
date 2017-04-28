
package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import datos.ConexionJDBC;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Alumnos
 */
public class Controlador extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            ConexionJDBC coneccion= new ConexionJDBC();
            coneccion.conectar();
            String id=request.getParameter("id");
            String nombres= request.getParameter("nombres");
            String ap1=request.getParameter("ap1");
            String ap2=request.getParameter("ap2");
            String sql="Insert into cli_clientes(cli_idCliente,cli_nombres,cli_apellido1,cli_apellido2)values(?,?,?,?);";
            try(PreparedStatement pst=coneccion.getConexion().prepareStatement(sql))
            {
                  pst.setString(1, id);
                  pst.setString(2, nombres);
                  pst.setString(3, ap1);
                  pst.setString(4, ap2);
                  pst.executeUpdate();
                  request.getRequestDispatcher("salida.jsp").forward(request, response);
           
            }
            catch(SQLException ex)
            {
                   String error=" Ha ocurrido un error: "+ex.getMessage().toString();
                   request.setAttribute("Error!!!", error);
                   request.getRequestDispatcher("index.jsp").forward(request, response);
            }
            
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
