package application;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class DashboardController implements Initializable {
	
	@FXML
	private Circle btnClose;
	
	@FXML
	private Text welcomeMsg;
	 
	@FXML
    private JFXButton navNotebook;

    @FXML
    private JFXButton navReminder;

    @FXML
    private JFXButton navShared;

    @FXML
    private JFXButton navProfile;

    @FXML
    private JFXButton navLogout;

    @FXML
    private AnchorPane mainPane;

    @FXML
    void btnCloseHandler(MouseEvent event) {
    	System.exit(0);
    }	
    
    @FXML
    void navEventHandler(MouseEvent event) {
    	resetNavColors();
    	JFXButton btn = (JFXButton)event.getSource();
    	String filename = "";
    	switch(btn.getId()) {
    		case "navNotebook": filename = "Notebooks.fxml"; navNotebook.setStyle("-fx-background-color: #0f5fd0");
    			break;
    		case "navReminder": navReminder.setStyle("-fx-background-color: #0f5fd0");
    			break;
    		case "navShared":  navShared.setStyle("-fx-background-color: #0f5fd0");
    			break;
    		case "navProfile":  filename = "Profile.fxml"; navProfile.setStyle("-fx-background-color: #0f5fd0");
    			break;
    		case "navLogout": filename = "Login.fxml"; navLogout.setStyle("-fx-background-color: #0f5fd0");
    			break;
    	}
		try {
			if(!filename.equals("Login.fxml")) {
				AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource(filename));
				mainPane.getChildren().setAll(root);
			}
			else {
				BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("Login.fxml"));
				Scene scene = new Scene(root);
				Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				app_stage.setScene(scene);
				app_stage.show();
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}	
		
    }
    
    
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1){
		welcomeMsg.setText("Hi, " + UserSession.getCurrentUser().getFname() + " " + UserSession.getCurrentUser().getLname() + ".");
		Event.fireEvent(navNotebook, new MouseEvent(MouseEvent.MOUSE_CLICKED,
				  0, 0, 0, 0, MouseButton.PRIMARY, 1,
				   true, true, true, true, true, true, true, true, true, true, null));
	}
		
	public void resetNavColors() {
		
		Scene scene = navNotebook.getScene(); if(scene != null)	
		scene.getStylesheets().clear();
		 
		navNotebook.setStyle(null);
		navReminder.setStyle(null);
		navShared.setStyle(null);
		navProfile.setStyle(null);
		navLogout.setStyle(null);
		
		if(scene != null)
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		

	}	
}
