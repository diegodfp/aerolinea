package com.aerolinea.trip.infrastructure.in;

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

import com.aerolinea.trip.domain.entity.Trip;
import com.aerolinea.trip.domain.service.TripService;

public class TripDeleteUi {

    private final TripService tripService;
    private Map<String, Trip> tripMap;

    public TripDeleteUi(TripService tripService) {
        this.tripService = tripService;
        this.tripMap = new HashMap<>();
    }

    public void showTripDeleteUi() {
        JFrame frame = new JFrame("Eliminar Trayecto");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        frame.add(panel);

        // Componentes de la interfaz
        JLabel selectTripLabel = new JLabel("Seleccionar Trayecto:");
        JComboBox<String> tripComboBox = new JComboBox<>();

        // Cargar datos desde la base de datos
        List<Trip> trips = tripService.getAllTrips();
        for (Trip trip : trips) {
            tripComboBox.addItem(String.valueOf(trip.getId()));
            tripMap.put(String.valueOf(trip.getId()), trip);
        }

        JButton deleteButton = new JButton("Eliminar");
        JButton backButton = new JButton("Regresar");

        // Añadir componentes al panel
        panel.add(selectTripLabel);
        panel.add(tripComboBox);
        panel.add(deleteButton);
        panel.add(backButton);

        // Acción del botón de eliminar
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedId = (String) tripComboBox.getSelectedItem();
                if (selectedId != null) {
                    int confirm = JOptionPane.showConfirmDialog(frame, "¿Está seguro de que desea eliminar el trayecto con ID " + selectedId + "?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        tripService.deleteTrip(Integer.parseInt(selectedId));
                        JOptionPane.showMessageDialog(frame, "Trayecto eliminado correctamente");
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
