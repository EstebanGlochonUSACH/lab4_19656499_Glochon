package lab4_19656499_Glochon.gui;

import javax.swing.event.EventListenerList;
import lab4_19656499_Glochon.Comentario;

/**
 *
 * @author EstebanGlochonUSACH [https://github.com/EstebanGlochonUSACH]
 */
public class MostrarComentarios extends Paginacion<Comentario> implements Displayable {

    public MostrarComentarios() {
        super("Comentarios");
    }

    @Override
    protected javax.swing.JPanel transformItem(Comentario comment) {
        ComentarioMinimo panel = new ComentarioMinimo(comment);
        
        MostrarComentarios main = this;
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
