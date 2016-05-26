/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.presentation;

import nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.businesslogic.*;
import nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.domain.*;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.JFXPanel;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import static javafx.scene.paint.Color.BLUE;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.event.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.*;
import javafx.stage.Stage;

/**
 *
 * @author J. Bouman
 */
public class InvoerUI {
    private Button backButton, logOutButton, confirmButton, checkButton;
    private ComboBox worker1CB, worker2CB, worker3CB, dayPartCB;
    private Label workerLabel, dateLabel, dayPartLabel;
    private TextField dayTF, monthTF, yearTF;
    private InputManager inputManager;
    
    public InvoerUI() {
        inputManager = new InputManager();
    }
    
    
    public void initFX(JFXPanel fxPanel) {
        // This method is invoked on the JavaFX thread
        Scene scene = createScene();
        fxPanel.setScene(scene);
        Start(new Stage());
    }

    public void Start(Stage primaryStage)
    {
        Scene scene = createScene();
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.setTitle("Planning invoeren");
        primaryStage.show();
    }
    
    public Scene createScene() {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new javafx.geometry.Insets(5, 5, 5, 5));
        grid.autosize();
        
        Scene  scene  =  new  Scene(grid);
        
        
        backButton = new Button("<--");
        logOutButton = new Button("Uitloggen");
        confirmButton = new Button("Invoer bevestigen");
        checkButton = new Button("Check mogelijkheid");

        worker1CB = new ComboBox();
        worker2CB = new ComboBox();
        worker3CB = new ComboBox();
        
        
        worker1CB.getItems().add("");
        worker2CB.getItems().add("");
        worker3CB.getItems().add("");
        
        for (Employee e : inputManager.getEmployees() )
        {
            worker1CB.getItems().add(e.getName());
            worker2CB.getItems().add(e.getName());
            worker3CB.getItems().add(e.getName());
        }
        
        dayPartCB = new ComboBox();
        
        dayPartCB.getItems().addAll("Ochtend", 
                                    "Middag",
                                    "Avond");

        dayPartCB.setValue("Ochtend");
        
        workerLabel = new Label("Medewerker");
        dateLabel = new Label("Datum");
        dayPartLabel = new Label("Dagdeel");

        dayTF = new TextField("Dag");
        // Add a listener to the textfield, so that when the textfield is clicked, it removes the placeholder text.
        dayTF.focusedProperty().addListener(new ChangeListener<Boolean>()
        {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
            {
                if (newPropertyValue)
                {
                    dayTF.setText("");
                }
            }
        }
        );
                
        monthTF = new TextField("Maand");
        // Add a listener to the textfield, so that when the textfield is clicked, it removes the placeholder text.
        monthTF.focusedProperty().addListener(new ChangeListener<Boolean>()
        {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
            {
                if (newPropertyValue)
                {
                    monthTF.setText("");
                }
            }
        }
        );
                  
        yearTF = new TextField("Jaar");
        // Add a listener to the textfield, so that when the textfield is clicked, it removes the placeholder text.
        yearTF.focusedProperty().addListener(new ChangeListener<Boolean>()
        {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
            {
                if (newPropertyValue)
                {
                    yearTF.setText("");
                }
            }
        }
        );

        grid.add(backButton,1,1);
        grid.add(logOutButton,4,1);

        grid.add(workerLabel,1,2);
        grid.add(worker1CB,2,2);
        grid.add(worker2CB,3,2);
        grid.add(worker3CB,4,2);

        grid.add(dateLabel,1,3);
        grid.add(dayTF,2,3);
        grid.add(monthTF,3,3);
        grid.add(yearTF,4,3);

        grid.add(dayPartLabel,1,4);
        grid.add(dayPartCB,2,4);

        grid.add(checkButton,1,5);
        grid.add(confirmButton,4,5);
        
    backButton.setOnAction(new EventHandler<ActionEvent>() {
 
    @Override
    public void handle(ActionEvent e) {

    }
});
            
    logOutButton.setOnAction(new EventHandler<ActionEvent>() {
 
    @Override
    public void handle(ActionEvent e) {

    }
});
                        
    logOutButton.setOnAction(new EventHandler<ActionEvent>() {
 
    @Override
    public void handle(ActionEvent e) {

    }
});
    
    checkButton.setOnAction(new EventHandler<ActionEvent>() {
 
    @Override
    public void handle(ActionEvent e) {

    }
});
    
    confirmButton.setOnAction(new EventHandler<ActionEvent>() {
 
    @Override
    public void handle(ActionEvent e) {
        
     int CB1SelectedIndex = worker1CB.getSelectionModel().getSelectedIndex();
     int CB2SelectedIndex = worker2CB.getSelectionModel().getSelectedIndex();
     int CB3SelectedIndex = worker3CB.getSelectionModel().getSelectedIndex();
     int dayPartCBSelectedIndex = dayPartCB.getSelectionModel()
             .getSelectedIndex();
     
     if (CB1SelectedIndex < 1 && CB2SelectedIndex < 1 
                && CB3SelectedIndex < 1)
     {
         return;
     }
     
     String dayStr = dayTF.getText();
     String monthStr = monthTF.getText();
     String yearStr = yearTF.getText();
     
    Employee e1 = null, e2 = null, e3 = null;
        
    if (CB1SelectedIndex > 0) e1 = inputManager.getEmployees()
                                    .get(CB1SelectedIndex - 1);
    if (CB2SelectedIndex > 0) e2 = inputManager.getEmployees()
                                    .get(CB2SelectedIndex - 1);
    if (CB3SelectedIndex > 0) e3 = inputManager.getEmployees()
                                    .get(CB3SelectedIndex - 1);
     
     try
     {
         inputManager.OnConfirmButtonPress(e1, e2, e3, dayPartCBSelectedIndex, 
                                                dayStr, monthStr, yearStr);

         PresentationUtils.showJavaFXAlert("Successfully entered input.");
     }
     catch(DateInvalidException die)
     {
         PresentationUtils.showJavaFXAlert("Entered date is invalid.");
     }
    }
        });
        
        return (scene);
    }
}


