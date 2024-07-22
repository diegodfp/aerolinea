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

public class PlaneUpdateUi {

    private final PlaneService planeService;
    private final AirlineService airlineService;
    private final ModelService modelService;
    private final StatusService statusService;

    private Map<String, Integer> airlineMap;
    private Map<String, Integer> modelMap;
    private Map<String, Integer> statusMap;
    private Map<String, Plane> planeMap;    

    private String originalPlates;

    public PlaneUpdateUi(PlaneService planeService, AirlineService airlineService, ModelService modelService,
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



    public void showPlaneUpdateUi() {
        JFrame frame = new JFrame("Actualizar Avión");
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

        JLabel capacityLabel = new JLabel("Capacidad:");
        JTextField capacityField = new JTextField();

        JLabel fabricationDateLabel = new JLabel("Fecha de Fabricación:");
        JDateChooser fabricationDateChooser = new JDateChooser();
        fabricationDateChooser.setDateFormatString("yyyy-MM-dd");

        JLabel statusLabel = new JLabel("Estatus:");
        JComboBox<String> statusComboBox = new JComboBox<>();

        JLabel modelLabel = new JLabel("Modelo:");
        JComboBox<String> modelComboBox = new JComboBox<>();

        JLabel airlineLabel = new JLabel("Aerolínea:");
        JComboBox<String> airlineComboBox = new JComboBox<>();

        // Cargar datos desde la base de datos
        
        List<Airline> airlines = airlineService.getAllAirlines();
        for (Airline airline : airlines) {
            airlineComboBox.addItem(airline.getName());
            airlineMap.put(airline.getName(), airline.getId());
        }

        List<Model> models = modelService.getAllModels();
        for (Model model : models) {
            modelComboBox.addItem(model.getName());
            modelMap.put(model.getName(), model.getId());
        }

        List<Status> statuses = statusService.getAllStatuses();
        for (Status status : statuses) {
            statusComboBox.addItem(status.getName());
            statusMap.put(status.getName(), status.getId());
        }

        List<Plane> planes = planeService.getAllPlanes();
        for (Plane plane : planes) {
            planeComboBox.addItem(plane.getPlates());
            planeMap.put(plane.getPlates(), plane);
        }

        JButton updateButton = new JButton("Actualizar");
        JButton backButton = new JButton("Regresar");

        
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
        panel.add(statusComboBox);

        panel.add(modelLabel);
        panel.add(modelComboBox);

        panel.add(airlineLabel);
        panel.add(airlineComboBox);

        panel.add(updateButton);
        panel.add(backButton);
        // Acción al seleccionar un avión
        planeComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedPlates = (String) planeComboBox.getSelectedItem();
                Plane selectedPlane = planeMap.get(selectedPlates);

                if (selectedPlane != null) {
                    originalPlates = selectedPlane.getPlates();
                    platesField.setText(selectedPlane.getPlates());
                    capacityField.setText(String.valueOf(selectedPlane.getCapacity()));
                    fabricationDateChooser.setDate(selectedPlane.getFabricationDate());
                    statusComboBox.setSelectedItem(getKeyByValue(statusMap, selectedPlane.getIdStatus()));
                    modelComboBox.setSelectedItem(getKeyByValue(modelMap, selectedPlane.getIdModel()));
                    airlineComboBox.setSelectedItem(getKeyByValue(airlineMap, selectedPlane.getIdAerolinea()));
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

        // Acción del botón updateButton
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String plates = platesField.getText();
                int capacity = Integer.parseInt(capacityField.getText());
                java.util.Date fabricationDate = fabricationDateChooser.getDate();
                java.sql.Date sqlFabricationDate = new java.sql.Date(fabricationDate.getTime());
                int idStatus = statusMap.get((String) statusComboBox.getSelectedItem());
                int idModel = modelMap.get((String) modelComboBox.getSelectedItem());
                int idAerolinea = airlineMap.get((String) airlineComboBox.getSelectedItem());

                Plane plane = new Plane();
                plane.setPlates(plates);
                plane.setCapacity(capacity);
                plane.setFabricationDate(sqlFabricationDate);
                plane.setIdStatus(idStatus);
                plane.setIdModel(idModel);
                plane.setIdAerolinea(idAerolinea);

                planeService.updatePlane(plane, originalPlates);

                JOptionPane.showMessageDialog(frame, "Avión actualizado correctamente");
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
