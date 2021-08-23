package lab4_19656499_Glochon.gui;

import java.util.EventListener;
import java.util.EventObject;
import java.util.HashMap;

class SubmitEvent extends EventObject {
    public final HashMap<String, Object> fields;
    
    public SubmitEvent(Object source) {
        super(source);
        fields = new HashMap<>();
    }
}

interface SubmitEventListener extends EventListener {
    public void onSubmit(SubmitEvent evt);
}

/**
 *
 * @author EstebanGlochonUSACH [https://github.com/EstebanGlochonUSACH]
 */
public interface Submitable extends EventTarget<SubmitEventListener, SubmitEvent> {}
