
/* 
 * Author: Renee Linford
 * Date: 12-4-19
 * OOP Final Project: AminoAcid (extends HBox Class)
 * 
 * -------------------------------
 * 		AminoAcid Object
 * -------------------------------
 * -aaList: ArrayList<String>
 * -codonArray: String[]
 * -aminoAcidArray: String[]
 * -------------------------------
 * #AminoAcid()
 * #AminoAcid(aaNames: String[])
 * +graphicAA: HBox
 * +getLength: int
 * +toAminoAcid: String[]
 * +toCodon: String[]
 * -------------------------------
 * 
 */

import java.util.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;

public class AminoAcid extends HBox {
	// Creates and displays a representation of an amino acid string.

	// Variables for class.
	private LinkedList<String> codonList = new LinkedList<String>();
	//private LinkedList<String> aaList = new LinkedList<String>();
	Map <String, String> aminoAcidMap = new LinkedHashMap<>(); 
	
	// Set no-arg constructor.
	public AminoAcid() {
	}

	// Set constructor with array.
	public AminoAcid(LinkedList<String> stringList) {	
		
		// New LinkedList<String>
		for (int i = 0; i < stringList.size(); i++) {
			this.codonList.add(stringList.get(i));
		}
	}

	public int getLength() {
		// Get length of AminoAcidString.
		return this.codonList.size();
	}
	

	/**
	 * This method takes a LinkedList of codons and returns an HBox with horizontal layout of colored circles. Each circle has label with amino acid abbreviation in the circle, and the corresponding codon sequence underneath. 
	 *
	 * <pre>Example:
	 * {@code	graphicAA(LinkedList<String>) returns HBox
	 *}</pre>
	 *
	 * @param LinkedList (<String>; a string array where each index is three characters long, such as AAA or UGC.)
	 * @return HBox (an HBox displaying the representation of the input codon sequence as a line of labeled circles.)
	 */

	public HBox graphicAA() {
		/* Adds colored circle and label for each amino acid in codonList. */
		
		// Create map entries (K, V)
			aminoAcidMap.put("UUU", "PHE");	aminoAcidMap.put("UUC", "PHE");	aminoAcidMap.put("UUA", "LEU"); aminoAcidMap.put("UUG", "LEU");
			aminoAcidMap.put("CUU", "LEU");	aminoAcidMap.put("CUC", "LEU");	aminoAcidMap.put("CUA", "LEU"); aminoAcidMap.put("CUG", "LEU");
			aminoAcidMap.put("AUU", "ILE");	aminoAcidMap.put("AUC", "ILE");	aminoAcidMap.put("AUA", "ILE"); aminoAcidMap.put("AUG", "MET");
			aminoAcidMap.put("GUU", "VAL");	aminoAcidMap.put("GUC", "VAL");	aminoAcidMap.put("GUA", "VAL"); aminoAcidMap.put("GUG", "VAL");
			aminoAcidMap.put("UCU", "SER");	aminoAcidMap.put("UCC", "SER");	aminoAcidMap.put("UCA", "SER"); aminoAcidMap.put("UCG", "SER");	
			aminoAcidMap.put("CCU", "PRO");	aminoAcidMap.put("CCC", "PRO");	aminoAcidMap.put("CCA", "PRO"); aminoAcidMap.put("CCG", "PRO");
			aminoAcidMap.put("ACU", "THR");	aminoAcidMap.put("ACC", "THR");	aminoAcidMap.put("ACA", "THR"); aminoAcidMap.put("ACG", "THR");	
			aminoAcidMap.put("GCU", "ALA");	aminoAcidMap.put("GCC", "ALA");	aminoAcidMap.put("GCA", "ALA"); aminoAcidMap.put("GCG", "ALA");	
			aminoAcidMap.put("UAU", "TYR");	aminoAcidMap.put("UAC", "TYR");	aminoAcidMap.put("UAA", "STOP"); aminoAcidMap.put("UAG", "STOP");
			aminoAcidMap.put("CAU", "HIS");	aminoAcidMap.put("CAC", "HIS");	aminoAcidMap.put("CAA", "GLN"); aminoAcidMap.put("CAG", "GLN");
			aminoAcidMap.put("AAU", "ASN");	aminoAcidMap.put("AAC", "ASN");	aminoAcidMap.put("AAA", "LYS"); aminoAcidMap.put("AAG", "LYS");
			aminoAcidMap.put("GAU", "ASP");	aminoAcidMap.put("GAC", "ASP");	aminoAcidMap.put("GAA", "GLU"); aminoAcidMap.put("GAG", "GLU");
			aminoAcidMap.put("UGU", "CYS");	aminoAcidMap.put("UGC", "CYS");	aminoAcidMap.put("UGA", "STOP"); aminoAcidMap.put("UGG", "TRP");
			aminoAcidMap.put("CGU", "ARG");	aminoAcidMap.put("CGC", "ARG");	aminoAcidMap.put("CGA", "ARG"); aminoAcidMap.put("CGG", "ARG");
			aminoAcidMap.put("AGU", "SER");	aminoAcidMap.put("AGC", "SER");	aminoAcidMap.put("AGA", "ARG"); aminoAcidMap.put("AGG", "ARG");
			aminoAcidMap.put("GGU", "GLY");	aminoAcidMap.put("GGC", "GLY");	aminoAcidMap.put("GGA", "GLY"); aminoAcidMap.put("GGG", "GLY");

		// Match the amino acid name to a circle of the corresponding color for the whole codonList.

		for (int k = 0; k < this.codonList.size(); k++) {
			String codon = codonList.get(k);
			String aminoAcid = aminoAcidMap.get(codonList.get(k));
			
			if (aminoAcid.equals("STOP")) { // STOP codons.
			
				// Stop adding amino acid circles & display STOP label.
				Rectangle rectangle = new Rectangle(40, 40, Color.valueOf("WHITE"));
				rectangle.setStroke(Color.BLACK);
				Label lblSTOP = new Label("STOP", rectangle);
				lblSTOP.setContentDisplay(ContentDisplay.CENTER);
				
				// Codon variation label is displayed below rectangle.
				Label lblCodon = new Label(codon, lblSTOP);
				lblCodon.setContentDisplay(ContentDisplay.TOP);
				this.getChildren().add(lblCodon);
				
				break;
			}
			
			else { // AA codons
			
				if (aminoAcid.equals("PHE")){ // PHENYLALANINE
					// AA label is displayed on aa circle.
					Label lblPHE = new Label(aminoAcid, new Circle(20, Color.rgb(180, 134, 194)));
					lblPHE.setContentDisplay(ContentDisplay.CENTER);
					// Codon label is displayed below aa circle.
					Label lblCodon = new Label(codon, lblPHE);
					lblCodon.setContentDisplay(ContentDisplay.TOP);
					this.getChildren().add(lblCodon);
				}
				if (aminoAcid.equals("LEU")) { // LEUCINE 
					// AA label
					Label lblLEU = new Label(aminoAcid, new Circle(20, Color.rgb(147, 134, 194)));
					lblLEU.setContentDisplay(ContentDisplay.CENTER);
					// Codon variation label 
					Label lblCodon = new Label("UUA", lblLEU);
					lblCodon.setContentDisplay(ContentDisplay.TOP);
					this.getChildren().add(lblCodon);
				}
				if (aminoAcid.equals("ILE")) { // ISOLEUCINE 
					// AA label
					Label lblILE = new Label(aminoAcid, new Circle(20, Color.rgb(134, 149, 194)));
					lblILE.setContentDisplay(ContentDisplay.CENTER);
					// Codon variation label 
					Label lblCodon = new Label(codon, lblILE);
					lblCodon.setContentDisplay(ContentDisplay.TOP);
					this.getChildren().add(lblCodon);
				}
				if (aminoAcid.equals("MET")) { // METHIONINE 
					// AA label
					Label lblMET = new Label(aminoAcid, new Circle(20, Color.rgb(134, 174, 194)));
					lblMET.setContentDisplay(ContentDisplay.CENTER);
					// Codon variation label 
					Label lblCodon = new Label(codon, lblMET);
					lblCodon.setContentDisplay(ContentDisplay.TOP);
					this.getChildren().add(lblCodon);
				}
				if (aminoAcid.equals("VAL")) { // VALINE 
					// AA label
					Label lblVAL = new Label(aminoAcid, new Circle(20, Color.rgb(134, 194, 192)));
					lblVAL.setContentDisplay(ContentDisplay.CENTER);
					// Codon variation label 
					Label lblCodon = new Label(codon, lblVAL);
					lblCodon.setContentDisplay(ContentDisplay.TOP);
					this.getChildren().add(lblCodon);
				}
				if (aminoAcid.equals("SER")) { // SERINE 
					// AA label
					Label lblSER = new Label(aminoAcid, new Circle(20, Color.rgb(158, 79, 120)));
					lblSER.setContentDisplay(ContentDisplay.CENTER);
					// Codon variation label 
					Label lblCodon = new Label(codon, lblSER);
					lblCodon.setContentDisplay(ContentDisplay.TOP);
					this.getChildren().add(lblCodon);
				}
				if (aminoAcid.equals("PRO")) { // PROLINE 
					// AA label
					Label lblPRO = new Label(aminoAcid, new Circle(20, Color.rgb(124, 90, 184)));
					lblPRO.setContentDisplay(ContentDisplay.CENTER);
					// Codon variation label 
					Label lblCodon= new Label(codon, lblPRO);
					lblCodon.setContentDisplay(ContentDisplay.TOP);
					this.getChildren().add(lblCodon);
				}
				if (aminoAcid.equals("THR")) { // THREONINE 
					// AA label
					Label lblTHR = new Label(aminoAcid, new Circle(20, Color.rgb(71, 114, 173)));
					lblTHR.setContentDisplay(ContentDisplay.CENTER);
					// Codon variation label 
					Label lblCodon = new Label(codon, lblTHR);
					lblCodon.setContentDisplay(ContentDisplay.TOP);
					this.getChildren().add(lblCodon);
				}
				if (aminoAcid.equals("ALA")) { // ALANINE 
					// AA label
					Label lblALA = new Label(aminoAcid, new Circle(20, Color.rgb(134, 194, 162)));
					lblALA.setContentDisplay(ContentDisplay.CENTER);
					// Codon variation label 
					Label lblCodon = new Label(codon, lblALA);
					lblCodon.setContentDisplay(ContentDisplay.TOP);
					this.getChildren().add(lblCodon);
				}
				if (aminoAcid.equals("TYR")) { // TYROSINE 
					// AA label
					Label lblTYR = new Label(aminoAcid, new Circle(20, Color.rgb(237, 157, 181)));
					lblTYR.setContentDisplay(ContentDisplay.CENTER);
					// Codon variation label 
					Label lblCodon = new Label(codon, lblTYR);
					lblCodon.setContentDisplay(ContentDisplay.TOP);
					this.getChildren().add(lblCodon);
				}
				if (aminoAcid.equals("HIS")) { // HISTIDINE 
					// AA label
					Label lblHIS = new Label(aminoAcid, new Circle(20, Color.rgb(189, 119, 126)));
					lblHIS.setContentDisplay(ContentDisplay.CENTER);
					// Codon variation label
					Label lblCodon = new Label(codon, lblHIS);
					lblCodon.setContentDisplay(ContentDisplay.TOP);
					this.getChildren().add(lblCodon);
				}
				if (aminoAcid.equals("GLN")) { // GLUTAMINE 
					// AA label
					Label lblGLN = new Label(aminoAcid, new Circle(20, Color.rgb(166, 73, 73)));
					lblGLN.setContentDisplay(ContentDisplay.CENTER);
					// Codon variation label 
					Label lblCodon = new Label(codon, lblGLN);
					lblCodon.setContentDisplay(ContentDisplay.TOP);
					this.getChildren().add(lblCodon);
				}
				if (aminoAcid.equals("ASN")) { // ASPARAGINE 
					// AA label
					Label lblASN = new Label(aminoAcid, new Circle(20, Color.rgb(204, 141, 114)));
					lblASN.setContentDisplay(ContentDisplay.CENTER);
					// Codon variation label 
					Label lblCodon = new Label(codon, lblASN);
					lblCodon.setContentDisplay(ContentDisplay.TOP);
					this.getChildren().add(lblCodon);
				}
				if (aminoAcid.equals("LYS")) { // LYSINE 
					// AA label
					Label lblLYS = new Label(aminoAcid, new Circle(20, Color.rgb(194, 165, 103)));
					lblLYS.setContentDisplay(ContentDisplay.CENTER);
					// Codon variation label 
					Label lblCodon = new Label(codon, lblLYS);
					lblCodon.setContentDisplay(ContentDisplay.TOP);
					this.getChildren().add(lblCodon);
				}
				if (aminoAcid.equals("ASP")) { // ASPARTIC ACID 
					// AA label
					Label lblASP = new Label(aminoAcid, new Circle(20, Color.rgb(181, 181, 74)));
					lblASP.setContentDisplay(ContentDisplay.CENTER);
					// Codon variation label 
					Label lblCodon = new Label(codon, lblASP);
					lblCodon.setContentDisplay(ContentDisplay.TOP);
					this.getChildren().add(lblCodon);
				}
				if (aminoAcid.equals("GLU")) { // GLUTAMIC ACID 	
					// AA label
					Label lblGLU = new Label(aminoAcid, new Circle(20, Color.rgb(196, 214, 114)));
					lblGLU.setContentDisplay(ContentDisplay.CENTER);
					// Codon variation label 
					Label lblCodon = new Label(codon, lblGLU);
					lblCodon.setContentDisplay(ContentDisplay.TOP);
					this.getChildren().add(lblCodon);
				}
				if (aminoAcid.equals("CYS")) { // CYSTEINE 
					// AA label
					Label lblCYS = new Label(aminoAcid, new Circle(20, Color.rgb(222, 112, 100)));
					lblCYS.setContentDisplay(ContentDisplay.CENTER);
					// Codon variation label 
					Label lblCodon = new Label(codon, lblCYS);
					lblCodon.setContentDisplay(ContentDisplay.TOP);
					this.getChildren().add(lblCodon);
				}
				if (aminoAcid.equals("TRP")) { // TRYPTOPHAN 
					// AA label
					Label lblTRP = new Label(aminoAcid, new Circle(20, Color.rgb(232, 138, 97)));
					lblTRP.setContentDisplay(ContentDisplay.CENTER);
					// Codon variation label 
					Label lblCodon = new Label(codon, lblTRP);
					lblCodon.setContentDisplay(ContentDisplay.TOP);
					this.getChildren().add(lblCodon);
				}
				if (aminoAcid.equals("ARG")) { // ARGININE 
					// AA label
					Label lblARG = new Label(aminoAcid, new Circle(20, Color.rgb(179, 114, 77)));
					lblARG.setContentDisplay(ContentDisplay.CENTER);
					// Codon variation label 
					Label lblCodon = new Label(codon, lblARG);
					lblCodon.setContentDisplay(ContentDisplay.TOP);
					this.getChildren().add(lblCodon);
				}
				if (aminoAcid.equals("GLY")) { // GLYCINE 
					// AA label
					Label lblGLY = new Label(aminoAcid, new Circle(20, Color.rgb(237, 233, 119)));
					lblGLY.setContentDisplay(ContentDisplay.CENTER);
					// Codon variation label 
					Label lblCodon = new Label(codon, lblGLY);
					lblCodon.setContentDisplay(ContentDisplay.TOP);
					this.getChildren().add(lblCodon);
				}
			}
		}
		// return HBox
		return this;
	}
}
