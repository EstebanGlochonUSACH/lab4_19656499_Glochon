package lab4_19656499_Glochon.gui;

import java.util.EventListener;
import java.util.EventObject;

class DisplayEvent extends EventObject {
    private String context = null;
    public final Object item;

    public DisplayEvent(Object source, Object item) {
        super(source);
        this.item = item;
    }

    public DisplayEvent(Object source, Object item, String context) {
        super(source);
        this.item = item;
        this.context = context;
    }

    public boolean isContext(String value) {
        return (context == null ? value == null : context.equals(value));
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
