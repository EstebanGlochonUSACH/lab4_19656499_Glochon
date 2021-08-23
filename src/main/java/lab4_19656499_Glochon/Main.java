package lab4_19656499_Glochon;

import lab4_19656499_Glochon.gui.MainWindow;

/**
 *
 * @author EstebanGlochonUSACH [https://github.com/EstebanGlochonUSACH]
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SocialNetwork social = new SocialNetwork("starbook");
        MainWindow win = new MainWindow(social);
        win.setVisible(true);
    }

}
