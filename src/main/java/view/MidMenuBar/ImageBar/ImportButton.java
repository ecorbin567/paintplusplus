package view.MidMenuBar.ImageBar;

import interface_adapter.image.import_image.ImportController;
import view.MidMenuBar.SelectButton;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class ImportButton {

    private final JButton button;
    private final ImportController controller;

    public ImportButton(ImportController controller) {
        this.controller = controller;
        button = new JButton();

        ImageIcon icon = new ImageIcon(SelectButton.class.getResource("/images/ImageIcon.png"));
        Image image = icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        button.setIcon(new ImageIcon(image));
        button.setPreferredSize(new Dimension(60, 60));

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Import PNG or PDF");

                FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG & PDF Files", "png", "pdf");
                fileChooser.setFileFilter(filter);

                int result = fileChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    controller.execute(selectedFile);
                }
            }
        });
    }

    public JButton getButton() {
        return button;
    }
}
