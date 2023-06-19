package fr.isika.cda25.projet1.vue;

	import fr.isika.cda25.projet1.model.Stagiaire;
import javafx.geometry.Insets;
	import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

	public class VueSup {

	    // Element variables
	    
	    private Label labelNom;
	    private Label labelPrenom;
	    private Label labelCP;
	    private Label labelFormation;
	    private Label labelAnneeRentree;
	    
	    private TextField textFieldNom;
	    private TextField textFieldPrenom;
	    private TextField textFieldCP;
	    private TextField textFieldFormation;
	    private TextField textFieldAnneeRentree;
	    
	    private Button buttonSupprimer;
	    private Button buttonRetour;

	    private Alert alertConfirmation;

	    // Layout variables
	    private VBox root;
	    private GridPane gridPane;

	    // Constructor
	    public VueSup(Stagiaire stagiaire) {
	        // Initialize elements
	        labelNom = new Label("Suppression d'un stagiaire");
	        labelNom.setFont(new Font("Arial", 20));

	        labelNom = new Label("Nom : ");
	        textFieldNom = new TextField();

	        labelPrenom = new Label("Prénom : ");
	        textFieldPrenom = new TextField();

	        labelCP = new Label("Code Postal : ");
	        textFieldCP = new TextField();
	        
	        labelFormation = new Label("Formation : ");
	        textFieldFormation = new TextField();
	        
	        labelAnneeRentree = new Label("Année de rentrée : ");
	        textFieldAnneeRentree = new TextField();
	        
	        buttonSupprimer = new Button("Supprimer le stagiaire");
	        buttonRetour = new Button("Retour");

	        alertConfirmation = new Alert(Alert.AlertType.CONFIRMATION, "Êtes-vous sûr de vouloir supprimer ce stagiaire ?",
	                ButtonType.YES, ButtonType.NO);

	        // Setup layout
	        root = new VBox();
	        root.setSpacing(10);
	        root.setPadding(new Insets(10));
	        root.setAlignment(Pos.CENTER);

	        gridPane = new GridPane();
	        gridPane.setHgap(10);
	        gridPane.setVgap(10);
	        gridPane.setAlignment(Pos.CENTER);

	        // Add elements to grid pane
	        gridPane.add(labelNom, 0, 0);
	        gridPane.add(textFieldNom, 1, 0);
	        gridPane.add(labelPrenom, 0, 1);
	        gridPane.add(textFieldPrenom, 1, 1);
	        
	        gridPane.add(labelCP, 0, 2);
	        gridPane.add(textFieldCP, 1, 2);
	        
	        gridPane.add(labelFormation, 0, 3);
	        gridPane.add(textFieldFormation, 1, 3);

	        gridPane.add(labelAnneeRentree, 0, 4);
	        gridPane.add(textFieldAnneeRentree, 1, 4);
	        
	        // Add grid pane and buttons to root
	        root.getChildren().add(labelNom);
	        root.getChildren().add(gridPane);
	        root.getChildren().add(buttonSupprimer);
	        root.getChildren().add(buttonRetour);
	    }

	    // Getters for elements
	    public TextField getTextFieldNom() {
	        return textFieldNom;
	    }

	    public TextField getTextFieldPrenom() {
	        return textFieldPrenom;
	    }

	    public TextField getTextFieldCP() {
	        return textFieldCP;
	    }
	    public TextField getTextFieldFormation() {
	        return textFieldFormation;
	    }

	    public TextField getTextFieldAnneerentree() {
	        return textFieldAnneeRentree;
	    }
	    
	    public Button getButtonSupprimer() {
	        return buttonSupprimer;
	    }

	    public Button getButtonRetour() {
	        return buttonRetour;
	    }

	    public Alert getAlertConfirmation() {
	        return alertConfirmation;
	    }

	    public VBox getRoot() {
	        return root;
	    }
	}
	

	
