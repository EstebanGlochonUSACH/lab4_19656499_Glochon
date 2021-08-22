package lab4_19656499_Glochon;

/**
 *
 * @author EstebanGlochonUSACH [https://github.com/EstebanGlochonUSACH]
 */
public class PublicacionShared extends Publicacion {
    
    private final Publicacion targetPublicacion;
    
    /**
     * Constructor de la clase PublicacionShared. Ademas ejecuta el metodo
     * "doShare" para incrementar el contador de veces compartidas de la 
     * publicaciones original.
     * @param pblcn
     * @param autor
     */
    public PublicacionShared(Usuario autor, Publicacion target){
        super(autor, "<" + autor.getUsername() + "> te compartio esta publicacion.", Publicacion.Tipo.SHARED);
        this.targetPublicacion = target;
        target.doShare();
    }

    /**
     * Devuelve la publicacion original que se esta compartiendo.
     * @return Publicacion
     */
    public Publicacion getTargetPublicacion() {
        return targetPublicacion;
    }
}
