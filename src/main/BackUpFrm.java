/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.awt.HeadlessException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ritch
 */
public class BackUpFrm extends javax.swing.JFrame {

    /**
     * Creates new form BackUpFrm
     */
    Connection Conn = ConnClass.connectDB();

    PreparedStatement pst = null;
    ResultSet rs = null;

    String BackUpFolder, RestoreFile;

    public BackUpFrm() {
        initComponents();
        new ConnClass().setFrameIcon(this);

        jLabel5.setVisible(false);
        jProgressBar1.setVisible(false);
    }

    private void BackUpData() {
        SwingWorker<Void, Integer> worker = new SwingWorker<Void, Integer>() {
            @Override
            protected Void doInBackground() throws Exception {
                jLabel5.setText("Backing up data...");
                jLabel5.setVisible(true);
                jProgressBar1.setVisible(true);
                Date date = new Date();
                SimpleDateFormat sdft = new SimpleDateFormat("dd-MM-yyyy_HMMSS");
                String timestamp = sdft.format(date);
                String backupFile = BackUpFolder + "/Backup" + timestamp + ".bak";
                try {
                    FileOutputStream fos;
                    FileInputStream fis;
                    byte[] b;

                    String Db_path = "C:/Acme/Exam System/ExamsDB.db";

                    fos = new FileOutputStream(backupFile);
                    fis = new FileInputStream(Db_path);
                    b = new byte[1024];
                    int i;
                    while ((i = fis.read(b)) >= 0) {
                        fos.write(b, 0, i);
                        fos.flush();
                    }
                    fos.close();
                    fis.close();
                    Runtime runtime = Runtime.getRuntime();
                    runtime.exec(new String[]{"cmd.exe", "/c", "compact /c /f " + backupFile});
                    JOptionPane.showMessageDialog(null, "Backup completed succesfully");
                } catch (HeadlessException | IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void done() {
                jLabel5.setVisible(false);
                jProgressBar1.setVisible(false);

            }
        };
        worker.execute();
    }

    private void RestoreDatabase() {
        SwingWorker<Void, Integer> worker = new SwingWorker<Void, Integer>() {
            @Override
            protected Void doInBackground() throws Exception {
                //Start backup first
                jLabel5.setText("Backing up data...");
                jLabel5.setVisible(true);
                jProgressBar1.setVisible(true);
                Date date = new Date();
                SimpleDateFormat sdft = new SimpleDateFormat("dd-MM-yyyy_HMMSS");
                String timestamp = sdft.format(date);
                String backupFile = BackUpFolder + "/Backup" + timestamp + ".bak";
                try {
                    FileOutputStream fos;
                    FileInputStream fis;
                    byte[] b;

                    String Db_path = "C:/Acme/Exam System/ExamsDB.db";

                    fos = new FileOutputStream(backupFile);
                    fis = new FileInputStream(Db_path);
                    b = new byte[1024];
                    int i;
                    while ((i = fis.read(b)) >= 0) {
                        fos.write(b, 0, i);
                        fos.flush();
                    }
                    fos.close();
                    fis.close();
                    Runtime runtime = Runtime.getRuntime();
                    runtime.exec(new String[]{"cmd.exe", "/c", "compact /c /f " + backupFile});
                    JOptionPane.showMessageDialog(null, "Backup completed succesfully");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //end backup
                jLabel5.setText("Restoring database...");
                jLabel5.setVisible(true);
                jProgressBar1.setVisible(true);
                try {
                    Runtime runtime = Runtime.getRuntime();
                    runtime.exec(new String[]{"cmd.exe", "/c", "compact /u " + RestoreFile});
                    Conn.close();
                    FileOutputStream fos;
                    FileInputStream fis;
                    byte[] b;
                    String old_db = "C:/Acme/Exam System/ExamsDB.db";
                    new File(old_db).delete();

                    //Restore the database
                    fos = new FileOutputStream(old_db);
                    fis = new FileInputStream(RestoreFile);
                    b = new byte[1024];
                    int i;
                    while ((i = fis.read(b)) >= 0) {
                        fos.write(b, 0, i);
                        fos.flush();
                    }
                    fos.close();
                    fis.close();
                    runtime.exec(new String[]{"cmd.exe", "/c", "compact /c /f " + RestoreFile});
                    JOptionPane.showMessageDialog(null, "Restoration completed succesfully");
                } catch (HeadlessException | IOException | SQLException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void done() {
                jLabel5.setVisible(false);
                jProgressBar1.setVisible(false);
            }
        };
        worker.execute();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        CustomQueryDlg = new javax.swing.JDialog();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtQuery = new javax.swing.JTextArea();
        jButton3 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_result = new javax.swing.JTable();
        jButton4 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        btnRestore = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();
        jPanel8 = new javax.swing.JPanel();
        jTextField2 = new javax.swing.JTextField();
        btnBackUp = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        CustomQueryDlg.setResizable(false);
        CustomQueryDlg.setType(java.awt.Window.Type.UTILITY);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 51, 51)));

        txtQuery.setColumns(20);
        txtQuery.setRows(5);
        txtQuery.setBorder(javax.swing.BorderFactory.createTitledBorder("Query Statement"));
        jScrollPane1.setViewportView(txtQuery);

        jButton3.setText("Run");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        tbl_result.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tbl_result.setFillsViewportHeight(true);
        jScrollPane2.setViewportView(tbl_result);

        jButton4.setText("Export");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 676, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout CustomQueryDlgLayout = new javax.swing.GroupLayout(CustomQueryDlg.getContentPane());
        CustomQueryDlg.getContentPane().setLayout(CustomQueryDlgLayout);
        CustomQueryDlgLayout.setHorizontalGroup(
            CustomQueryDlgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CustomQueryDlgLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        CustomQueryDlgLayout.setVerticalGroup(
            CustomQueryDlgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel7.setBackground(new java.awt.Color(204, 204, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Restore Database", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(255, 255, 255))); // NOI18N

        btnRestore.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnRestore.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/images/Download_24x24.png"))); // NOI18N
        btnRestore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRestoreActionPerformed(evt);
            }
        });

        jTextField1.setEnabled(false);

        jButton6.setBackground(new java.awt.Color(255, 255, 255));
        jButton6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton6.setText("GO");
        jButton6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102), 2));
        jButton6.setContentAreaFilled(false);
        jButton6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton6.setOpaque(true);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap(343, Short.MAX_VALUE)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jTextField1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnRestore)))
                .addGap(23, 23, 23))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnRestore)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel5.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, 500, 100));

        jPanel6.setBackground(new java.awt.Color(0, 204, 51));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Easy Back up And Restore");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 590, Short.MAX_VALUE)
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel6Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 449, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(131, Short.MAX_VALUE)))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jPanel5.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 590, -1));

        jLabel5.setText("Restoring you database please wait..");
        jPanel5.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 330, 230, -1));

        jProgressBar1.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jProgressBar1.setForeground(new java.awt.Color(0, 153, 0));
        jProgressBar1.setStringPainted(true);
        jPanel5.add(jProgressBar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 350, 480, 10));

        jPanel8.setBackground(new java.awt.Color(0, 153, 102));
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Backup Database", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(255, 255, 255))); // NOI18N

        jTextField2.setEnabled(false);

        btnBackUp.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnBackUp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/images/Upload_24x24.png"))); // NOI18N
        btnBackUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackUpActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setText("GO");
        jButton1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102), 2));
        jButton1.setContentAreaFilled(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.setOpaque(true);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBackUp)))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnBackUp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel5.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, 500, 100));

        jButton2.setText("Custom Query");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(393, 303, 140, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 584, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 385, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnRestoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRestoreActionPerformed
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Backup Files", "bak");
        JFileChooser fc = new JFileChooser();
        fc.setFileFilter(filter);
        fc.setDialogTitle("Select A folder to Restore Data From");
        fc.setFileSelectionMode(2);
        fc.setCurrentDirectory(fc.getFileSystemView().getParentDirectory(new File("C:\\")));
        int option = fc.showOpenDialog(null);
        if (option == JFileChooser.APPROVE_OPTION) {
            RestoreFile = fc.getSelectedFile().getPath();
            if (RestoreFile != null) {
                jTextField1.setText(RestoreFile.replaceAll("\\\\", "/"));
            }
        }
    }//GEN-LAST:event_btnRestoreActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        RestoreFile = jTextField1.getText();
        if (!RestoreFile.isEmpty()) {
            BackUpFolder = FileSystemView.getFileSystemView().getDefaultDirectory().getPath() + "/Acme";
            RestoreDatabase();
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void btnBackUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackUpActionPerformed
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Folder", "db");
        JFileChooser fc = new JFileChooser();
        fc.setFileFilter(filter);
        fc.setDialogTitle("Select A folder to save you back ups");
        fc.setFileSelectionMode(2);
        fc.setCurrentDirectory(fc.getFileSystemView().getParentDirectory(new File("C:\\")));
        int option = fc.showSaveDialog(null);

        if (option == JFileChooser.APPROVE_OPTION) {
            String FolderPath = fc.getSelectedFile().getAbsolutePath();
            if (!(FolderPath == null)) {
                BackUpFolder = FolderPath + "/";
                jTextField2.setText(BackUpFolder.replaceAll("\\\\", "/"));
            }
        }
    }//GEN-LAST:event_btnBackUpActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        BackUpFolder = jTextField2.getText();
        if (BackUpFolder != null) {
            try {
                System.out.println("backing up");
                BackUpData();
            } catch (Exception ex) {
                Logger.getLogger(AdminPanelFrm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        try {
            Conn.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        this.dispose();
        new AdminPanelFrm().setVisible(true);
    }//GEN-LAST:event_formWindowClosing

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed

    }//GEN-LAST:event_formWindowClosed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        String QueryText = txtQuery.getText();
        QueryText = QueryText.toLowerCase();
        if (QueryText.contains(";")) {
            String[] parts = QueryText.split(";");
            for (String part : parts) {
                if (!(part == null || part.isEmpty())) {
                    runQuery(part);
                }
            }
        } else {
            runQuery(QueryText);
        }

        JOptionPane.showMessageDialog(null, "Done");
    }//GEN-LAST:event_jButton3ActionPerformed

    private void runQuery(String QueryText) {
        try {
            pst = Conn.prepareStatement(QueryText);
            if (QueryText.trim().startsWith("select")) {
                rs = pst.executeQuery();

                ResultSetMetaData rsmd = pst.getMetaData();
                int c = rsmd.getColumnCount();

                String[] column_names = new String[c];//column names

                for (int i = 0; i < c; i++) {
                    column_names[i] = rsmd.getColumnName(i + 1);
                }

                DefaultTableModel model = new DefaultTableModel(null, column_names);
                while (rs.next()) {
                    String[] rowData = new String[c];
                    for (int i = 0; i < c; i++) {
                        rowData[i] = rs.getString(i + 1);
                    }
                    model.addRow(rowData);
                }
                tbl_result.setModel(model);
            } else {
                pst.executeUpdate();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        CustomQueryDlg.pack();
        CustomQueryDlg.setLocationRelativeTo(this);
        CustomQueryDlg.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        JFileChooser fc = new JFileChooser();
        int option = fc.showSaveDialog(this);
        if (option == 0) {
            String filename = fc.getSelectedFile().getName();
            String path = fc.getSelectedFile().getParentFile().getPath();
            ExportTableToCSV.ExportTable(tbl_result, filename, path);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
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
            java.util.logging.Logger.getLogger(BackUpFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BackUpFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BackUpFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BackUpFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BackUpFrm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog CustomQueryDlg;
    private javax.swing.JButton btnBackUp;
    private javax.swing.JButton btnRestore;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTable tbl_result;
    private javax.swing.JTextArea txtQuery;
    // End of variables declaration//GEN-END:variables
}
