package application;

import java.sql.Connection;
import java.sql.Statement;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class CreateNotebookController {
	
	@FXML
    private AnchorPane mainPane;
	
	@FXML
    private JFXTextField titleTf;

    @FXML
    private JFXButton createBtn;

    @FXML
    private JFXButton backBtn;

    @FXML
    private Text successMsg;

    @FXML
    void backBtnHandler(MouseEvent event) throws java.io.IOException {
    	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("Notebooks.fxml"));
		mainPane.getChildren().setAll(root);
    }

    @FXML
    void createBtnHandler(MouseEvent event) {
    	Connection con = new DBConnection().getCon();
    	try {
    		Statement stmt = con.createStatement();
    		String query = "insert into notebook values('" + UserSession.getCurrentUser().getUsername() + "', seq_notebookID.nextval, '" + titleTf.getText() + "')"; 
    		stmt.execute(query);
      		successMsg.setVisible(true);
    		con.close();
    	}
    	catch(Exception e) {
    		successMsg.setVisible(false);
    		e.printStackTrace();
    	}
    	
    }

}
