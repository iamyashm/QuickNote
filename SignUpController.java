package application;

import java.io.IOException;
import java.sql.*;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SignUpController {

    @FXML
    private JFXTextField fnameTf;

    @FXML
    private JFXPasswordField passwordTf;

    @FXML
    private JFXButton createAccBtn;

    @FXML
    private Text invalidLoginMsg;

    @FXML
    private JFXTextField lnameTf;

    @FXML
    private JFXTextField emailTf;

    @FXML
    private JFXTextField usernameTf;
    
    @FXML
    private Text successMsg;

    @FXML
    private Circle btnClose;
    
    @FXML
    void backToLogin(MouseEvent event) throws IOException {
    	Parent home_page_parent  = FXMLLoader.load(getClass().getResource("Login.fxml"));
 	   	Scene newscene = new Scene(home_page_parent);
 	   	Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
 	   	app_stage.setScene(newscene);
 	   	app_stage.show();
    }

    @FXML
    void btnCloseHandler(MouseEvent event) {
    	System.exit(0);
    }

    @FXML
    void createAcc(ActionEvent event) {
    	try {
    		Class.forName("oracle.jdbc.driver.OracleDriver");
    		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","sys as sysdba","yash2000");
    		PreparedStatement stmt = con.prepareStatement("insert into app_user values(?,?,?,?,?)");
    		stmt.setString(1, fnameTf.getText());
    		stmt.setString(2, lnameTf.getText());
    		stmt.setString(3, emailTf.getText());
    		stmt.setString(4, usernameTf.getText());
    		stmt.setString(5, passwordTf.getText());
    		System.out.println(stmt);
    		stmt.execute();	
    		con.close();
    		successMsg.setVisible(true);
    	}
    	catch(Exception e) {
    		successMsg.setText("Invalid details. Try again.");
    		successMsg.setVisible(true);
    		successMsg.setFill(Color.RED);;
    		e.printStackTrace();
    	}
    }

}
