package lab4_19656499_Glochon.gui;

import java.util.EventListener;
import java.util.EventObject;

class DisplayEvent extends EventObject {
    public final Object item;

    public DisplayEvent(Object source, Object item) {
        super(source);
        this.item = item;
    }
}

interface DisplayEventListener extends EventListener {
    public void onDisplay(DisplayEvent evt);
}

/**
 *
 * @author EstebanGlochonUSACH [https://github.com/EstebanGlochonUSACH]
 */
public interface Displayable extends EventTarget<DisplayEventListener, DisplayEvent> {}
