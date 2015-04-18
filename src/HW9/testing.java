package HW9;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
/**Testing the huffman class by checking individual methods
 * 
 * @author Kelly Jones
 *
 */
public class testing {
	
	public static void main(String args[]) throws IOException{
		
		File jabber =  new File("triangles.txt.huff");
		FileInputStream testin = new FileInputStream(jabber);
		BitReader testBR = new BitReader(testin);
		Huffman testHF = new Huffman(testBR);
		//testHF.decode(testHF.bytes, testBR, System.out);
		testHF.decompress("triangles.txt.huff");
		testin.close();
		
		
	}

}

//some early notes on how the tree was being written
/**
init build
1006
Internal Node
Internal Node
Internal Node
Internal Node
Internal Node
Leaf node is: 103
Leaf node is: 117
Internal Node
Internal Node
Internal Node
Leaf node is: 46
**/
//103,117,46,118,33,119,112,66,79,72,99,109,111,97,104,98,84,63,120,68,34,44,116,100,108,39,59,76,83,58,107,121,110,32,13,105,115,10,65,106,45,74,67,102,114,101