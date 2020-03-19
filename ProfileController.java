package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class ProfileController implements Initializable {

    @FXML
    private Text nameText;

    @FXML
    private Text usernameText;

    @FXML
    private Text emailText;

    @FXML
    private JFXButton changePassBtn;

    @FXML
    private JFXPasswordField oldPassTf;

    @FXML
    private JFXPasswordField newPassTf;

    @FXML
    private JFXButton confirmBtn;

    @FXML
    private Text successMsg;

    @FXML
    void changePass(MouseEvent event) {
    	oldPassTf.setVisible(true);
    	newPassTf.setVisible(true);
    	confirmBtn.setVisible(true);
    }

    @FXML
    void confirmChangePassword(MouseEvent event) {
    	try {
    		Connection con = new DBConnection().getCon();
    		String query1 = "select password from user_info where username='" + UserSession.getCurrentUser().getUsername() + "'";
    		Statement stmt = con.createStatement();
    		ResultSet rs = stmt.executeQuery(query1);
    		rs.next();
    		if(rs.getString(1).equals(oldPassTf.getText())) {
    			if(newPassTf.getText().trim().length() > 0) {
    				String query2 = "update user_info set password='" + newPassTf.getText() + "' where username='" + UserSession.getCurrentUser().getUsername() + "'";
    				stmt.execute(query2);
    				successMsg.setText("Password changed successfully");
    				successMsg.setFill(Color.web("1ff551"));
    			}
    			else {
    				successMsg.setText("New password cannot be empty.");
        			successMsg.setFill(Color.RED);
    			}		
    		}
    		else {
    			successMsg.setText("Old password is incorrect.");
    			successMsg.setFill(Color.RED);
    		}
    		successMsg.setVisible(true);	
    		con.close();
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		UserSession curruser = UserSession.getCurrentUser();
		nameText.setText(curruser.getFname() + " " + curruser.getLname());
		emailText.setText(curruser.getEmail());
		usernameText.setText(curruser.getUsername());
	}
    
    

}
