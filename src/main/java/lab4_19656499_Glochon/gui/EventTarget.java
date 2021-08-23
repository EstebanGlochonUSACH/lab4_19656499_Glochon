package lab4_19656499_Glochon.gui;

/**
 * @author EstebanGlochonUSACH [https://github.com/EstebanGlochonUSACH]
 * @param <GenericListener>
 * @param <GenericEvent>
 */
public interface EventTarget<GenericListener, GenericEvent> {

    /**
     * Metodo generico para registrar un nuevo listener en la instancia en del
     * objeto en contexto.
     * @param listener
     */
    public void addListener(GenericListener listener);

    /**
     * Metodo generico para eliminar un listener antes registrado en la 
     * instancia del objeto en contexto.
     * @param listener
     */
    public void removeListener(GenericListener listener);

    /**
     * Metodo generico para emitir/iniciar un evento desde la instancia del
     * objeto en contexto.
     * @param evt
     */
    public void emitEvent(GenericEvent evt);
}
