package com.aerolinea.plane.infrastructure.in;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.aerolinea.plane.domain.entity.Plane;
import com.aerolinea.plane.domain.service.PlaneService;

public class PlaneDeleteUi {

    private final PlaneService planeService;
    private Map<String, Plane> planeMap;

    public PlaneDeleteUi(PlaneService planeService) {
        this.planeService = planeService;
        this.planeMap = new HashMap<>();
    }

    public void showPlaneDeleteUi() {
        JFrame frame = new JFrame("Eliminar Avión");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        frame.add(panel);

        // Componentes de la interfaz
        JLabel selectPlaneLabel = new JLabel("Seleccionar Avión:");
        JComboBox<String> planeComboBox = new JComboBox<>();

        // Cargar datos desde la base de datos
        List<Plane> planes = planeService.getAllPlanes();
        for (Plane plane : planes) {
            planeComboBox.addItem(plane.getPlates());
            planeMap.put(plane.getPlates(), plane);
        }

        JButton deleteButton = new JButton("Eliminar");
        JButton backButton = new JButton("Regresar");

        // Añadir componentes al panel
        panel.add(selectPlaneLabel);
        panel.add(planeComboBox);
        panel.add(deleteButton);
        panel.add(backButton);

        // Acción del botón de eliminar
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedPlates = (String) planeComboBox.getSelectedItem();
                if (selectedPlates != null) {
                    int confirm = JOptionPane.showConfirmDialog(frame, "¿Está seguro de que desea eliminar el avión con placa " + selectedPlates + "?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        planeService.deletePlane(selectedPlates);
                        JOptionPane.showMessageDialog(frame, "Avión eliminado correctamente");
                        frame.dispose();
                    }
                }
            }
        });

        // Acción del botón de regreso
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        frame.setVisible(true);
    }
}
