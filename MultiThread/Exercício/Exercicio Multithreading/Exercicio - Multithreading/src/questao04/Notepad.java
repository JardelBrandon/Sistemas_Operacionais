package questao04;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Notepad extends JFrame implements ActionListener, Runnable {

    private static final long serialVersionUID = 1L;
    private static final long UM_MINUTO = 60000;
    private TextArea textArea = new TextArea("", 0, 0, TextArea.SCROLLBARS_VERTICAL_ONLY);
    private MenuBar menuBar = new MenuBar();
    private Menu file = new Menu();
    private MenuItem openFile = new MenuItem();
    private MenuItem saveFile = new MenuItem();
    private File arquivo = null;
    private File arquivoAnterior = null;

    public Notepad() {
        this.setSize(500, 300);
        this.setTitle("Sem titulo - Bloco de notas");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.textArea.setFont(new Font("Century Gothic", Font.BOLD, 12));
        this.textArea.addTextListener(new TextListener() {
            public void textValueChanged(TextEvent e) {
                String title = Notepad.this.getTitle();
                if (!title.startsWith("*")) {
                    Notepad.this.setTitle("*" + title);
                }
            }
        });
        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(textArea);
        this.setMenuBar(this.menuBar);
        this.menuBar.add(this.file);
        this.file.setLabel("File");
        this.openFile.setLabel("Open");
        this.openFile.addActionListener(this);
        this.openFile.setShortcut(new MenuShortcut(KeyEvent.VK_O, false));
        this.file.add(this.openFile);
        this.saveFile.setLabel("Save");
        this.saveFile.addActionListener(this);
        this.saveFile.setShortcut(new MenuShortcut(KeyEvent.VK_S, false));
        this.file.add(this.saveFile);

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.openFile) {
            JFileChooser open = new JFileChooser();
            int option = open.showOpenDialog(this);
            if (option == JFileChooser.APPROVE_OPTION) {
                this.textArea.setText("");
                try {
                    arquivo = open.getSelectedFile();
                    Scanner scan = new Scanner(new FileReader(arquivo.getPath()));
                    while (scan.hasNext()) {
                        this.textArea.append(scan.nextLine() + "\n");
                    }
                    scan.close();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } else if (e.getSource() == this.saveFile) {
            JFileChooser save = new JFileChooser();
            int option = save.showSaveDialog(this);
            if (option == JFileChooser.APPROVE_OPTION) {
                arquivo = save.getSelectedFile();
                salvarArquivo();
            }
        }
    }

    private void salvarArquivo() {
        try {
            setTitle(arquivo.getName() + " - Bloco de notas");
            BufferedWriter out = new BufferedWriter(new FileWriter(arquivo.getPath()));
            out.write(this.textArea.getText());
            out.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void main(String args[]) {
        Notepad app = new Notepad();
        app.setVisible(true);
        Thread salvarAutomaticamente = new Thread(app, "Salvar");
        salvarAutomaticamente.start();
    }

    @Override
    public void run() {
        while (true) {
            if (this.getArquivoAlterado()) {
                System.out.println("Arquivo alterado:" + this.arquivo);
                this.arquivoAnterior = this.arquivo;
            }
            if (!this.getArquivoEstaNulo()) {
                esperarTempo(UM_MINUTO);
                salvarArquivo();
                System.out.println("Arquivo salvo!");
            }
            //esperarTempo(0);
        }
    }
    
    private boolean getArquivoAlterado() {
        return (this.arquivo != this.arquivoAnterior);
    }

    private boolean getArquivoEstaNulo() {
        return (this.arquivo == null);
    }

    private void esperarTempo(long tempo) {
        try {
            Thread.sleep(tempo);
        } catch (InterruptedException ex) {
            Logger.getLogger(Notepad.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
