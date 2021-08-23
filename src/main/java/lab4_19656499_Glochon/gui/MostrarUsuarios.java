package lab4_19656499_Glochon.gui;

import javax.swing.event.EventListenerList;
import lab4_19656499_Glochon.Usuario;

/**
 * @author EstebanGlochonUSACH [https://github.com/EstebanGlochonUSACH]
 */
public class MostrarUsuarios extends Paginacion<Usuario> implements Displayable {

    /**
     * Contructor. Se define el titlo de la vista.
     */
    public MostrarUsuarios() {
        super("Usuarios");
    }

    /**
     * Transforma un modelo dado en una componente JPanel a mostrar en la 
     * ventana.
     * @param user
     * @return JPanel
     */
    @Override
    protected javax.swing.JPanel transformItem(Usuario user) {
        UsuarioMinimo panel = new UsuarioMinimo(user);
        
        MostrarUsuarios main = this;
        panel.addListener(new DisplayEventListener(){
            @Override
            public void onDisplay(DisplayEvent evt) {
                main.emitEvent(evt);
            }
        });

        return panel;
    }

    /**
     * Lista de registros de listener para eventos.
     */
    protected EventListenerList listenerList = new EventListenerList();

    /**
     * Registra un nuevo listener.
     * @param listener
     */
    @Override
    public void addListener(DisplayEventListener listener) {
        listenerList.add(DisplayEventListener.class, listener);
    }

    /**
     * Elimina un listener antes registrado.
     * @param listener
     */
    @Override
    public void removeListener(DisplayEventListener listener) {
        listenerList.remove(DisplayEventListener.class, listener);
    }

    /**
     * Emite un evento en el contexto de la instacia del objeto.
     * @param evt
     */
    @Override
    public void emitEvent(DisplayEvent evt) {
        for(DisplayEventListener listener: listenerList.getListeners(DisplayEventListener.class)) {
            listener.onDisplay(evt);
        }
    }
}
