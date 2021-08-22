package lab4_19656499_Glochon;

import java.util.Iterator;
import java.util.LinkedList;


/**
 *
 * @author EstebanGlochonUSACH [https://github.com/EstebanGlochonUSACH]
 */
public interface Likeable {

    /**
     * Genera una reaccion "like" asociada al objeto en cuestio y devuelve la
     * instacia del Like generado.
     * @param user
     * @return Like
     */
    public Like doLike(Usuario user);

    /**
     * Obtiene el numero total de likes de la publicacion.
     * @return int
     */
    public int totalLikes();
    
    /**
     * Metodo utilitario para reusar en las clases que implementen la interfaz.
     * @param likes
     * @param user
     * @return
     */
    static public Like doLike(LinkedList<Like> likes, Usuario user){
        // Comprobar si el usuario ya dio like
        Iterator<Like> iter = likes.iterator();
        Like aux;
        while(iter.hasNext()){
            aux = iter.next();
            if(aux.getAutor() == user){
                return null;
            }
        }

        // Registrar like
        Like like = new Like(user);
        likes.add(like);
        return like;
    }
}
