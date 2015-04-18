package HW9;

import javax.xml.datatype.DatatypeConfigurationException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

/**This can be interactive or called from commandlines
 * 
 * @author Kelly Jones
 *
 */

public class A09Huffman {
	int failcount = 0;//an integer to keep track of mistakes
	public static void main(String args[]) throws IOException{
		if(args.length==0){
			A09Huffman go = new A09Huffman();
			go.start();
		}
		else{
			Huffman.decompress(args[0]);
		}

		
	}
	/**This method starts a console input interface if no argument is given on run
	 * 
	 */
	public void start() throws IOException{		
		System.out.println("No arguments given, interactive mode starting...\nInput the location of a valid .huff file (enter -h for help, -q to exit):");
		String input = "";
		Scanner scanIn = new Scanner(System.in);
		input = scanIn.nextLine();
		File huffIn = new File(input);
		if(input.equals("-h")){
			System.out.println("please provide a valid file name as an argument EX:\njava A09Huffman MyFavoriteFile.huff");
		}
		else if(input.equals("-q")){
			System.out.println("Okay maybe you ran this on accident I will just leave you alone now...");
		}
		else if(huffIn.exists()){
			if(input.endsWith(Huffman.HUFF_EXT)){
				decodeMenu(input);				
			}
			else{
				System.out.println("File does not use a "+ Huffman.HUFF_EXT +" extension! change this and run it if you dare...");
				failcount++;
				start();
			}
		}
		else if(input.length()>=1){
			System.out.println("That file doesn't exist in this directory...");
			failcount++;
			start();
		}
		else{
			System.out.println("Why don't you type something in?");
			failcount++;
			start();
		}
	}
	/**an interactive menu for decoding
	 * 
	 * @param fileName the name of the file to decode
	 * @throws IOException
	 */
	private void decodeMenu(String fileName) throws IOException{
		FileInputStream inStream = new FileInputStream(fileName);
		BitReader br = new BitReader(inStream);
		Huffman huffopolisPrime = new Huffman(br);
		
		System.out.println(
				"What do you want to do now?\n"
				+ "Decompress the file to a regular format: f\n"
				+ "Decode the data to the console as ASCII: p\n"
				+ "Just print out that beautiful Huffman tree: b\n"
				+ "Get me outta here!: q\n");
		String input = "";
		Scanner scanIn = new Scanner(System.in);
		input = scanIn.nextLine();
		if(input.equals("f")){
			String newName = fileName.substring(0, fileName.lastIndexOf(Huffman.HUFF_EXT));
			File checkName = new File(newName);
			
			if(checkName.exists()){
				System.out.println(newName + " already exists, do you want to overwrite? y(es)|n(o)");
				String owinput = "";
				Scanner owscanIn = new Scanner(System.in);
				owinput = owscanIn.nextLine();
				if(owinput.equals("n")){
					System.out.println("Ok maybe do something else...");
					decodeMenu(fileName);
				}
				else if(owinput.equals("y")){
					System.out.println("your file " + newName + " will now be OVERWRITTEN...");
					huffopolisPrime.decompress(fileName);
					decodeMenu(fileName);
				}
				else{
					System.out.println("invalid input!");
					decodeMenu(fileName);
				}
			}
			System.out.println("your file will be written as " + newName);
			huffopolisPrime.decompress(fileName);
			decodeMenu(fileName);
		}
		else if(input.equals("p")){
			huffopolisPrime.decode(huffopolisPrime. bytes, br, System.out);
			System.out.println("\n");
			decodeMenu(fileName);
		}

		else if(input.equals("b")){
			System.out.println("\n" + huffopolisPrime.huffmanTreeToString() + "\n");
			
			decodeMenu(fileName);
		}
		else if(input.equals("q")){
			System.out.println("Until we meet again...");
			
			return;
		}
		else{
			System.out.println("Thats not a valid command!");
			
			decodeMenu(fileName);
		}
		inStream.close();
	}

}