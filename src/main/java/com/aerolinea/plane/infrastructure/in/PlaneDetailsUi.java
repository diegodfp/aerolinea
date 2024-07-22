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
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.aerolinea.airline.domain.entity.Airline;
import com.aerolinea.airline.domain.service.AirlineService;
import com.aerolinea.model.domain.entity.Model;
import com.aerolinea.model.domain.service.ModelService;
import com.aerolinea.plane.domain.entity.Plane;
import com.aerolinea.plane.domain.service.PlaneService;
import com.aerolinea.status.domain.entity.Status;
import com.aerolinea.status.domain.service.StatusService;
import com.toedter.calendar.JDateChooser;

public class PlaneDetailsUi {

    private final PlaneService planeService;
    private final AirlineService airlineService;
    private final ModelService modelService;
    private final StatusService statusService;

    private Map<String, Integer> airlineMap;
    private Map<String, Integer> modelMap;
    private Map<String, Integer> statusMap;
    private Map<String, Plane> planeMap;

    public PlaneDetailsUi(PlaneService planeService, AirlineService airlineService, ModelService modelService,
            StatusService statusService) {
        this.planeService = planeService;
        this.airlineService = airlineService;
        this.modelService = modelService;
        this.statusService = statusService;

        this.airlineMap = new HashMap<>();
        this.modelMap = new HashMap<>();
        this.statusMap = new HashMap<>();
        this.planeMap = new HashMap<>();
    }

    public void showPlaneDetailsUi() {
        JFrame frame = new JFrame("Detalles del Avión");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(10, 2, 10, 10));
        frame.add(panel);

        // Componentes de la interfaz
        JLabel selectPlaneLabel = new JLabel("Seleccionar Avión:");
        JComboBox<String> planeComboBox = new JComboBox<>();

        JLabel platesLabel = new JLabel("Placas:");
        JTextField platesField = new JTextField();
        platesField.setEditable(false);

        JLabel capacityLabel = new JLabel("Capacidad:");
        JTextField capacityField = new JTextField();
        capacityField.setEditable(false);

        JLabel fabricationDateLabel = new JLabel("Fecha de Fabricación:");
        JDateChooser fabricationDateChooser = new JDateChooser();
        fabricationDateChooser.setDateFormatString("yyyy-MM-dd");
        fabricationDateChooser.setEnabled(false);

        JLabel statusLabel = new JLabel("Estatus:");
        JTextField statusField = new JTextField();
        statusField.setEditable(false);

        JLabel modelLabel = new JLabel("Modelo:");
        JTextField modelField = new JTextField();
        modelField.setEditable(false);

        JLabel airlineLabel = new JLabel("Aerolínea:");
        JTextField airlineField = new JTextField();
        airlineField.setEditable(false);

        JButton backButton = new JButton("Regresar");

        // Cargar datos desde la base de datos
        List<Airline> airlines = airlineService.getAllAirlines();
        for (Airline airline : airlines) {
            airlineMap.put(airline.getName(), airline.getId());
        }

        List<Model> models = modelService.getAllModels();
        for (Model model : models) {
            modelMap.put(model.getName(), model.getId());
        }

        List<Status> statuses = statusService.getAllStatuses();
        for (Status status : statuses) {
            statusMap.put(status.getName(), status.getId());
        }

        List<Plane> planes = planeService.getAllPlanes();
        for (Plane plane : planes) {
            planeComboBox.addItem(plane.getPlates());
            planeMap.put(plane.getPlates(), plane);
        }

        // Añadir componentes al panel
        panel.add(selectPlaneLabel);
        panel.add(planeComboBox);

        panel.add(platesLabel);
        panel.add(platesField);

        panel.add(capacityLabel);
        panel.add(capacityField);

        panel.add(fabricationDateLabel);
        panel.add(fabricationDateChooser);

        panel.add(statusLabel);
        panel.add(statusField);

        panel.add(modelLabel);
        panel.add(modelField);

        panel.add(airlineLabel);
        panel.add(airlineField);

        panel.add(backButton);

        // Acción al seleccionar un avión
        planeComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedPlates = (String) planeComboBox.getSelectedItem();
                Plane selectedPlane = planeMap.get(selectedPlates);

                if (selectedPlane != null) {
                    platesField.setText(selectedPlane.getPlates());
                    capacityField.setText(String.valueOf(selectedPlane.getCapacity()));
                    fabricationDateChooser.setDate(selectedPlane.getFabricationDate());
                    statusField.setText(getKeyByValue(statusMap, selectedPlane.getIdStatus()));
                    modelField.setText(getKeyByValue(modelMap, selectedPlane.getIdModel()));
                    airlineField.setText(getKeyByValue(airlineMap, selectedPlane.getIdAerolinea()));
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

    private <T, E> String getKeyByValue(Map<String, T> map, E value) {
        for (Map.Entry<String, T> entry : map.entrySet()) {
            if (entry.getValue().equals(value)) {
                return entry.getKey();
            }
        }
        return null;
    }
}
