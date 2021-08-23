package lab4_19656499_Glochon.gui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;

/**
 *
 * @author EstebanGlochonUSACH [https://github.com/EstebanGlochonUSACH]
 * @param <Item>
 */
public abstract class Paginacion<Item> extends javax.swing.JPanel {
    
    private Collection<Item> items = null;
    private Integer pageCurrent = 1;
    private Integer pageTotal = 1;
    private Integer itemsPerPage = 10;
    private javax.swing.JPanel[] tmpItems = null;

    /**
     * Crea un panel para visualizar Publicaciones.
     * @param titulo
     */
    public Paginacion(String titulo) {
        initComponents();
        this.items = null;

        labelTitulo.setText(titulo);
        labelPageCurrent.setText(pageCurrent.toString());
        labelPageTotal.setText(pageTotal.toString());
        toggleButtons();
    }
    
    public void loadItems(Collection<Item> items, int itemsPerPage){
        this.items = items;
        this.itemsPerPage = itemsPerPage;

        this.pageTotal = (int)Math.ceil((double)items.size() / (double)itemsPerPage);
        labelPageCurrent.setText(pageCurrent.toString());
        labelPageTotal.setText(pageTotal.toString());
        
        toggleButtons();
        
        tmpItems = new javax.swing.JPanel[itemsPerPage];
        
        addItemWidgets();
    }
    
    private void toggleButtons(){
        if(pageCurrent == 1){
            btnPrev.setEnabled(false);
        }
        else{
            btnPrev.setEnabled(true);
        }
        if(Objects.equals(pageCurrent, pageTotal)){
            btnNext.setEnabled(false);
        }
        else{
            btnNext.setEnabled(true);
        }
    }
    
    /**
     *
     * @return
     */
    protected ArrayList<Item> getItems(){
        Iterator<Item> iter = items.iterator();
        Item aux;
        ArrayList<Item> result = new ArrayList<>();
        int index = 0;
        int startingIndex = (pageCurrent - 1) * itemsPerPage;
        int endingIndex = startingIndex + itemsPerPage;
        
        while(iter.hasNext()){
            aux = iter.next();
            if(index >= startingIndex && index < endingIndex){
                result.add(aux);
            }
            else if(index >= endingIndex) break;
            ++index;
        }
        
        return result;
    }
    
    abstract protected javax.swing.JPanel transformItem(Item item);
    
    protected void addItemWidgets(){
        ArrayList<Item> items = getItems();
        Iterator<Item> iter = items.iterator();
        Item aux;
        javax.swing.JPanel tmp;
        int index = 0;
        
        dynamicPanel.removeAll();
        
        while(iter.hasNext()){
            aux = iter.next();
            tmp = transformItem(aux);
            dynamicPanel.add(tmp);
            tmpItems[index] = tmp;
            ++index;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelTitulo = new javax.swing.JLabel();
        infoPanel = new javax.swing.JPanel();
        btnPrev = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        labelPageCurrent = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        labelPageTotal = new javax.swing.JLabel();
        btnNext = new javax.swing.JButton();
        scrollPanel = new javax.swing.JScrollPane();
        dynamicPanel = new javax.swing.JPanel();

        labelTitulo.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        labelTitulo.setText("Titulo");

        btnPrev.setText("Anterior");
        btnPrev.setPreferredSize(new java.awt.Dimension(100, 23));
        btnPrev.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnPrevMouseClicked(evt);
            }
        });
        infoPanel.add(btnPrev);

        jLabel1.setText("Pagina:");
        infoPanel.add(jLabel1);

        labelPageCurrent.setText("0");
        infoPanel.add(labelPageCurrent);

        jLabel4.setText("/");
        infoPanel.add(jLabel4);

        labelPageTotal.setText("0");
        infoPanel.add(labelPageTotal);

        btnNext.setText("Siguiente");
        btnNext.setPreferredSize(new java.awt.Dimension(100, 23));
        btnNext.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnNextMouseClicked(evt);
            }
        });
        infoPanel.add(btnNext);

        dynamicPanel.setLayout(new javax.swing.BoxLayout(dynamicPanel, javax.swing.BoxLayout.Y_AXIS));
        scrollPanel.setViewportView(dynamicPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(scrollPanel)
                    .addComponent(infoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 738, Short.MAX_VALUE)
                    .addComponent(labelTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(infoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnPrevMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPrevMouseClicked
        if(pageCurrent > 1){
            pageCurrent -= 1;
            labelPageCurrent.setText(pageCurrent.toString());
            toggleButtons();
            addItemWidgets();
        }
    }//GEN-LAST:event_btnPrevMouseClicked

    private void btnNextMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNextMouseClicked
        if(pageCurrent < pageTotal){
            pageCurrent += 1;
            labelPageCurrent.setText(pageCurrent.toString());
            toggleButtons();
            addItemWidgets();
        }
    }//GEN-LAST:event_btnNextMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrev;
    private javax.swing.JPanel dynamicPanel;
    private javax.swing.JPanel infoPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel labelPageCurrent;
    private javax.swing.JLabel labelPageTotal;
    private javax.swing.JLabel labelTitulo;
    private javax.swing.JScrollPane scrollPanel;
    // End of variables declaration//GEN-END:variables
}
