/**aqui se establecen los get y set de los datos de la clase Rol
 * 
 */
package datos;

public class Rol {

    private Integer Id;
    private String Rol;
//se establece que el dato precardo de los item de la clase rol inicialmete son null.
    public Rol() {
        this.Id = null;
        this.Rol = null;
    }
    
    public Rol(String Rol) {
        this.Rol = Rol;
    }
    public Integer getId() {
        return Id;
    }

    public String getRol() {
        return Rol;
    }

    public void setRol(String Rol) {
        this.Rol = Rol;
    }



}
