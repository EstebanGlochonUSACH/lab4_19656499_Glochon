package lab4_19656499_Glochon.gui;

import javax.swing.event.EventListenerList;
import lab4_19656499_Glochon.Publicacion;

/**
 *
 * @author EstebanGlochonUSACH [https://github.com/EstebanGlochonUSACH]
 */
public class MostrarPublicaciones extends Paginacion<Publicacion> implements Displayable {

    public MostrarPublicaciones() {
        super("Publicaciones");
    }

    @Override
    protected javax.swing.JPanel transformItem(Publicacion pub) {
        PublicacionMinimo panel = new PublicacionMinimo(pub);
        
        MostrarPublicaciones main = this;
        panel.addListener(new DisplayEventListener(){
            @Override
            public void onDisplay(DisplayEvent evt) {
                main.emitEvent(evt);
            }
        });

        return panel;
    }

    protected EventListenerList listenerList = new EventListenerList();

    @Override
    public void addListener(DisplayEventListener listener) {
        listenerList.add(DisplayEventListener.class, listener);
    }

    @Override
    public void removeListener(DisplayEventListener listener) {
        listenerList.remove(DisplayEventListener.class, listener);
    }

    @Override
    public void emitEvent(DisplayEvent evt) {
        for(DisplayEventListener listener: listenerList.getListeners(DisplayEventListener.class)) {
            listener.onDisplay(evt);
        }
    }
}
