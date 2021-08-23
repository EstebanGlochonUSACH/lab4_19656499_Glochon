package lab4_19656499_Glochon;

/**
 *
 * @author EstebanGlochonUSACH [https://github.com/EstebanGlochonUSACH]
 */
public class PublicacionShared extends Publicacion {
    
    private final Publicacion targetPublicacion;
    
    /**
     * Constructor de la clase PublicacionShared.Ademas ejecuta el metodo
     * "doShare" para incrementar el contador de veces compartidas de la 
     * publicaciones original.
     * @param autor
     * @param target
     */
    public PublicacionShared(Usuario autor, Publicacion target){
        super(autor, "El usuario <" + autor.getUsername() + "> compartio una publicacion de <"+ target.getAutor().getUsername() +">.", Publicacion.Tipo.SHARED);
        this.targetPublicacion = target;
        target.doShare();
    }

    /**
     * Retorna el contenido compuesto de la publicacion compartida.
     * @return String
     */
    @Override
    public String getContenido() {
        return super.getContenido() + "\n============\n\n" + targetPublicacion.getContenido();
    }

    /**
     * Devuelve la publicacion original que se esta compartiendo.
     * @return Publicacion
     */
    public Publicacion getTargetPublicacion() {
        return targetPublicacion;
    }
}
