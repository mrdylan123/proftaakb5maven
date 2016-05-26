/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.presentation;

import nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.businesslogic.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.datastorage.DBUtils;
import nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.domain.DayPart;
import nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.domain.DayPartEmployee;
import nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.domain.Employee;

/**
 *
 * @author J. Bouman
 */
public class WeergaveUI {
    private Button backBtn, logoutBtn, requestBtn;
    private TextArea barTA, keukenTA;
    private TextField dayTF, monthTF, yearTF;
    private PresentationManager presentationManager;
    
    public WeergaveUI() {   
        presentationManager = new PresentationManager();
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
        primaryStage.setTitle("Planning weergave");
        primaryStage.show();
    }
    
    public Scene createScene() {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new javafx.geometry.Insets(5, 5, 5, 5));
        grid.autosize();
       
        Scene  scene  =  new  Scene(grid);
        
        // De knoppen op het paneel
        backBtn = new Button ("<--");
        logoutBtn = new Button ("Uitloggen");
        barTA= new TextArea ("De weergave van bar");
        keukenTA = new TextArea ("De weergave van keuken.");
        requestBtn = new Button ("Vraag op");
        
        dayTF = new TextField("dag");
        monthTF = new TextField("maand");
        yearTF = new TextField("jaar");
        
        final Text barText = new Text(25, 25, "BAR");
        barText.setFill(Color.CHOCOLATE);
        barText.setFont(Font.font(java.awt.Font.SERIF, 30));
        barText.setTextAlignment(TextAlignment.CENTER);
        
        final Text kitchenText = new Text(25, 25, "KEUKEN");
        kitchenText.setFill(Color.CHOCOLATE);
        kitchenText.setFont(Font.font(java.awt.Font.SERIF, 30));        
        
        grid.add(backBtn,1,1);
        grid.add(dayTF,2,1);
        grid.add(monthTF,3,1);
        grid.add(yearTF,4,1);
        grid.add(logoutBtn,5,1);
        grid.add(barTA,2,4);
        grid.add(keukenTA,4,4);
        grid.add(requestBtn,3,5);
        grid.add(barText,2,3);
        grid.add(kitchenText,4,3);
        
        
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
    backBtn.setOnAction(new EventHandler<ActionEvent>() {
 
    @Override
    public void handle(ActionEvent e) {
        
    }
});
            
    logoutBtn.setOnAction(new EventHandler<ActionEvent>() {
 
    @Override
    public void handle(ActionEvent e) {
     barTA.setText("Gelukt!");
    }
});
                        
    requestBtn.setOnAction(new EventHandler<ActionEvent>() {
 
    @Override
    public void handle(ActionEvent e) {

    }
});        
    
    requestBtn.setOnAction(new EventHandler<ActionEvent>() {
 
    @Override
    public void handle(ActionEvent e) {
        
     
     String dayStr = dayTF.getText();
     String monthStr = monthTF.getText();
     String yearStr = yearTF.getText();
     
     try
     {
         DayPart[] dpArr = presentationManager.OnRequestButtonPress(
                 dayStr, monthStr, yearStr);

         String s = "";

         for (DayPart dp : dpArr)
         {
             s += getFormattedOutputForDayPart(dp);
         }

         barTA.setText(s);
         keukenTA.setText(s);
     }
     catch(DateInvalidException die)
     {
         PresentationUtils.showJavaFXAlert("Entered date is invalid");
     }
    }
        });
        return (scene);
        
        
    }
    
    public String getFormattedOutputForDayPart(DayPart dp)
    {
        String s = DBUtils.toSQLString(dp.getDate());
        s += "\t\t\t";
        s += dp.getDayPartType().toString();
        s += "\n----------------------------------------\n";
        
        boolean found = false;
        
        for (DayPartEmployee dpe : dp.getDpeList())
        {
            found = true;
            
            Employee e = dpe.getEmployee();
            
            s += e.getName();
            s += "\t(";
            s += dpe.getPresenceStatus().toString();
            s += ")\n";
        }
        
        if (found == false)
        {
            s += "<NO RESULTS>\n";
        }
        
        s += "\n\n";
        return s;
    }
}

