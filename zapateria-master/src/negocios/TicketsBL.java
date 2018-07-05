/*
 Gestion de la tabla Tickets en la base de datos
 */
package negocios;
/*
 Importacion de Librerias necesarias
 */
import conexion.ConexionDB;
import datos.Ticket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author REBOOTSYSTEM
 */
public class TicketsBL extends BaseBL {

    private static final Logger LOG = Logger.getLogger(ClientesBL.class.getName());
    private Integer id = null;
    private static Connection con = null;
    private static ConexionDB cxn = null;
    private static PreparedStatement stmt = null;
    private static ResultSet rs = null;
    DefaultTableModel model = new DefaultTableModel();
    private static final String tableName = "Reparaciones";

    public TicketsBL() {
// conexion a la base de datos 
        cxn = new ConexionDB();

        // Si no existe la tabla ClientesBL Crearla Automaticamente
        if (!cxn.tableExist(tableName)) {
            Statement stmt = null;

            try {
                Class.forName("com.mysql.jdbc.Driver");

                con = cxn.openDB();
                stmt = con.createStatement();

                String sql = "CREATE TABLE IF NOT EXISTS `zapateria`.`tickets` (\n"
                        + "  `id` INT NOT NULL AUTO_INCREMENT,\n"
                        + "  `fecha` DATETIME NOT NULL,\n"
                        + "  `valor_total` DECIMAL(15,0) NULL,\n"
                        + "  `usuarios_id` INT NOT NULL,\n"
                        + "  `clientes_id` INT NOT NULL,\n"
                        + "  PRIMARY KEY (`id`),\n"
                        + "  UNIQUE INDEX `id_UNIQUE` (`id` ASC),\n"
                        + "  INDEX `fk_tickets_usuarios1_idx` (`usuarios_id` ASC),\n"
                        + "  INDEX `fk_tickets_clientes1_idx` (`clientes_id` ASC),\n"
                        + "  CONSTRAINT `fk_tickets_usuarios1`\n"
                        + "    FOREIGN KEY (`usuarios_id`)\n"
                        + "    REFERENCES `zapateria`.`usuarios` (`id`)\n"
                        + "    ON DELETE NO ACTION\n"
                        + "    ON UPDATE NO ACTION,\n"
                        + "  CONSTRAINT `fk_tickets_clientes1`\n"
                        + "    FOREIGN KEY (`clientes_id`)\n"
                        + "    REFERENCES `zapateria`.`clientes` (`id`)\n"
                        + "    ON DELETE NO ACTION\n"
                        + "    ON UPDATE NO ACTION)\n"
                        + "ENGINE = InnoDB;";

                stmt.executeUpdate(sql);

            } catch (ClassNotFoundException | SQLException e) {
                JOptionPane.showMessageDialog(null, e.getClass().getName() + ": " + e.getMessage());
                LOG.log(Level.SEVERE, null, e);
                System.exit(0);
            }
            System.out.println("Se ha creado la tabla");
        }
    }
 /*
 Crear registro en la tabla TicketsBL de la base de datos
 */
    public static void create(String fecha, Double valor_total, Integer usuarios_id, Integer clientes_id) {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = cxn.openDB();
            String sqlQuery = "INSERT INTO tickets(fecha, valor_total, usuarios_id, clientes_id) VALUES(?,?,?,?);";
            java.sql.PreparedStatement stmt = con.prepareStatement(sqlQuery);

            stmt.setString(1, fecha);
            stmt.setDouble(2, valor_total);
            stmt.setInt(3, usuarios_id);
            stmt.setInt(4, clientes_id);

            stmt.executeUpdate();

        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e.getClass().getName() + ": " + e.getMessage());
            LOG.log(Level.SEVERE, null, e);
        }

    }
    
        public static void create(Ticket ticket) {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = cxn.openDB();
            String sqlQuery = "INSERT INTO tickets(fecha, valor_total, usuarios_id, clientes_id) VALUES(?,?,?,?);";
            java.sql.PreparedStatement stmt = con.prepareStatement(sqlQuery);

            stmt.setString(1, ticket.getFecha());
            stmt.setDouble(2, ticket.getValorTotal());
            stmt.setInt(3, ticket.getUsuarioID());
            stmt.setInt(4, ticket.getClienteID());

            stmt.executeUpdate();

        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e.getClass().getName() + ": " + e.getMessage());
            LOG.log(Level.SEVERE, null, e);
        }

    }


    /**
     * Busca un registro por el id
     *
     * @param Id
     */
    public static void findById(Integer Id) {

    }

    /**
     * Busca un registro por un campo diferente al id
     */
    public static void findByField() {

    }
 /*
 actualizar registro en la tabla TicketsBL de la base de datos
 */
    public void update(String fecha, Double valor_total, Integer usuarios_id, Integer clientes_id, Integer id) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = cxn.openDB();
            String sqlQuery = "UPDATE usuarios SET fecha=?, valor_total=?, usuarios_id=?, clientes_id=?  WHERE id=?";
            stmt = con.prepareStatement(sqlQuery);

            stmt.setString(1, fecha);
            stmt.setDouble(2, valor_total);
            stmt.setInt(3, usuarios_id);
            stmt.setInt(4, clientes_id);
            stmt.setInt(6, id);

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Se ha modificado un registro en " + tableName);

        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error al modificar " + tableName);
            LOG.log(Level.SEVERE, null, e);
        }
    }
 /*
 Eliminar registro en la tabla TicketsBL de la base de datos
 */
    public static void delete(Integer Id) {
        if (Id != null) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                con = cxn.openDB();
                String sqlQuery = "DELETE FROM ? WHERE id=?";
                stmt = con.prepareStatement(sqlQuery);

                stmt.setString(1, tableName);
                stmt.setInt(2, Id);

                stmt.executeUpdate();

                JOptionPane.showMessageDialog(null, "Se ha eliminado el registro con el indice #" + Id);

            } catch (ClassNotFoundException | SQLException e) {
                JOptionPane.showMessageDialog(null, "Ocurrio un error al eliminar " + tableName + " " + e.getClass().getName() + ": " + e.getMessage() + "]");
                LOG.log(Level.SEVERE, null, e);
            }

        }
    }
 /*
 listar registro en la tabla TicketsBL de la base de datos
 */
    public static DefaultTableModel listar() {
        String[] columns = {"Id", "Fecha", "Cliente", "Valor"};
        DefaultTableModel model = new DefaultTableModel(null, columns);

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = cxn.openDB();

            Statement st = con.createStatement();
            String query = "SELECT tickets.id, tickets.fecha, clientes.nombre_completo, tickets.valor_total\n"
                    + "FROM tickets INNER JOIN clientes ON tickets.clientes_id = clientes.id;";

            rs = st.executeQuery(query);
            ResultSetMetaData rsMd = rs.getMetaData();

            int countColumns = rsMd.getColumnCount();

            while (rs.next()) {
                Object[] fila = new Object[countColumns];
                for (int i = 0; i < countColumns; i++) {
                    fila[i] = rs.getObject(i + 1);
                }
                model.addRow(fila);
            }

        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e.getClass().getName() + ": " + e.getMessage());
            e.printStackTrace();
            return model;
        }

        return model;
    }

}