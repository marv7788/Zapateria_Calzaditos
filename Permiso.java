/**aqui se establecen los get y set de los datos de la clase permiso de cada rol
 * 
 */
package datos;

public class Permiso {

    private Integer Id;
    private String Nombre;
    private String Descripcion;

    public Permiso() {
    }

    public Permiso(String Nombre, String Descripcion) {
        this.Nombre = Nombre;
        this.Descripcion = Descripcion;
    }

    public Integer getId() {
        return Id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    
    
}
