
package minesweeper;
import javax.swing.JToggleButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Sweep extends javax.swing.JFrame {
    /*
       -2 opened but no bomb
       -1 bomb
        0 not open
        1-8: number of bombs around
    */
    
    final int wid=9,hei=9, noOfBombs = 10;
    JToggleButton[][] blocks= new JToggleButton[hei][wid];
    int[][] blox=new int[hei][wid];
    boolean first =false,canPlay = true;
    
    ActionListener Listen = new ActionListener(){
        public void actionPerformed(ActionEvent e){
            int i=0,j=0;
            boolean found=false;
            for(i=0;i<hei;i++){
                for(j=0;j<wid;j++){
                    if(e.getSource()==blocks[i][j]){
                        found=true;
                        break;
                    }
                }
                    if(found) break;
                }
                if(canPlay)
                {
                    blocks[i][j].setSelected(true);
                    if(!first){
                        spawn(i,j);
                        first=true;
                    }
                
                if(blox[i][j]!=-1){
                open(i,j);
                reval();
                }
                else
                    lose();
                checkWin();
                }
                else
                    reval();
        }
    };
    
    private void lose(){                                            //bomb touched.
        canPlay=false;
        for(int i=0;i<hei;i++){
                for(int j=0;j<wid;j++){
                    if(blox[i][j]==-1){
                        //blocks[i][j].setText(" "+blox[i][j]);
                        blocks[i][j].setIcon(new ImageIcon(getClass().getResource("ms2.jpg")));
                        blocks[i][j].setSelected(true);
                    }
                }
        }
    }
    
    
    private void open(int y,int x){
        if(y<0 || x<0 ||x>wid-1 || y>hei-1 || blox[y][x]!=0) return;
        int bombs=0;
        for(int i=y-1;i<=y+1;i++){
            for(int j=x-1;j<=x+1;j++){
                if(!(j<0 || i<0 ||j>wid-1 || i>hei-1)&&blox[i][j]==-1)        //counting bombs around x,y
                    bombs++;
                }
                }
        
           
            if(bombs==0){
                blox[y][x]=-2;                                               // its neither bomb nor besides any of the bombs.
                    for(int i=y-1;i<=y+1;i++){
                for(int j=x-1;j<=x+1;j++){
                    if(i!=y ||j!=x ) open(i,j);                              // if nothing found go recursively
            }
        }
    }
            else 
                blox[y][x]=bombs;                                            // if the cell is beside bomb then give it the number of bombs surrounding it.
                
  }
    
    
    private void reval(){              //to check which one is open and which one is not
        for(int i=0;i<hei;i++){
                for(int j=0;j<wid;j++){
                    if(blox[i][j]==0){
                    blocks[i][j].setText("");
                    blocks[i][j].setSelected(false);
                    }
                    if(blox[i][j]==-2){
                    blocks[i][j].setText("");
                    blocks[i][j].setSelected(true);         // now this button can not be selecetd again.
                    }
                    if(blox[i][j]>0){
                    blocks[i][j].setText(""+blox[i][j]);                // printing the number of bombs it is surrounded with.
                    blocks[i][j].setSelected(true);    
                    }
                    if(!canPlay && blox[i][j]==-1)blocks[i][j].setSelected(true);           //when game becomes over after encountering of any bomb then all buttons should become ineligible to be clicked.
                  }
                }
        jPanel1.repaint();          //after end of the game repainting is done.
    }
    private void checkWin(){
        boolean won = true;
        for(int i=0;i<hei;i++){
            for(int j=0;j<wid;j++){
                if(blox[i][j]==0){
                    won = false;
                    break;
                }
            }
            if(!won) break;
        }
        if(won) {
            javax.swing.JOptionPane.showMessageDialog(null,"CONGRATULATIONS!!");                //pop up after win.
            canPlay=false;    
        }
        
    }
    
    
    //for bomb
    private void spawn(int y,int x){                                                        // fixing the bombs after the first click.
        int i,j;
        for(int k=1;k<=noOfBombs;k++){
            do{
                i=(int)(Math.random()*(wid-.01));
                j=(int)(Math.random()*(hei-.01));
              }   //.01 to cover the edges.
              while(blox[i][j]== -1 || (i==y && j==x));
            
            blox[i][j]=-1;
            //blocks[i][j].setText("boom");
        }
    }
    
    public Sweep() {
        initComponents();
        for(int i=0;i<hei;i++)
            for(int j=0;j<wid;j++){
                blocks[i][j]=new JToggleButton();
                blocks[i][j].setSize(jPanel1.getWidth()/wid,jPanel1.getHeight()/hei);
                jPanel1.add(blocks[i][j]);
                blocks[i][j].setLocation(j*jPanel1.getWidth()/wid,i*jPanel1.getHeight()/hei);
                blocks[i][j].addActionListener(Listen);
            }
        first=false;
        canPlay=true;
    }
    
    private void resiz(){
        for(int i=0;i<hei;i++)
            for(int j=0;j<wid;j++){
                blocks[i][j].setSize(jPanel1.getWidth()/wid,jPanel1.getHeight()/hei);
                jPanel1.add(blocks[i][j]);
                blocks[i][j].setLocation(j*jPanel1.getWidth()/wid,i*jPanel1.getHeight()/hei);
            }
    }
    
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(51, 0, 204));
        jPanel1.setForeground(new java.awt.Color(255, 0, 255));
        jPanel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jPanel1.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                jPanel1ComponentResized(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1690, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 821, Short.MAX_VALUE)
        );

        jMenu1.setText("GAME");

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setBackground(new java.awt.Color(204, 255, 204));
        jMenuItem2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jMenuItem2.setText("New Game");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jPanel1ComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanel1ComponentResized
        resiz();// for adjusting the size of jpanel
    }//GEN-LAST:event_jPanel1ComponentResized

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        blox=new int[hei][wid];
        reval();
        canPlay=true;
        first=false;
        //jPanel1.repaint();
        for(int i=0;i<hei;i++)
            for(int j=0;j<wid;j++)
                   blocks[i][j].setIcon(null);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    public static void main(String args[]) {
        
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Sweep.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Sweep.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Sweep.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Sweep.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Sweep().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
