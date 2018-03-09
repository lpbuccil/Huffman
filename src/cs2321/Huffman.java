package cs2321;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import net.datastructures.*;


//import java.io.BufferedWriter;
//import java.io.FileReader;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//
//import net.datastructures.*;
/*
 * The format of the compressed file includes 3 continuously parts:
 *  1. prefix tree in bit stream
 *  2. length of the original file using 4 bytes
 *  3. data coded with Huffman coding. 
 * 
 * Encoding prefix tree bit stream: 
 *   if the node is external, output 0, followed by the letter
 *   if the node is internal, output 1, followed by 
 *  		the bit stream of left subtree, then the bit stream of right subtree. 
 */
public class Huffman {

    LinkedBinaryTree<Character> t = new LinkedBinaryTree();
    String[] CODE_TABLE = new String[256];

    public static void main(String[] args) {
        Huffman huffman = new Huffman();
        int length;

        // db.txt has only two letters "ab". The length with Huffman coding should be 2.
        length = huffman.compress("ab.txt", "ab.txt.huffman");
        System.out.println("length is " + length);

        // decode your newly created compress file. The generated file "ab.txt.decoded" should have same content as "ab.txt"
        huffman.decode("ab.txt.huffman", "ab.txt.decoded");


        // decode the previous correctly compressed file by instructor.  The generated file "ab.txt.decoded" should have content as "ab.txt"
        huffman.decode("ab.txt.compressed", "ab.txt.decoded");


        // You may perform the above same testing for other files, like abra.txt, gogo.txt, tinytinyTable.txt

    }

    /**
     * Compress file using Huffman code.
     *
     * @param inputFile  The original data file
     * @param outputFile The compressed data file that should be generated.
     * @return the length of the data encoded with Huffman Code, don't include data for the prefix tree and length of the original file.
     */
    public int compress(String inputFile, String outputFile) {
        String inputFileString = "";
        char[] inputFileCharacterArray = null;
        FileReader fileReader;


        //Read all text from file and store it in inputFileString then add all char to inputFileCharArray
        try {
            fileReader = new FileReader(inputFile);
            while (fileReader.ready()) {
                // System.out.println((char) fileReader.read());
                inputFileString += (char) fileReader.read();
            }

            //remove new line feed char and windows char
            inputFileString = inputFileString.replace("\n", "").replace("\r", "");
            inputFileCharacterArray = inputFileString.toCharArray();
            fileReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


        //Calculate frequency of chars
        int[] frequencyOfChars = new int[256];
        for (int i = 0; i < inputFileCharacterArray.length; i++) {
            frequencyOfChars[inputFileCharacterArray[i]]++;
        }

        LinkedBinaryTree<Character> huffmanTree = buildPrefixTree(frequencyOfChars);

        buildCodeTable(huffmanTree.root(), "");


        try {
            FileWriter fileWriter = new FileWriter(outputFile);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            



              bufferedWriter.close();
                fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }



    public LinkedBinaryTree<Character> buildPrefixTree(int[] A){

        HeapPQ<Integer, LinkedBinaryTree<Character>> PQ = new HeapPQ<>();

        for (char i = 0 ; i < 256 ; i++) {
            if (A[i] > 0) {
                t = new LinkedBinaryTree<>();
                t.addRoot(i);
                PQ.insert(A[i], t);
            }
        }

        while (PQ.size() > 1){
            Entry<Integer, LinkedBinaryTree<Character>> e1 = PQ.removeMin();
            Entry<Integer, LinkedBinaryTree<Character>> e2 = PQ.removeMin();
                int combinedFreq = e1.getKey() + e2.getKey();
                t = new LinkedBinaryTree();
                t.addRoot((char) 0);
                t.attach(t.root(), e1.getValue(), e2.getValue());
                PQ.insert(combinedFreq, t);
        }
        return PQ.min().getValue();
    }

    public void buildCodeTable(Position<Character> V, String code){

        if (t.isExternal(V)){
            CODE_TABLE[V.getElement()] = code;
        }else {
            buildCodeTable(t.left(V), code + "0");
            buildCodeTable(t.right(V), code + "1");
        }

    }

    /**
     * Decode the compressed data file back to the original data file.
     *
     * @param inputFile  : the compressed file
     * @param outputFile : the file that should be generated by the decode function using ascii code.
     */


    public void decode(String inputFile, String outputFile) {

    }







}
