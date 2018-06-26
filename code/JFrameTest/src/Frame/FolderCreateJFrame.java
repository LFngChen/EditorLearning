/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frame;

import javax.swing.JOptionPane;
import po.Folder;
import service.FolderService;
import service.Locality;

/**
 *
 * @author ���Ƹ�
 */
public class FolderCreateJFrame extends javax.swing.JDialog {
    static MainJFrame mf;
    /**
     * Creates new form FolderCreateJFrame
     */
   /**
     * Creates new form LoginFrame
     */
    public FolderCreateJFrame(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    public FolderCreateJFrame(java.awt.Frame parent, MainJFrame mf, boolean model) {
        super(parent, model);
        this.mf = mf;
        initComponents();
        this.setLocationRelativeTo(null);
        //getCheckCode();

    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        folderNameText = new javax.swing.JTextField();
        createFolderButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("΢���ź�", 1, 18)); // NOI18N
        jLabel1.setText("�����ļ���");

        jLabel2.setFont(new java.awt.Font("΢���ź�", 0, 14)); // NOI18N
        jLabel2.setText("�ļ�����");

        createFolderButton.setBackground(new java.awt.Color(0, 204, 204));
        createFolderButton.setFont(new java.awt.Font("΢���ź�", 0, 14)); // NOI18N
        createFolderButton.setText("ȷ���½�");
        createFolderButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createFolderButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(createFolderButton))
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(folderNameText, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(folderNameText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(createFolderButton)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void createFolderButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createFolderButtonActionPerformed
        // TODO add your handling code here:
         //�жϸ��û��Ƿ��Ѿ������ļ�����ͬ���ļ�
        if(FolderService.searchFolderByNameUserID(mf.user.getId(), folderNameText.getText()) != null){
            mf.showMessageDialog("���Ѿ���������Ϊ" + folderNameText.getText() + "���ļ��У�������������");
            return;
        }
        //����ļ���·���к��пո�
        if(folderNameText.getText().contains(" ")){
            mf.showMessageDialog("�ļ��������к��зǷ��ַ��ո�");
            return;
        }
        
        Folder folder =  FolderService.createFolder(folderNameText.getText(), mf.user);
        if(folder == null){
            JOptionPane.showMessageDialog(
                    this,
                    "�����ļ���ʧ��",
                    "��Ϣ��ʾ",
                    JOptionPane.WARNING_MESSAGE
            );
            this.setVisible(false);
            return;
        }
        else{
            //��������ͼ�ĸ��º����������û����ļ���
            mf.flushFolderListAdd(folder.getName());
            this.setVisible(false);
            //�ڱ��ش�����Ӧ���ļ���
            String path = mf.user.getAccount() + "/" + folder.getName();
            Locality.createDirectory(path);
        }
    }//GEN-LAST:event_createFolderButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                FolderCreateJFrame dialog = new FolderCreateJFrame(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton createFolderButton;
    private javax.swing.JTextField folderNameText;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}