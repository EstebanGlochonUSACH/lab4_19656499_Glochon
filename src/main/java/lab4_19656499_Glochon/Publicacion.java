package lab4_19656499_Glochon;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Iterator;

/**
 *
 * @author EstebanGlochonUSACH [https://github.com/EstebanGlochonUSACH]
 */
public class Publicacion extends AutoIncrementable implements Likeable {
    
    /**
     * Clase anidad para definir los valores "Tipos" que pueden tomar las
     * publicaciones.
     */
    public static enum Tipo { PHOTO, VIDEO, URL, TEXT, AUDIO, SHARED };

    private Publicacion.Tipo tipo = Publicacion.Tipo.TEXT;
    private final Usuario autor;
    private final LocalDate fechaCreacion;
    private final String contenido;
    private final LinkedList<Like> likes;
    private final LinkedList<Comentario> comentarios;
    private int shares = 0;

    /**
     * Constructor para la calse Publicacion.
     * @param autor
     * @param contenido
     */
    public Publicacion(Usuario autor, String contenido){
        super();
        this.autor = autor;
        this.contenido = contenido;
        this.fechaCreacion = LocalDate.now();
        this.likes = new LinkedList<>();
        this.comentarios = new LinkedList<>();
    }

    /**
     * Constructor para la calse Publicacion.
     * @param autor
     * @param contenido
     * @param tipo
     */
    public Publicacion(Usuario autor, String contenido, Publicacion.Tipo tipo){
        super();
        this.tipo = tipo;
        this.autor = autor;
        this.contenido = contenido;
        this.fechaCreacion = LocalDate.now();
        this.likes = new LinkedList<>();
        this.comentarios = new LinkedList<>();
    }

    /**
     * Obtener el contenido de la publicacion.
     * @return String
     */
    public String getContenido() {
        return contenido;
    }

    /**
     * Obtener el tipo de la publicacion.
     * @return String
     */
    public Publicacion.Tipo getTipo() {
        return tipo;
    }

    /**
     * Obtener la fecha de creacion de la publicacion.
     * @return LocalDate
     */
    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    /**
     * Obtener la instancia del usuario autor de la publicacion.
     * @return Usuario
     */
    public Usuario getAutor() {
        return autor;
    }

    /**
     * Obtener la lista de comentarios asociados a la publicacion.
     * @return LinkedList(Comentario)
     */
    public LinkedList<Comentario> getComentarios() {
        return comentarios;
    }

    /**
     * Obtener el numero de veces que se compartio la publicacion.
     * @return int
     */
    public int getShares(){
        return shares;
    }

    /**
     * Obtener el numero total de comentarios que hay en la publicacion.
     * @return int
     */
    public int totalComentarios() {
        return comentarios.size();
    }

    /**
     * Incrementar el contador de numero de veces que se compartio la
     * publicacion.
     */
    public void doShare(){
        ++shares;
    }

    /**
     * Asocia un nuevo comentario con la publicacion.
     * @param comment
     */
    public void addComentario(Comentario comment) {
        this.comentarios.add(comment);
    }

    /**
     * Genera una reaccion "like" asociada a la publicacion y devuelve la
     * instacia del Like generado.
     * @param user
     * @return Like
     */
    @Override
    public Like doLike(Usuario user){
        return Likeable.doLike(likes, user);
    }
    
    /**
     * Obtiene el numero total de likes de la publicacion.
     * @return int
     */
    @Override
    public int totalLikes(){
        return this.likes.size();
    }
}