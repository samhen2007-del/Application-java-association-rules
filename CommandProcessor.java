package AssociationRules_Algos;
/*
 * Copyright (c) 2008-2015 Philippe Fournier-Viger
 *
 * This file is part of the SPMF DATA MINING SOFTWARE
 * (http://www.philippe-fournier-viger.com/spmf).
 *
 * SPMF is free software: you can redistribute it and/or modify it under the
 * terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * SPMF is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * SPMF. If not, see <http://www.gnu.org/licenses/>.
 */
import java.io.File;
import java.io.IOException;
import java.util.Map;
/**
 * This class executes commands from the command line interface or
 * graphical interface to run the algorithms.
 * 
 * @author Philippe Fournier-Viger
 */
public class CommandProcessor {
    public static int minsuppR;
    public static int k_c =0;
    public static int kRulesC;
    public static MemoryLogger y; 
    public static long timeTotale;
	/**
	 * This method run an algorithm. It is called from the GUI interface or when
	 * the user run the jar file from the command line.
	 * 
	 * @param algorithmName
	 *            the name of the algorithm
	 * @param inputFile
	 *            the input file for the algorithm
	 * @param outputFile
	 *            the output file for the algorithm
	 * @param parameters
	 *            the parameters of the algorithm
	 * @throws IOException  exception if an error occurs
	 */
	public static void runAlgorithm(String algorithmName,
			String inputFile, String outputFile, String[] parameters) throws IOException {
//		 int minsuppR=0;
//		 int k_c=0;
//		 int kRulesC=0;
//		 MemoryLogger y; 
//		 long timeTotale;
		// System.out.println("C" + algorithmName);
		// **** CHECK IF ARFF AS INPUT FILE *****
		// FIRST WE WILL CHECK IF IT IS AN ARFF FILE...
		// IF YES, WE WILL CONVERT IT TO SPMF FORMAT FIRST,
		// THEN WE WILL RUN THE ALGORITHM, AND FINALLY CONVERT THE RESULT SO
		// THAT IT CAN
		// BE SHOWED TO THE USER.
		// This map is to store the mapping from ItemID to Attribute value for
		// the conversion
		// from ARFF to SPMF.	
		Map<String, String> mapItemAttributeValue = null;
		// This variable store the path of the original output file
		String originalOutputFile = null;
		// This variable store the path of the original input file
		String originalInputFile = null;
		// If the file is ARFF
		if (inputFile != null
				&& (inputFile.endsWith(".arff") || inputFile.endsWith(".ARFF"))) {
			// Convert it
			TransactionDatabaseConverter converter = new TransactionDatabaseConverter();
			System.out.println("Converting ARFF to SPMF format.");
			// save the file paths selected by the user
			originalOutputFile = outputFile;
			originalInputFile = inputFile;
			// change the ouptut file path to a temporary file
			inputFile = inputFile + ".tmp";
			outputFile = outputFile + ".tmp";
			mapItemAttributeValue = converter.convertARFFandReturnMap(
					originalInputFile, inputFile, Integer.MAX_VALUE);
			System.out.println("Conversion completed.");
		}
		// ****** NEXT WE WILL APPLY THE DESIRED ALGORITHM ******
		// There is a if condition for each algorithm.
		// I will not describe them one by one because it is
		// straightforward.
		
		 if ("TopKRules".equals(algorithmName)) {
			Database database = new Database();
			database.loadFile(inputFile);
			int k = getParamAsInteger(parameters[1]);
			double minconf = getParamAsDouble(parameters[2]);           
			AssociationRules_Algos.AlgoTopKRules algo = new AssociationRules_Algos.AlgoTopKRules();
			algo.runAlgorithm(k, minconf, database);
			algo.printStats();
			k_c = k;
			minsuppR = algo.minsuppRelative;
			kRulesC = algo.kRules.size();
			y = algo.x;
			timeTotale = algo.TimeTotale;
			//JOptionPane.showMessageDialog (null, "MinSup :"+minsuppR, "support minimum", JOptionPane.INFORMATION_MESSAGE);
			//JOptionPane.showMessageDialog (null, "MinSup :"+algo.kRules.size(), "Nombre Rules trouvés", JOptionPane.INFORMATION_MESSAGE);
			//JOptionPane.showMessageDialog (null, "MinSup :"+algo.x, "Memoire utilisé", JOptionPane.INFORMATION_MESSAGE);
			//JOptionPane.showMessageDialog (null, "MinSup :"+algo.TimeTotale, "Temps consommé", JOptionPane.INFORMATION_MESSAGE);
			algo.writeResultTofile(outputFile); // to save results to file
		} else if ("TNR".equals(algorithmName)) {
			AssociationRules_Algos.Database database = new AssociationRules_Algos.Database();
			database.loadFile(inputFile);

			int k = getParamAsInteger(parameters[1]);
			double minconf = getParamAsDouble(parameters[2]);
			int delta = getParamAsInteger(parameters[3]);
			AssociationRules_Algos.AlgoTNR algo = new AssociationRules_Algos.AlgoTNR();
			algo.runAlgorithm(k, minconf, database, delta);
			algo.printStats();
			k_c = k;
			minsuppR = algo.minsuppRelative;
			kRulesC = algo.kRules.size();
			y = algo.x;
			timeTotale = algo.TimeTotale;			
			algo.writeResultTofile(outputFile); // to save results to file
		}
		 

		// IF THE FILE WAS AN ARFF FILE, WE NEED TO CONVERT BACK THE RESULT
		// SO THAT IT IS PRESENTED IN TERMS OF VALUES
		if (mapItemAttributeValue != null) {
			ResultConverter converter = new ResultConverter();
			System.out
					.println("Post-processing to show result in terms of ARFF attribute values.");
			converter.convert(mapItemAttributeValue, outputFile,
					originalOutputFile);
			System.out.println("Post-processing completed.");
			// delete the temporary files
			// System.out.println("Delete : " + outputFile);
			File file = new File(outputFile);
			file.delete();
			// System.out.println("Delete : " + inputFile);
			File file2 = new File(inputFile);
			file2.delete();
			// set the original outputFile and inputFile
			outputFile = originalOutputFile;
			inputFile = originalInputFile;
		}
	}

	/**
	 * Method to convert a parameter given as a string to a double. For example,
	 * convert something like "50%" to 0.5.
	 * 
	 * @param value
	 *            a string
	 * @return a double
	 */
	private static double getParamAsDouble(String value) {
		if (value.contains("%")) {
			value = value.substring(0, value.length() - 1);
			return Double.parseDouble(value) / 100d;
		}
		return Double.parseDouble(value);
	}

	/**
	 * Method to transform a string to an integer
	 * 
	 * @param value
	 *            a string
	 * @return an integer
	 */
	private static int getParamAsInteger(String value) {
		return Integer.parseInt(value);
	}
	
	/**
	 * Method to transform a string to an boolean
	 * 
	 * @param value         a string
	 * @return a boolean
	 */
	@SuppressWarnings("unused")
	private static boolean getParamAsBoolean(String value) {
		return Boolean.parseBoolean(value);
	}

	/**
	 * Method to get a parameter as a string. Note: this method just return the
	 * string taken as parameter.
	 * 
	 * @param value
	 *            a string
	 * @return a string
	 */
	@SuppressWarnings("unused")
	private static String getParamAsString(String value) {
		return value;
	}

}
