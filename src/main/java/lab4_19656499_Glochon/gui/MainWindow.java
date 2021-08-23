package lab4_19656499_Glochon.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import lab4_19656499_Glochon.Comentario;
import lab4_19656499_Glochon.Publicacion;
import lab4_19656499_Glochon.SocialNetwork;
import lab4_19656499_Glochon.Usuario;

/**
 *
 * @author EstebanGlochonUSACH [https://github.com/EstebanGlochonUSACH]
 */
public class MainWindow extends javax.swing.JFrame {
    
    private final SocialNetwork socialNetwork;
    private final GridBagLayout layout = new GridBagLayout();
    private final Login panel1 = new Login();
    private final Register panel2 = new Register();
    private final CrearPublicacion panel3 = new CrearPublicacion();
    private final MostrarPublicacion panel4;
    private final BuscarPublicaciones panel5 = new BuscarPublicaciones();
    private final BuscarUsuarios panel6 = new BuscarUsuarios();
    private final MostrarPublicaciones panel7 = new MostrarPublicaciones();
    private final MostrarUsuarios panel8 = new MostrarUsuarios();
    private final MostrarUsuario panel9;
    private final CrearComentario panel10 = new CrearComentario();
    private final MostrarComentarios panel11 = new MostrarComentarios();
    private final MostrarComentario panel12;
    private final BuscarVirales panel13 = new BuscarVirales();

    /**
     * Creates new form MainWindow
     */
    public MainWindow(SocialNetwork socialNetwork) {
        this.socialNetwork = socialNetwork;
        this.panel4 = new MostrarPublicacion(socialNetwork);
        this.panel9 = new MostrarUsuario(socialNetwork);
        this.panel12 = new MostrarComentario(socialNetwork);

        setTitle("SocialNetwork Client");
        initComponents();

        dynamicPanel.setLayout(layout);
        GridBagConstraints cons = new GridBagConstraints();
        cons.anchor = GridBagConstraints.NORTHWEST;
        cons.weightx = 1;
        cons.weighty = 1;
        dynamicPanel.add(panel1, cons);
        dynamicPanel.add(panel2, cons);
        dynamicPanel.add(panel3, cons);
        dynamicPanel.add(panel4, cons);
        dynamicPanel.add(panel5, cons);
        dynamicPanel.add(panel6, cons);
        dynamicPanel.add(panel7, cons);
        dynamicPanel.add(panel8, cons);
        dynamicPanel.add(panel9, cons);
        dynamicPanel.add(panel10, cons);
        dynamicPanel.add(panel11, cons);
        dynamicPanel.add(panel12, cons);
        dynamicPanel.add(panel13, cons);
        panel1.setVisible(true);
        panel2.setVisible(false);
        panel3.setVisible(false);
        panel4.setVisible(false);
        panel5.setVisible(false);
        panel6.setVisible(false);
        panel7.setVisible(false);
        panel8.setVisible(false);
        panel9.setVisible(false);
        panel10.setVisible(false);
        panel11.setVisible(false);
        panel12.setVisible(false);
        panel13.setVisible(false);
        
        java.awt.Frame main = this;
        
        panel1.addListener(new SubmitEventListener() {
            @Override
            public void onSubmit(SubmitEvent evt) {
                if(evt.fields.containsKey("username") && evt.fields.containsKey("password")){
                    InfoDialog dialog;
                    String username = (String)evt.fields.get("username");
                    String password = (String)evt.fields.get("password");
                    if(socialNetwork.login(username, password)){
                        labelSesion.setText(socialNetwork.getSessionUsername());
                        btnLogin.setText("Logout");
                        btnRegister.setEnabled(false);
                        dialog = new InfoDialog(main, "La sesion se inicio correctamente!");
                        dialog.setVisible(true);
                    }
                    else{
                        dialog = new InfoDialog(main, "No se pudo iniciar la sesion!");
                        dialog.setVisible(true);
                    }
                }
            }
        });
        
        panel2.addListener(new SubmitEventListener() {
            @Override
            public void onSubmit(SubmitEvent evt) {
                if(evt.fields.containsKey("username") && evt.fields.containsKey("password")){
                    InfoDialog dialog;
                    String username = (String)evt.fields.get("username");
                    String password = (String)evt.fields.get("password");
                    if(socialNetwork.register(username, password)){
                        showLogin();
                    }
                    else{
                        dialog = new InfoDialog(main, "El usuario se no se pudo registrar!");
                        dialog.setVisible(true);
                    }
                }
            }
        });
        
        panel3.addListener(new SubmitEventListener() {
            @Override
            public void onSubmit(SubmitEvent evt) {
                if(evt.fields.containsKey("contenido")){
                    InfoDialog dialog;
                    String contenido = (String) evt.fields.get("contenido");
                    Publicacion pub = socialNetwork.post(Publicacion.Tipo.TEXT, contenido);
                    if(pub == null){
                        dialog = new InfoDialog(main, "No se pudo crear la publicacion!");
                        dialog.setVisible(true);
                    }
                    else{
                        showPublicacion(pub);
                    }
                }
            }
        });
        
        panel4.addListener(new DisplayEventListener() {
            @Override
            public void onDisplay(DisplayEvent evt) {
                Publicacion pub = (Publicacion)evt.item;
                if(evt.isContext("ver")){
                    Collection<Comentario> items = pub.getComentarios(false);
                    showMostrarComentarios(items);
                }
                else if(evt.isContext("crear")){
                    showCrearComentario(pub, null);
                }
            }
        });
        
        panel5.addListener(new SubmitEventListener() {
            @Override
            public void onSubmit(SubmitEvent evt) {
                if(evt.fields.containsKey("busqueda")){
                    String busqueda = (String) evt.fields.get("busqueda");
                    ArrayList<Publicacion> pubs = socialNetwork.searchPublicaciones(busqueda);
                    panel7.loadItems(pubs, 10);
                    showMostrarPublicaciones();
                }
            }
        });
        
        panel7.addListener(new DisplayEventListener() {
            @Override
            public void onDisplay(DisplayEvent evt) {
                Publicacion pub = (Publicacion)evt.item;
                if(pub != null){
                    showPublicacion(pub);
                }
            }
        });
        
        panel8.addListener(new DisplayEventListener() {
            @Override
            public void onDisplay(DisplayEvent evt) {
                Usuario user = (Usuario)evt.item;
                if(user != null){
                    showUsuario(user);
                }
            }
        });
        
        panel9.addListener(new DisplayEventListener() {
            @Override
            public void onDisplay(DisplayEvent evt) {
                Usuario user = (Usuario)evt.item;
                if(user != null){
                    LinkedList<Publicacion> pubs = user.getPublicaciones();
                    panel7.loadItems(pubs, 10);
                    showMostrarPublicaciones();
                }
            }
        });
        
        panel10.addListener(new SubmitEventListener() {
            @Override
            public void onSubmit(SubmitEvent evt) {
                if(evt.fields.containsKey("contenido")){
                    InfoDialog dialog;
                    String contenido = (String) evt.fields.get("contenido");
                    Publicacion pub = (Publicacion) evt.fields.get("publicacion");
                    Comentario padre = (Comentario) evt.fields.get("comentario");
                    
                    Comentario comment;
                    if(padre != null){
                        comment = socialNetwork.comment(pub, contenido);
                    }
                    else{
                        comment = socialNetwork.comment(padre, contenido);
                    }
                     
                    if(pub == null){
                        dialog = new InfoDialog(main, "No se pudo crear la publicacion!");
                        dialog.setVisible(true);
                    }
                    else{
                        showComentario(comment);
                    }
                }
            }
        });
        
        panel11.addListener(new DisplayEventListener() {
            @Override
            public void onDisplay(DisplayEvent evt) {
                Comentario comment = (Comentario)evt.item;
                if(comment != null){
                    showComentario(comment);
                }
            }
        });
        
        panel12.addListener(new DisplayEventListener() {
            @Override
            public void onDisplay(DisplayEvent evt) {
                if(evt.isContext("ver")){
                    Comentario comment = (Comentario)evt.item;
                    showComentario(comment);
                }
                else if(evt.isContext("crear")){
                    Comentario comment = (Comentario)evt.item;
                    showCrearComentario(comment.getPublicacion(), comment);
                }
            }
        });
        
        panel13.addListener(new SubmitEventListener() {
            @Override
            public void onSubmit(SubmitEvent evt) {
                if(evt.fields.containsKey("minimo")){
                    int minimo = (int)evt.fields.get("minimo");
                    
                    Collection<Publicacion> pubs = socialNetwork.getPublicaciones();
                    Publicacion[] pubArray = new Publicacion[pubs.size()];
                    pubArray = pubs.toArray(pubArray);
                    
                    ArrayList<Publicacion> items = socialNetwork.isViral(pubArray, minimo);

                    panel7.loadItems(items, 10);
                    showMostrarPublicaciones();
                }
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menuPanel = new javax.swing.JPanel();
        btnLogin = new javax.swing.JButton();
        btnRegister = new javax.swing.JButton();
        btnShowPublicaciones = new javax.swing.JButton();
        btnCrearPublicacion = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        panelBottom = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        labelSesion = new javax.swing.JLabel();
        dynamicPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnLogin.setText("Login");
        btnLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLoginMouseClicked(evt);
            }
        });

        btnRegister.setText("Registrar");
        btnRegister.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                showRegister(evt);
            }
        });

        btnShowPublicaciones.setText("Ver Publicaciones");
        btnShowPublicaciones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnShowPublicacionesMouseClicked(evt);
            }
        });

        btnCrearPublicacion.setText("Crear Publicacion");
        btnCrearPublicacion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                showCrearPublicacion(evt);
            }
        });

        jButton2.setText("Buscar Usuarios");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });

        jButton4.setText("Buscar Publicacion");
        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton4MouseClicked(evt);
            }
        });

        jButton1.setText("Ver Pub. Virales");

        javax.swing.GroupLayout menuPanelLayout = new javax.swing.GroupLayout(menuPanel);
        menuPanel.setLayout(menuPanelLayout);
        menuPanelLayout.setHorizontalGroup(
            menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnRegister, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnShowPublicaciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCrearPublicacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        menuPanelLayout.setVerticalGroup(
            menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnShowPublicaciones)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCrearPublicacion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 432, Short.MAX_VALUE)
                .addComponent(btnLogin)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRegister)
                .addContainerGap())
        );

        panelBottom.setBackground(new java.awt.Color(204, 204, 204));
        panelBottom.setLayout(new java.awt.GridLayout(1, 2));

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("Sesion:");
        jPanel1.add(jLabel1);

        labelSesion.setFont(new java.awt.Font("sansserif", 2, 13)); // NOI18N
        labelSesion.setForeground(new java.awt.Color(51, 51, 51));
        labelSesion.setText("-No hay-");
        jPanel1.add(labelSesion);

        panelBottom.add(jPanel1);

        javax.swing.GroupLayout dynamicPanelLayout = new javax.swing.GroupLayout(dynamicPanel);
        dynamicPanel.setLayout(dynamicPanelLayout);
        dynamicPanelLayout.setHorizontalGroup(
            dynamicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 757, Short.MAX_VALUE)
        );
        dynamicPanelLayout.setVerticalGroup(
            dynamicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBottom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(menuPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dynamicPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(menuPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dynamicPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelBottom, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void hidePaneles(){
        panel1.setVisible(false);
        panel2.setVisible(false);
        panel3.setVisible(false);
        panel4.setVisible(false);
        panel5.setVisible(false);
        panel6.setVisible(false);
        panel7.setVisible(false);
        panel8.setVisible(false);
        panel9.setVisible(false);
        panel10.setVisible(false);
        panel11.setVisible(false);
        panel12.setVisible(false);
        panel13.setVisible(false);
    }
    
    private void showPublicacion(Publicacion pub) {
        panel4.setPublicacion(pub);
        hidePaneles();
        panel4.setVisible(true);
    }
    
    private void showUsuario(Usuario user) {
        panel9.setUsuario(user);
        hidePaneles();
        panel9.setVisible(true);
    }
    
    private void showComentario(Comentario comment) {
        panel12.setComentario(comment);
        hidePaneles();
        panel12.setVisible(true);
    }
    
    private void showRegister(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_showRegister
        if(btnRegister.isEnabled()){
            hidePaneles();
            panel2.setVisible(true);
        }
    }//GEN-LAST:event_showRegister

    private void showCrearPublicacion(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_showCrearPublicacion
        if(socialNetwork.hasSesion()){
            hidePaneles();
            panel3.setVisible(true);
        }
        else{
            showLogin();
            InfoDialog dialog = new InfoDialog(this, "Para crear una publicacion, se debe iniciar sesion!");
            dialog.setVisible(true);
        }
    }//GEN-LAST:event_showCrearPublicacion

    private void showCrearComentario(Publicacion pub, Comentario comment) {                                      
        if(socialNetwork.hasSesion()){
            hidePaneles();
            if(comment == null){
                panel10.setInfo(pub);
            }
            else{
                panel10.setInfo(pub, comment);
            }
            panel10.setVisible(true);
        }
        else{
            showLogin();
            InfoDialog dialog = new InfoDialog(this, "Para crear un comentario, se debe iniciar sesion!");
            dialog.setVisible(true);
        }
    }                                     

    private void showLogin() {
        hidePaneles();
        panel1.setVisible(true);
    }

    private void btnLoginMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLoginMouseClicked
        if(socialNetwork.hasSesion()){
            socialNetwork.logout();
            labelSesion.setText("-No hay-");
            btnLogin.setText("Login");
            btnRegister.setEnabled(true);
        }
        showLogin();
    }//GEN-LAST:event_btnLoginMouseClicked

    private void jButton4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MouseClicked
        hidePaneles();
        panel5.setVisible(true);
    }//GEN-LAST:event_jButton4MouseClicked

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        hidePaneles();
        panel6.setVisible(true);
    }//GEN-LAST:event_jButton2MouseClicked

    private void btnShowPublicacionesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnShowPublicacionesMouseClicked
        Collection<Publicacion> pubs = socialNetwork.getPublicaciones();
        panel7.loadItems(pubs, 10);
        showMostrarPublicaciones();
    }//GEN-LAST:event_btnShowPublicacionesMouseClicked

    private void showMostrarPublicaciones() {
        hidePaneles();
        panel7.setVisible(true);
    }

    private void showMostrarComentarios(Collection<Comentario> items) {
        panel11.loadItems(items, 15);
        hidePaneles();
        panel11.setVisible(true);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCrearPublicacion;
    private javax.swing.JButton btnLogin;
    private javax.swing.JButton btnRegister;
    private javax.swing.JButton btnShowPublicaciones;
    private javax.swing.JPanel dynamicPanel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel labelSesion;
    private javax.swing.JPanel menuPanel;
    private javax.swing.JPanel panelBottom;
    // End of variables declaration//GEN-END:variables
}
