package modelo;

public class Cliente {
    private int id;
    private String nombre;
    private String identificacion;
    private String correo;
    private String telefono;


    public Cliente(int id, String nombre, String identificacion, String correo, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.identificacion = identificacion;
        this.correo = correo;
        this.telefono = telefono;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public String getCorreo() {
        return correo;
    }

    public String getTelefono() {
        return telefono;
    }
    
    @Override
    public String toString() {
        return id + " - " + nombre + " - " + identificacion;
    }
        
    
}
