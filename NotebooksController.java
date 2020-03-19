package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class NotebooksController implements Initializable {
	
	ObservableList<NotebookModel> ob = FXCollections.observableArrayList();
	
	@FXML
	private AnchorPane mainPane;
	
	@FXML
    private JFXButton createNb;

    @FXML
    private JFXButton deleteNb;

    @FXML
    private JFXButton openNb;

	
	@FXML
    private TableView<NotebookModel> notebookTable;

    @FXML
    private TableColumn<NotebookModel, String> titleColumn;
    

    @FXML
    void createNbHandler(MouseEvent event) throws java.io.IOException {
    	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("CreateNotebook.fxml"));
		mainPane.getChildren().setAll(root);
    }

    @FXML
    void deleteNbHandler(MouseEvent event) {
    	NotebookModel person = notebookTable.getSelectionModel().getSelectedItem();
    	try {
	    	Connection con = new DBConnection().getCon();
			String query = "delete from notebook where title='" + person.getNbName() + "' and username='" + UserSession.getCurrentUser().getUsername() + "'";
			Statement st = con.createStatement();
			st.executeUpdate(query);
			notebookTable.getItems().remove(person);
			con.close();
    	} catch(Exception e) {
    		System.out.println(e);
    	}
    }

    @FXML
    void openNbHandler(MouseEvent event) {
    	NotebookModel person = notebookTable.getSelectionModel().getSelectedItem();
    	System.out.println(person.getNbName()); 
    }

	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			Connection con = new DBConnection().getCon();
			String query = "select title from notebook where username='" + UserSession.getCurrentUser().getUsername() + "'";
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			while(rs.next()) {
				ob.add(new NotebookModel(rs.getString(1)));
			}
			titleColumn.setCellValueFactory(new PropertyValueFactory<>("nbName"));
			notebookTable.setItems(ob);
			
			con.close();
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	
	}
}
