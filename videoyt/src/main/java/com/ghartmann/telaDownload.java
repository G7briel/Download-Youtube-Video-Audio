/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.ghartmann;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;

import net.coobird.thumbnailator.Thumbnails;

/**
 *
 * @author Gabriel
 */
public class telaDownload extends javax.swing.JFrame {

    Video videos = new Video();

    private static final String API_KEY = "AIzaSyA_nf5sD833P4sNQw8Q72Mvvj-is_2zUkw";

    /**
     * Creates new form telaDownload
     */
    public telaDownload() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        jLink = new javax.swing.JTextField();
        jFormato = new javax.swing.JComboBox<>();
        jDownload = new javax.swing.JButton();
        jDiretório = new javax.swing.JButton();
        jInfo = new javax.swing.JLabel();
        jThumb = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(153, 153, 153));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLink.setText("Insira o link do video");
        jLink.setForeground(Color.GRAY);
        jLink.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e){
                if (jLink.getText().equals("Insira o link do video")) {
                    jLink.setText("");
                    jLink.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e){
                if(jLink.getText().isEmpty()){
                    jLink.setText("Insira o link do video");
                    jLink.setForeground(Color.GRAY);
                }
            }
        });
        jLink.addActionListener(this::jLinkActionPerformed);

        jFormato.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "mp4", "mp3"}));
        jFormato.addActionListener(this::jFormatoActionPerformed);

        jDownload.setText("Download");
        jDownload.addActionListener(this::jDownloadActionPerformed);

        jDiretório.setText("Diretório");
        jDiretório.addActionListener(this::jDiretórioActionPerformed);

        jInfo.setText("");

        jThumb.setText("");
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLink, javax.swing.GroupLayout.PREFERRED_SIZE, 604, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                .addGap(85, 85, 85)
                .addComponent(jDownload, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)))
        .addGap(34, 34, 34)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
            .addComponent(jFormato, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jDiretório, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE))
        .addContainerGap(38, Short.MAX_VALUE))
    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
        .addGap(56, 56, 56)
        .addComponent(jThumb, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addComponent(jInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(58, 58, 58))
);
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jThumb, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 95, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLink, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFormato, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDiretório, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDownload, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(74, 74, 74))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLinkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jLinkActionPerformed
        videos.setLink(jLink.getText());
        String videoID = extrairVideoId(videos.getLink());

        if (videoID != null) {
            videos.setTitulo( getVideoTitulo(videos.getLink()));
            String thumbnailUrl = "https://img.youtube.com/vi/" + videoID + "/0.jpg";
            carregarThumb(thumbnailUrl, jThumb);
            if(videos.getTitulo() != null){
                jInfo.setText("<html><div style='width: 380px;'>" + videos.getTitulo() + "</div></html>");
                jInfo.setFont(new Font("Arial", Font.BOLD, 13));
                jInfo.setHorizontalAlignment(SwingConstants.CENTER);
                jInfo.setVerticalAlignment(SwingConstants.CENTER);
            }else{
                jInfo.setText("Não foi possível carregar o título do Video");
            }
        } else {
            System.out.println("Link do YouTube inválido.");
        }
    }//GEN-LAST:event_jLinkActionPerformed

    private void jDownloadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDownloadActionPerformed
       String linkVideo = videos.getLink().trim();
       if(!linkVideo.isEmpty()){
            String downloadPath = videos.getDiretorio();
            downloadVideo(linkVideo, downloadPath);
       } else{
        JOptionPane.showMessageDialog(this, "Por favor, insira o link do video");
       }
    }//GEN-LAST:event_jDownloadActionPerformed

    private void jFormatoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormatoActionPerformed
        videos.setFormato((String) jFormato.getSelectedItem());
    }//GEN-LAST:event_jFormatoActionPerformed

    private void jDiretórioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDiretórioActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Escolha a pasta para download");
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int result = fileChooser.showOpenDialog(null);
        if(result == JFileChooser.APPROVE_OPTION){
            File diretorioSelecionado = fileChooser.getSelectedFile();
            videos.setDiretorio(diretorioSelecionado.getAbsolutePath());
            JOptionPane.showMessageDialog(null, "Diretorio Escolhido: " + diretorioSelecionado);
        }
    }//GEN-LAST:event_jDiretórioActionPerformed

    private void carregarThumb(String thumbUrl, JLabel jThumb) {
        try {
            URL url = new URL(thumbUrl);
            ImageIcon imageIcon = new ImageIcon(url);
            int novaLargura = 420;
            int novaAltura = calcularAlturaProporcional(imageIcon, novaLargura);
            ImageIcon resizedIcon = redimensionarThumb(imageIcon, novaLargura, novaAltura);
            jThumb.setIcon(resizedIcon);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(telaDownload.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new telaDownload().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jDiretório;
    private javax.swing.JButton jDownload;
    private javax.swing.JComboBox<String> jFormato;
    private javax.swing.JLabel jInfo;
    private javax.swing.JTextField jLink;
    private javax.swing.JLabel jThumb;
    // End of variables declaration//GEN-END:variables

    private void downloadVideo(String linkVideo, String downloadPath) {
        // Cria um SwingWorker para executar o download em segundo plano
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                String command;
                try {
                    if (videos.getFormato().equals("mp3")) {
                        // Comando para baixar apenas o áudio em MP3
                        command = "yt-dlp -x --audio-format mp3 -o \"" + downloadPath + "/%(title)s.%(ext)s\" " + linkVideo;
                    } else {
                        // Comando para baixar o vídeo no melhor formato MP4 disponível
                        command = "yt-dlp -f \"bv*[ext=mp4]+ba[ext=m4a]/b[ext=mp4]\" -o \"" + downloadPath + "/%(title)s.%(ext)s\" " + linkVideo;
                    }

                    // Executa o comando
                    Process process = Runtime.getRuntime().exec(command);
                    BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                    int exitCode = process.waitFor();

                    // Verifica se o download foi bem-sucedido
                    if (exitCode == 0) {
                        JOptionPane.showMessageDialog(telaDownload.this, "Download Completo!");
                    } else {
                        JOptionPane.showMessageDialog(telaDownload.this, "Erro ao baixar o vídeo.");
                    }
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(telaDownload.this, "Erro ao executar o comando.");
                }
                return null;
            }
        };

        // Executa o SwingWorker
        worker.execute();
    }

    private static String extrairVideoId(String link) {
        if (link == null || link.isEmpty()) {
            return null; // Retorna null se o link for nulo ou vazio
        }
    
        // Verifica se o link contém "v="
        int indexOfV = link.indexOf("v=");
        if (indexOfV == -1) {
            return null; // Retorna null se "v=" não for encontrado
        }
    
        // Extrai a parte da URL após "v="
        String videoId = link.substring(indexOfV + 2); // +2 para pular "v="
    
        // Remove qualquer parâmetro adicional após o ID do vídeo
        int indexOfAmpersand = videoId.indexOf('&');
        if (indexOfAmpersand != -1) {
            videoId = videoId.substring(0, indexOfAmpersand);
        }
    
        return videoId;
    }

    
    private int calcularAlturaProporcional(ImageIcon imageIcon, int novaLargura) {
        int originalLargura = imageIcon.getIconWidth();
        int originalAltura = imageIcon.getIconHeight();
        double aspectRatio = (double) originalAltura/originalLargura;
        return (int) (novaLargura * aspectRatio);
    }

    private String getVideoTitulo(String videoLink) {
        try {
            URL url = new URL(videoLink);
            BufferedReader leitor = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuilder conteudoPagina = new StringBuilder();
            String linha;
            while((linha = leitor.readLine()) != null){
                conteudoPagina.append(linha);

            }
            leitor.close();

            String html = conteudoPagina.toString();
            Pattern pattern = Pattern.compile("<meta name=\\\"title\\\" content=\\\"(.*?)\\\">");
            Matcher matcher = pattern.matcher(html);
            
            if(matcher.find()){
                String temp = matcher.group();
                return temp.split("content=")[1].replaceAll("&quot;", "").replaceAll(">", "");
            }else{
                return null;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private ImageIcon redimensionarThumb(ImageIcon icon, int novaLargura, int novaAltura) {
        try {
            BufferedImage imagemOriginal = new BufferedImage(
                icon.getIconWidth(),
                icon.getIconHeight(), 
                BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = imagemOriginal.createGraphics();
            icon.paintIcon(null, g2d, 0, 0);
            g2d.dispose();

            BufferedImage resizedImage = Thumbnails.of(imagemOriginal).size(novaLargura, novaAltura).asBufferedImage();
            return new ImageIcon(resizedImage);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
