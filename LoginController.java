package application;

import java.io.IOException;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.sql.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LoginController {
	
    @FXML
    private JFXTextField usernameTf;

    @FXML
    private JFXPasswordField passwordTf;

    @FXML
    private JFXButton signInBtn;

    @FXML
    private JFXButton signUpBtn;
    
    @FXML
    private Text invalidLoginMsg;
    
    @FXML
    private Circle btnClose;

    @FXML
    void btnCloseHandler(MouseEvent event) {
    	System.exit(0);
    }

    @FXML
    void signInBtnClick(ActionEvent event) {
    	try {
    		Connection con = new DBConnection().getCon();
    		Statement stmt = con.createStatement();
    		ResultSet rs = stmt.executeQuery("select * from user_info where username='" + usernameTf.getText() + "'");
    		if(rs.next() && rs.getString(5).equals(passwordTf.getText())) {
    			UserSession.login(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
    			invalidLoginMsg.setVisible(false);
    			Parent home_page_parent  = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
    	 	   	Scene newscene = new Scene(home_page_parent);
    	 	   	Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    	 	   	app_stage.setScene(newscene);
    	 	   	app_stage.show();
    			
    		}
    		else
    			invalidLoginMsg.setVisible(true);
    		con.close();
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}

    }

    @FXML
    void signUpBtnClick(ActionEvent event) throws IOException {
    	Parent home_page_parent  = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
 	   	Scene newscene = new Scene(home_page_parent);
 	   	Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
 	   	app_stage.setScene(newscene);
 	   	app_stage.show();
    }

}
