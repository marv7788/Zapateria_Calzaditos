/**aqui se establecen los get y set de los datos de la clase RolUsuario
 * 
 */
package datos;

/**
 *
 * @author REBOOTSYSTEM
 */
public class RolUsuario {
    
    private Rol RolId;
    private Usuario UsuarioId;

    public RolUsuario() {
        this.RolId = null;
        this.UsuarioId = null;
    }

    public RolUsuario(Rol RolId, Usuario UsuarioId) {
        this.RolId = RolId;
        this.UsuarioId = UsuarioId;
    }

    public Rol getRolId() {
        return RolId;
    }

    public void setRolId(Rol RolId) {
        this.RolId = RolId;
    }

    public Usuario getUsuarioId() {
        return UsuarioId;
    }

    public void setUsuarioId(Usuario UsuarioId) {
        this.UsuarioId = UsuarioId;
    }
    
    
    
}
