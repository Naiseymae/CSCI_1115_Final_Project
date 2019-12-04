
/* 
 * Author: Renee Linford
 * Date: 12-4-19
 * ADS Final Project: RNA translation displayed in GUI using Map & Textarea.
 * 
 */

import java.util.*;
import javafx.application.*;
import javafx.stage.*;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.image.*;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.collections.*;
import javafx.scene.control.Button;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.text.*;
import javafx.event.*;


public class Final_project extends Application {
	/* User inputs RNA sequence into GUI.   
	For each iteration of the amino acid string, user chooses which amino acids to alter  
	Amino acid strings visually displayed.*/

	public static void main(String[] args) {
		Application.launch(args);
	}

	/* Start Method */
	@Override // Override the start method in the Application Class.
	public void start(Stage primaryStage) {


		// Create codon linkedList.
		LinkedList<String> codonList = new LinkedList<String>();
		int generationCount = 0;

		/* Create main pane. */
		BorderPane pane = new BorderPane();

		// Create buttons.		
		Button btReplicate = new Button("REPLICATE");
		Button btClear = new Button("CLEAR ALL");
		Button btMutate = new Button("MUTATE");
		Button btExample = new Button("EXAMPLE");

		// Create panes for buttons and textarea.
		GridPane topPane = new GridPane();
		HBox buttonBox = new HBox();
		FlowPane taPane = new FlowPane();
		FlowPane errorPane = new FlowPane();

		// Set alignment, spacing and style for topPane.
		buttonBox.setSpacing(10);
		buttonBox.setPadding(new Insets(5, 5, 5, 5));
		buttonBox.getChildren().addAll(btReplicate, btClear);
		buttonBox.setAlignment(Pos.BASELINE_CENTER);
		pane.setBottom(buttonBox); // Add buttons to bottom topPane.
		topPane.setStyle("-fx-border-color: black");
		topPane.setPadding(new Insets(10, 5, 10, 5));
		topPane.setHgap(10);
		topPane.setVgap(5);
		taPane.setAlignment(Pos.BASELINE_CENTER);

		// Create text input area.
		Text text = new Text("Type neucleotide base sequence (min 3 bases): (i.e., A U G or C)"); 
		TextArea taRNA = new TextArea();
		taRNA.setPrefRowCount(2);
		taRNA.setPrefColumnCount(40);
		taRNA.setWrapText(true);
		taRNA.setEditable(true);
		taPane.getChildren().add(taRNA);

		// Add text, textarea, taPane and buttonBox to topPane.
		topPane.add(text, 0, 0);
		topPane.add(buttonBox, 6, 1);
		topPane.add(taPane, 6, 0);
		topPane.add(errorPane, 0, 1);
		topPane.add(btExample, 1, 0);

		// Create pane for amino acid display pane.
		VBox aaPane = new VBox(20); // Amino Acid pane.
		StackPane stackPane = new StackPane();
		stackPane.setMinWidth(1100);
		stackPane.getChildren().add(aaPane); // Wrap aaPane in stackPane to center it.

		// Add stackPane to scrollPane and place in center of main pane.
		ScrollPane scrollPane = new ScrollPane(stackPane); 
		scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
		pane.setCenter(scrollPane);

		// Add topPane to main pane.
		pane.setTop(topPane);

		/* Add action handlers (using lambda expressions) for EXAMPLE, REPLICATE, MUTATE and CLEAR ALL buttons. */

		// "EXAMPLE" button
		btExample.setOnAction(e -> { // Starts example run.
			
			// Clear LinkedList & Textarea
			codonList.clear();
			taRNA.clear();
			
			// Clear nodes from aaPane.
			aaPane.getChildren().clear(); 

			// Clear text area
			taRNA.clear();
			
			// Clear any Error Messages.
			errorPane.getChildren().clear();
			
			// Remove MUTATE button if exists.
			buttonBox.getChildren().remove(btMutate);
			
			// Add base letters to textarea.
			String s = "UUUAAAGGGCCC";
			//String [] exampleArray = new String [s.length()];
			for (int x = 0; x < s.length(); x++) {
				taRNA.appendText(s.substring(x, x+1)); // Add each letter to text area.
				if (x % 3 == 2) {
					taRNA.appendText(" ");
				}
			}
						
			// Convert to codons, replicate, and display in GUI.
			String [] codonArray = new String[s.length()/3];
			codonArray = toCodon(s); // Change to codons.
			Label label = new Label("", replicate(codonArray)); // New label.
			label.setContentDisplay(ContentDisplay.BOTTOM);
			aaPane.getChildren().add(label); // Add label to aaPane.
			aaPane.setAlignment(Pos.CENTER); // Center in aaPane.
			
			// Button appears for MUTATE option.
			buttonBox.getChildren().add(btMutate);
		});	

		// "REPLICATE" button
		btReplicate.setOnAction(e -> { // Starts new run using sequence saved in taPane.
			// Increase generation count.
			//generationCount = increase(generationCount); 
			
			// Remove MUTATE button if exists.
			buttonBox.getChildren().remove(btMutate);
			
			//** If non-base character used, display error.	**/			
			
			// Create Error Message:
			Rectangle rectangle = new Rectangle(310, 25, Color.valueOf("ORANGE")); // Orange error box.
			rectangle.setStroke(Color.BLACK);
			Label lblError = new Label("ERROR: Incorrect character used.", rectangle); // Error text.
			lblError.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 20));
			lblError.setContentDisplay(ContentDisplay.CENTER);
			
			// Loop checks string characters.
			String s = taRNA.getText().trim().toUpperCase().replaceAll("\\s+", ""); // Trim text.
			//System.out.println("string s = " + s);
			for (int c = 0; c < taRNA.getText().trim().replaceAll("\\s+", "").length(); c++) {
				if (s.contains("B") || s.contains("D") || s.contains("E") || s.contains("F") || s.contains("H") || s.contains("I") || 
					s.contains("J") || s.contains("K") || s.contains("L") || s.contains("M") || s.contains("N") || s.contains("O") ||
					s.contains("P") || s.contains("Q") || s.contains("R") || s.contains("S") || s.contains("T") || s.contains("V") ||
					s.contains("W") || s.contains("X") || s.contains("Y") || s.contains("Z") || s.contains("1") || s.contains("2") ||
					s.contains("3") || s.contains("4") || s.contains("5") || s.contains("6") || s.contains("7") || s.contains("8") ||
					s.contains("9") || s.contains("0")) {
					// Display Error Message:
					errorPane.getChildren().add(lblError); // Place label in pane.
					return; // Stop program.
				}
				else {
					// Clear previous Error Message:
					errorPane.getChildren().clear();
				}
			}
			// clear codonList, add string to linkedlist, temp array toCodon, appendtext of codonList, replicate to tempArray of codons
			
			// Clear LinkedList
			codonList.clear();
			
			// Add string s to LinkedList 
			for (int i = 0; i < s.length(); i++) { 
				codonList.add(s.substring(i, i+1)); // Add to codonList.
			}
				
			// Temporary array holds list of codons.
			String [] codonArray = new String[taRNA.getLength()/3]; // New string array.
			codonArray = toCodon(s); // Convert RNA string to codons of 3.
			
			// Replace textarea with uppercase base sequence.
			taRNA.clear(); // Clear TextArea
			for (int t = 0; t < codonList.size(); t++) {
				taRNA.appendText(codonList.get(t));
				if (t % 3 == 2) {
					taRNA.appendText(" ");
				}
			}
			
			// Ready pane for calling replication method.
			Label label = new Label("", replicate(codonArray)); // New label.
			label.setContentDisplay(ContentDisplay.BOTTOM);
			aaPane.getChildren().add(label); // Add label to aaPane.
			aaPane.setAlignment(Pos.CENTER); // Center in aaPane.

			// Button appears for MUTATE option.
			buttonBox.getChildren().add(btMutate);

		});		
		
		// "MUTATE" button
		btMutate.setOnAction(e -> { // Randomly mutates sequence saved in taPane.
			// Increaase generation count.
			//generationCount = increase(generationCount); 
			
			//** If non-base character used, display error.	**/			
			// Create Error Message:
			Rectangle rectangle = new Rectangle(310, 25, Color.valueOf("ORANGE")); // Orange error box.
			rectangle.setStroke(Color.BLACK);
			Label lblError = new Label("ERROR: Incorrect character used.", rectangle); // Error text.
			lblError.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 20));
			lblError.setContentDisplay(ContentDisplay.CENTER);
			
			// Loop checks string characters.
			String string = taRNA.getText().trim().toUpperCase().replaceAll("\\s+", ""); // Trim text.
			for (int c = 0; c < taRNA.getText().trim().replaceAll("\\s+", "").length(); c++) {
				if (string.contains("B") || string.contains("D") || string.contains("E") || string.contains("F") || string.contains("H") || 
					string.contains("I") || string.contains("J") || string.contains("K") || string.contains("L") || string.contains("M") || 
					string.contains("N") || string.contains("O") || string.contains("P") || string.contains("Q") || string.contains("R") ||
					string.contains("S") || string.contains("T") || string.contains("V") || string.contains("W") || string.contains("X") || 
					string.contains("Y") || string.contains("Z") || string.contains("1") || string.contains("2") || string.contains("3") || 
					string.contains("4") || string.contains("5") || string.contains("6") || string.contains("7") || string.contains("8") ||
					string.contains("9") || string.contains("0")) {
					// Display Error Message:
					errorPane.getChildren().add(lblError); // Place label in pane.
					return; // Stop program.
				}
				else {
					// Clear previous Error Message:
					errorPane.getChildren().clear();
				}
			}
			
			// Call mutate method to mutate string.
			String mutatedString = mutate(string);
			mutatedString.trim(); // Trim whitespace.
			
			// Put new mutatedString into codonList.
			codonList.clear();
			for (int i = 0; i < mutatedString.length(); i++) { 
				codonList.add(mutatedString.substring(i, i+1)); // Add to codonList.
			}		
			
			// Temp mutatedArray holds list of new codons.
			String [] mutatedArray = new String[mutatedString.length()/3]; // New string array.
			mutatedArray = toCodon(mutatedString); // Convert RNA string to codons of 3.
			
			// Replace textarea with new base sequence.
			taRNA.clear(); // Clear TextArea
			for (int t = 0; t < codonList.size(); t++) {
				taRNA.appendText(codonList.get(t));
				if (t % 3 == 2) {
					taRNA.appendText(" ");
				}
			}
		
			// Ready pane for calling replication method.
			Label label = new Label("", replicate(mutatedArray)); // New label.
			label.setContentDisplay(ContentDisplay.BOTTOM);
			aaPane.getChildren().add(label); // Add label to aaPane.
			aaPane.setAlignment(Pos.CENTER); // Center in aaPane.

		});

		// "CLEAR ALL" button	
		btClear.setOnAction(e -> { // Completely clears GUI to start new sequence.
			// Clear nodes from aaPane.
			aaPane.getChildren().clear(); 

			// Clear text area
			taRNA.clear();
			
			// Clear any Error Messages.
			errorPane.getChildren().clear();
			
			// Remove MUTATE button.
			buttonBox.getChildren().remove(btMutate);
		});

		/* Create scene & place it in stage. */
		Scene scene = new Scene(pane, 1150, 900);
		primaryStage.setTitle("CSCI 1115 Final Project"); // Set title
		primaryStage.setScene(scene); // Place the scene in the stage
		primaryStage.show(); // Display the stage

		scrollPane.requestFocus();
	}


	/** The replicate() method. */

	/** This method takes an LinkedList<String> and, using the AminoAcid class, returns an HBox with amino acid circle labels. Each circle represents a different amino acid and is displayed with the amino acid abbreviation and the corresponding rna codon sequence. 
	 *
	 * <pre>Example:
	 *{@code	replicate(LinkedList<String>) returns HBox
	 *}</pre>
	 *
	 * @param (LinkedList<String>; an LinkedList string with indices of 3 base characters (i.e., "UUU", "AAA", "CAU", etc) that will be matched to the corresponding amino acid.)
	 * @return HBox (Hbox; object with amino acid circle labels. Each amino acid is labeled with abbrevation on circle and rna codon sequence below the circle.) 
	 */

	public static HBox replicate(LinkedList<String> codonList) { /** Amino Acid class needs codons to work. */

		// Pass contents of stringArray to AminoAcid class.
		AminoAcid aminoAcidCircles = new AminoAcid(codonList); // Create colored circles for amino acids.

		return aminoAcidCircles.graphicAA(); // Return HBox with a colored circle for each amino acid.
	}
	
	public static HBox replicate(String [] codonList) { /** Helper method that takes String array. */
	
		// New linked list from string array.
		LinkedList<String> linkedList = new LinkedList<String>();
		for (int i = 0; i < codonList.length; i++) {
			linkedList.add(codonList[i]);
		}
		
		// Pass contents of stringArray to AminoAcid class. 
		AminoAcid aminoAcidCircles = new AminoAcid(linkedList); // Create colored circles for amino acids.

		return aminoAcidCircles.graphicAA(); // Return HBox with a colored circle for each amino acid.
	}


	/** The toCodon() method. **/
	
	/** This method is used to concat strings into a String Array from a string. Method converts string into string array then concats 3 indices at a time (i.e., array with index values of "A A A A A A" becomes "AAA AAA").  Best for changing a string of RNA characters to an array of codons.
	 * <pre>Examples: 
	 * {@code	toCodon(baseSequence) returns codonSequence[]
	 *} </pre>
	 *
	 * @param baseSequence (String; string with only characters of A, C, G, or U)
	 * @return codonSequence (String []; an array of strings where each index is three characters such as AAA)
	 */

	public static String [] toCodon(String baseSequence) {
		/* Method takes array of single character strings and converts it to new string array where each index has three characters (i.e. a codon). */

		// Put baseSequence string into array format.
		String [] baseArray = new String[baseSequence.length()];
		for (int k = 0; k < baseSequence.length(); k++) {
			baseArray[k] = (String)(baseSequence.charAt(k) + "");
		}	

		// Assign base sequence length to codonSeq array.
		int arrayLength = ((baseSequence.length())/3);
		String [] codonSequence = new String[arrayLength]; // new String array to return.

		// Loop assigns 3 characters of 3 indices in base sequence to one index in a new codon array.
		for (int i = 0, j = 0; i < codonSequence.length; i++, j+=3) { // Where i = codonSeq array index, & j = sequence string index.
			if (baseArray[j] != "" && baseArray[j+1] != "" && baseArray[j+2] != "") { 
				// Only concat 3 character codon if there are 3 characters in sequence to use.
				codonSequence[i] = baseArray[j] + baseArray[j+1] + baseArray[j+2];
			}
		}
		return codonSequence;
	}


	/** The mutate() method. **/

	/** This method takes a string containing characters (A, C, G, or U), and randomly selects a random number of indices to change.  It will
	change, delete, or insert next to the index character.  Returns mutated string containing indices of single characters (A, C, G, or U).
	 *
	 * <pre>Example:
	 *{@code	mutate(String sequence) returns String mutatedSequence
	 *}</pre>
	 *
	 * @param sequence (String; a string array of single base characters to be randomly altered.)
	 * @return mutatedSeq (String; a string array of single base characters with indices randomly changed.) 
	 */
	
	public static String mutate(String [] sequence) { // Helper method takes String array.
		String string = ""; // New string.
		for (int i = 0; i < sequence.length; i++) {
			string.concat(sequence[i]); // Add to string.
		}
		return string;
	}
	
	public static String mutate(String sequence) {
		/* Method will randomly mutate contents of a random number of indices in a string array, and return a randomly mutated string array.
		 * Random mutations include point, deletion and insertion. */	

		// Copy original sequence to new array.
		LinkedList<String> stringList = new LinkedList<String>();
		for (int i = 0; i < sequence.length(); i++) {
			stringList.add(sequence.substring(i, i+1));
		}

		// Randomly selects total number of mutations.
		int numberOfMutations = (int)(Math.random() * stringList.size());

		// Randomly select index from new array.
		for (int j = 0; j < numberOfMutations; j++) {
			int typeOfMutation = (int)(Math.random() * 3);
			int randomIndex = (int)(Math.random() * stringList.size());
			
			if (typeOfMutation == 0) { // POINT MUTATION
				// Select random base for replacement.
				String randomBase = new String(Integer.toString((int)(Math.random() * 4)));
				
				if (randomBase.equals("0")) { // If random number is 0, mutation to base A.
					randomBase = ("A");
				}
				if (randomBase.equals("1")) { // If random number is 1, mutation to base C.
					randomBase = ("C");
				}
				if (randomBase.equals("2")) { // If random number is 2, mutation to base G.
					randomBase = ("G");
				}
				if (randomBase.equals("3")) { // If random number is 3, mutation to base U.
					randomBase = ("U");
				}
				// Mutate random index to random nucleotide base (A, C, G, U).
				stringList.add(randomIndex, randomBase);
			}
			if (typeOfMutation == 1) { // DELETION
				stringList.remove(randomIndex);
			}
			if (typeOfMutation == 2) { // INSERTION
				String randomBase = new String(Integer.toString((int)(Math.random() * 4)));
				
				if (randomBase.equals("0")) { // If random number is 0, mutation is base A.
					randomBase = ("A");
				}
				if (randomBase.equals("1")) { // If random number is 1, mutation is base C.
					randomBase = ("C");
				}
				if (randomBase.equals("2")) { // If random number is 2, mutation is base G.
					randomBase = ("G");
				}
				if (randomBase.equals("3")) { // If random number is 3, mutation is base U.
					randomBase = ("U");
				}
				stringList.add(randomIndex, randomBase); // Insert randomBase to the randomIndex
			}	
		}
		// Concat String array to single string for return.
		String mutatedSeq = "";
		for (int i = 0; i < stringList.size(); i++) {
			mutatedSeq = mutatedSeq.concat(stringList.get(i));
		}
		// Return mutated string. 
		return mutatedSeq;
	}	
}
