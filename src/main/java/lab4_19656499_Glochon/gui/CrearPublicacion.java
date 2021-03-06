package lab4_19656499_Glochon.gui;

import java.util.ArrayList;
import javax.swing.event.EventListenerList;
import lab4_19656499_Glochon.SocialNetwork;
import lab4_19656499_Glochon.Usuario;

/**
 *
 * @author EstebanGlochonUSACH [https://github.com/EstebanGlochonUSACH]
 */
public class CrearPublicacion extends javax.swing.JPanel implements Submitable {
    
    private final SocialNetwork socialNetwork;

    /**
     * Creates new form CrearPublicacion
     * @param socialNetwork
     */
    public CrearPublicacion(SocialNetwork socialNetwork) {
        this.socialNetwork = socialNetwork;
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton4 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        jLabel3.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        jLabel3.setText("Crear Publicacion");

        jLabel1.setText("Contenido");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jButton4.setText("Publicar");
        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton4MouseClicked(evt);
            }
        });

        jButton1.setText("Publicar a Otros");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 438, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addComponent(jButton1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MouseClicked
        String content = jTextArea1.getText().trim();

        if(content.length() == 0){
            InfoDialog dialog = new InfoDialog(null, "El campo '"+ jLabel1.getText() +"' esta vacio!");
            dialog.setVisible(true);
            return;
        }

        jTextArea1.setText("");

        SubmitEvent event = new SubmitEvent(this);
        event.fields.put("contenido", content);
        emitEvent(event);
    }//GEN-LAST:event_jButton4MouseClicked

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        String content = jTextArea1.getText().trim();

        if(content.length() == 0){
            InfoDialog dialog = new InfoDialog(null, "El campo '"+ jLabel1.getText() +"' esta vacio!");
            dialog.setVisible(true);
            return;
        }

        if(socialNetwork.hasSesion()){
            Usuario user = socialNetwork.getSesion();
            ArrayList<Usuario> users = user.getContactos();
            
            if(users.size() > 0){
                SeleccionarUsuarios dialog = new SeleccionarUsuarios(null, users);

                CrearPublicacion self = this;
                dialog.addListener(new SubmitEventListener() {
                    @Override
                    public void onSubmit(SubmitEvent evt) {
                        if(!evt.fields.containsKey("usuarios")) return;
                        Usuario[] usuarios = (Usuario[])evt.fields.get("usuarios");
                        jTextArea1.setText("");
                        SubmitEvent event = new SubmitEvent(self);
                        event.fields.put("contenido", content);
                        event.fields.put("usuarios", usuarios);
                        emitEvent(event);
                    }
                });

                dialog.setVisible(true);
            }
            else{
                jTextArea1.setText("");
                SubmitEvent event = new SubmitEvent(this);
                event.fields.put("contenido", content);
                emitEvent(event);
            }
        }
        else{
            jTextArea1.setText("");
            SubmitEvent event = new SubmitEvent(this);
            event.fields.put("contenido", content);
            emitEvent(event);
        }
    }//GEN-LAST:event_jButton1MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables

    /**
     * Lista de registros de listener para eventos.
     */
    protected EventListenerList listenerList = new EventListenerList();

    /**
     * Registra un nuevo listener.
     * @param listener
     */
    @Override
    public void addListener(SubmitEventListener listener) {
        listenerList.add(SubmitEventListener.class, listener);
    }

    /**
     * Elimina un listener antes registrado.
     * @param listener
     */
    @Override
    public void removeListener(SubmitEventListener listener) {
        listenerList.remove(SubmitEventListener.class, listener);
    }

    /**
     * Emite un evento en el contexto de la instacia del objeto.
     * @param evt
     */
    @Override
    public void emitEvent(SubmitEvent evt) {
        for(SubmitEventListener listener: listenerList.getListeners(SubmitEventListener.class)) {
            listener.onSubmit(evt);
        }
    }
}
