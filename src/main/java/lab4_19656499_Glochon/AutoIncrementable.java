package lab4_19656499_Glochon;

/**
 *
 * @author esteban
 */
public abstract class AutoIncrementable {

    /**
     * Variable global que mantiene el contador unico para las IDs.
     */
    public static int CURRENT_ID = 1;

    /**
     * Id representativa de la instancia (en el contxto de las subclases).
     */
    protected int id;

    /**
     * Costructor para los aitoincrementables, asigna automaticamente una ID
     * unica.
     */
    public AutoIncrementable(){
        this.id = CURRENT_ID++;
    }

    /**
     * Metodo selector para acceder a la ID de la instancia.
     * @return
     */
    public int getId(){
        return id;
    }
}