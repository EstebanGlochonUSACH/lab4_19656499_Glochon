package lab4_19656499_Glochon;

/**
 *
 * @author EstebanGlochonUSACH [https://github.com/EstebanGlochonUSACH]
 */
public class Like extends Reaccion {

    /**
     * Constructor para la clase Like.
     * @param autor
     */
    public Like(Usuario autor) {
        super(autor);
        this.tipo = Reaccion.Tipo.LIKE;
    }
}
