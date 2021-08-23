package lab4_19656499_Glochon.gui;

/**
 * @author EstebanGlochonUSACH [https://github.com/EstebanGlochonUSACH]
 * @param <GenericListener>
 * @param <GenericEvent>
 */
public interface EventTarget<GenericListener, GenericEvent> {
    public void addListener(GenericListener listener);
    public void removeListener(GenericListener listener);
    public void emitEvent(GenericEvent evt);
}
