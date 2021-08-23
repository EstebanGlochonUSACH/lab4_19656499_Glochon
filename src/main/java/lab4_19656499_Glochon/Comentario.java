package lab4_19656499_Glochon;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Iterator;

/**
 *
 * @author EstebanGlochonUSACH [https://github.com/EstebanGlochonUSACH]
 */
public class Comentario extends AutoIncrementable implements Likeable {

    private final Usuario autor;
    private final Publicacion publicacion;
    private final Comentario parent;
    private final String contenido;
    private final LocalDate fechaCreacion;
    private final LinkedList<Like> likes;

    /**
     * Construcor de la clase Comentario.
     * @param autor
     * @param publicacion
     * @param contenido
     */
    public Comentario(Usuario autor, Publicacion publicacion, String contenido){
        super();
        this.autor = autor;
        this.publicacion = publicacion;
        this.parent = null;
        this.contenido = contenido;
        this.fechaCreacion = LocalDate.now();
        this.likes = new LinkedList<>();
    }

    /**
     * Construcor de la clase Comentario.
     * @param autor
     * @param parent
     * @param contenido
     */
    public Comentario(Usuario autor, Comentario parent, String contenido){
        super();
        this.autor = autor;
        this.publicacion = parent.getPublicacion();
        this.parent = parent;
        this.contenido = contenido;
        this.fechaCreacion = LocalDate.now();
        this.likes = new LinkedList<>();
    }

    /**
     * Devuelve la instancia del usuario autor del comentario.
     * @return Usuario
     */
    public Usuario getAutor() {
        return autor;
    }

    /**
     * Devuelve la instancia de la publicacion a la que esta asociado el 
     * comentario.
     * @return Publicacion
     */
    public Publicacion getPublicacion() {
        return publicacion;
    }

    /**
     * Devuelve el comentario parte en caso de que el comentario sea una
     * respuesta a otro comentario.
     * @return Comentario
     */
    public Comentario getParentComment() {
        return parent;
    }

    /**
     * Devuelve el contenido del comentario.
     * @return String
     */
    public String getContenido() {
        return contenido;
    }

    /**
     * Devuelve la fecha en la que se creo el comentario.
     * @return LocalDate
     */
    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    /**
     * Genera una reaccion "like" asociada al comentario y devuelve la
     * instacia del Like generado.
     * @param user
     * @return Like
     */
    public Like doLike(Usuario user){
        return Likeable.doLike(likes, user);
    }
    
    /**
     * Obtiene el numero total de likes del comentario.
     * @return int
     */
    public int totalLikes(){
        return this.likes.size();
    }

    /**
     * Es true si el comentario tiene un comentario padre (i.e. es subcomentario
     * de otro comentario).
     * @return boolean
     */
    public boolean isSubcomentario() {
        return(parent != null);
    }
}
