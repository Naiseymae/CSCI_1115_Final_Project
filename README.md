## CSCI_1115_Final_Project

# Synopsis
This is the final project code for the Algorithms and Data Structures course. This program allows a user to input their own genetic code and alter using any type of mutation they desire in order to see how each mutation alters the outcome of the amino acid sequence. When the program runs, a GUI window loads displaying a text area and buttons. A user types RNA nucleotide base characters (A, U, G, C) and then press the Replicate  button to show that sequence as an amino acid graphic. The amino acid strings are displayed as colored circles with their corresponding RNA codons underneath. The user can freely type changes in the text area and replicate those changes (or mutations), or the user can use the Mutate button. The Mutate button will randomly select a random number of indicies to change, and those changes will be a random selection of point, deletion and insertion mutations. That new mutated sequence will be displayed as an amino acid graphic. The user may alter their base sequence as much as they like or can start over by clicking the Clear All button and input a new RNA sequence. This program, in partnership with the AminoAcid class, benefits from using a LinkedList and a Map, and simulates how neucleotide bases can be inserted or deleted anywhere in the genetic code.  


# Code Example 1
The following code shows how the mutate method randomly selects a random number of mutations, a random type of mutation, and random neucleotide base to mutate into.

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
  
 

# Code Example 2
The code below is a portion of the graphicAA method inside the AminoAcid class which returns an HBox filled with amino acid circles that will be displayed in the main pane. This method uses a Map to match the codon in the array to an amino acid and create a new label with a circle object while also displaying the codon sequence underneath the circle. When the sequence for a STOP codon is found, a rectangle instead of an amino acid circle is created, and the method ends.

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
 

# Motivation
This new program will allow for a more hands on approach, giving the user the ability to insert, delete, and alter specific parts of their genetic code.  My previous program allowed random mutations decided by the program, not the user.  In this new program, I allow the user to take more of a role in deciding where the mutations will occur, thus cutting a pathway for the user to be more engaged in the process.  I aim to find better way to engage the user, and I believe that by granting the user a choice in which parts of the code to alter, they will feel more engaged and entertained, thus leading to a better learning experience.

