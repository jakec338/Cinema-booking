package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class FilmsController implements Initializable {

    
    @FXML
    private Label label;
    
    @FXML
    private Button addFilmBtn;

    @FXML
    public ListView filmListView;

    protected List<String> filmList = new ArrayList<>();

    protected ListProperty<String> listProperty = new SimpleListProperty<>();

    
	@FXML
	public void handleAddFilm(ActionEvent event) throws IOException{
    	Stage stage; 
	    Parent root;
	    stage=(Stage) addFilmBtn.getScene().getWindow();
	    root = FXMLLoader.load(getClass().getResource("addFilm.fxml"));
	    Scene scene = new Scene(root, 400, 400);
	    stage.setScene(scene);
	    stage.show();
	    
	}

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        filmList.add("A Film");
        filmList.add("Another Film");
        filmList.add("Another Film");
        filmList.add("KRW");
        filmList.add("SGD");


        filmListView.itemsProperty().bind(listProperty);

        //This does not work, you can not directly add to a ListProperty
        //listProperty.addAll( asianCurrencyList );
        listProperty.set(FXCollections.observableArrayList(filmList));
    }
    
    
    
    @FXML public void handleMouseClick(MouseEvent arg0) throws IOException {
    	
        String selectedFilm = (String) filmListView.getSelectionModel().getSelectedItem();
        for (int i = 0; i < filmList.size(); i++){
        	String film = new String("");
        }
       	
        switch (selectedFilm){
        case filmList.get(0):
        	newPage("Login.fxml");
        }
        
        if (selectedFilm == filmList.get(0)){
        	newPage("Login.fxml");
        }
    }
    
//    @FXML public void handleMouseClick(MouseEvent arg0) throws IOException {
//    	Stage stage; 
//	    Parent root;
//	    
//	    
//	    
//	    if (arg0.getSource() == filmList.get(0)){
//	    	stage=(Stage) filmListView.getScene().getWindow();
//		    root = FXMLLoader.load(getClass().getResource("Login.fxml"));      // ??????????????????????
//		    Scene scene = new Scene(root, 400, 400);
//		    stage.setScene(scene);
//		    stage.show();
//	    } else if (arg0.getSource() == filmList.get(1)){
//	    	stage=(Stage) filmListView.getScene().getWindow();
//		    root = FXMLLoader.load(getClass().getResource("Main.fxml"));
//		    Scene scene = new Scene(root, 400, 400);
//		    stage.setScene(scene);
//		    stage.show();
//	    }
//    }
    
    
    public void newPage(String fxmlFile) throws IOException{
    	Stage stage;
    	Parent root;
    	stage=(Stage) filmListView.getScene().getWindow();
	    root = FXMLLoader.load(getClass().getResource(fxmlFile));     
	    Scene scene = new Scene(root, 400, 400);
	    stage.setScene(scene);
	    stage.show();
    }
}
