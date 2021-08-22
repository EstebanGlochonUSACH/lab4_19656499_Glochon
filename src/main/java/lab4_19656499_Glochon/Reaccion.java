package lab4_19656499_Glochon;

import java.time.LocalDate;

/**
 *
 * @author EstebanGlochonUSACH [https://github.com/EstebanGlochonUSACH]
 */
public class Reaccion extends AutoIncrementable {
    
    /**
     * Definicion para los "tipos" de reaccion.
     */
    protected enum Tipo { LIKE };

    /**
     * Usuario que realizo la "reaccion".
     */
    public Usuario autor;

    /**
     * Fecha en la que se realizo la "reaccion".
     */
    public LocalDate fechaCreacion;

    /**
     * Define el tipo de reaccion que se esta registrando.
     */
    public Reaccion.Tipo tipo;

    /**
     * Constructor para la clase Reaccion.
     * @param autor
     * @param fechaCreacion
     */
    public Reaccion(Usuario autor, LocalDate fechaCreacion) {
        this.autor = autor;
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * Constructor para la clase Reaccion.
     * @param autor
     */
    public Reaccion(Usuario autor) {
        this.autor = autor;
        this.fechaCreacion = LocalDate.now();
    }

    /**
     * Metodo selector para obtener el autor de la reaccion.
     * @return Usuario
     */
    public Usuario getAutor() {
        return autor;
    }
    
}
