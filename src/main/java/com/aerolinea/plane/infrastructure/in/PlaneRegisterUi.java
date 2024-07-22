package com.aerolinea.plane.infrastructure.in;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.aerolinea.airline.domain.entity.Airline;
import com.aerolinea.airline.domain.service.AirlineService;
import com.aerolinea.model.domain.entity.Model;
import com.aerolinea.model.domain.service.ModelService;
import com.aerolinea.status.domain.entity.Status;
import com.aerolinea.status.domain.service.StatusService;
import com.aerolinea.plane.domain.entity.Plane;
import com.aerolinea.plane.domain.service.PlaneService;
import com.toedter.calendar.JDateChooser;

public class PlaneRegisterUi {
    private final PlaneService planeService;
    private final AirlineService airlineService;
    private final ModelService modelService;
    private final StatusService statusService;

    private Map<String, Integer> airlineMap;
    private Map<String, Integer> modelMap;
    private Map<String, Integer> statusMap;

    public PlaneRegisterUi(PlaneService planeService, AirlineService airlineService, ModelService modelService,
                           StatusService statusService) {
        this.planeService = planeService;
        this.airlineService = airlineService;
        this.modelService = modelService;
        this.statusService = statusService;

        this.airlineMap = new HashMap<>();
        this.modelMap = new HashMap<>();
        this.statusMap = new HashMap<>();
    }

    public void showPlaneRegisterUi() {
        JFrame frame = new JFrame("Registrar Avión");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(8, 2, 10, 10));
        frame.add(panel);

        // Componentes de la interfaz
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

        JButton registerButton = new JButton("Registrar");

        JButton backButton = new JButton("Regresar");
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

        // Añadir componentes al panel
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

        panel.add(registerButton);
        panel.add(backButton);
        // Acción del botón registrar
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // VALIDACION DE LA PLACA // 
                String plates = platesField.getText();
                if (planeService.isPlanePlatesExists(plates)) {
                    JOptionPane.showMessageDialog(frame, "La placa ya está registrada.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                //
                int capacity = Integer.parseInt(capacityField.getText());
                java.util.Date fabricationDate = fabricationDateChooser.getDate();
                java.sql.Date sqlFabricationDate = new java.sql.Date(fabricationDate.getTime());
                int idStatus = statusMap.get((String) statusComboBox.getSelectedItem());
                int idModel = modelMap.get((String) modelComboBox.getSelectedItem());
                int idAirline = airlineMap.get((String) airlineComboBox.getSelectedItem());

                Plane plane = new Plane();
                plane.setPlates(plates);
                plane.setCapacity(capacity);
                plane.setFabricationDate(sqlFabricationDate);
                plane.setIdStatus(idStatus);
                plane.setIdModel(idModel);
                plane.setIdAerolinea(idAirline);

                planeService.createPlane(plane);

                JOptionPane.showMessageDialog(frame, "Avión registrado exitosamente");
                frame.dispose();
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
