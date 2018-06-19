

import java.io.*;
import java.util.Scanner;

public class Lex{
  public static void main(String[] args) throws IOException {
    
    //vars
    Scanner in=null;
    PrintWriter out = null;
    String line = null;
    String[] token = null;
    int lineNum=-1;
    
    if(args.length != 2){ //check if two arguments are passed
      System.err.println("Usage: Lex input output"); //usage message if 2 are not passed
      System.exit(1);
    }
    
    in=new Scanner(new File(args[0])); //takes in input file
    
    //count lines of the input file
    int numlines = 0; //var for number of lines
    while(in.hasNextLine()){ //while there is a next line,
      numlines++; //increment numlines var
      in.nextLine(); //moves to next line
    }
      
    in.close(); //closes file
    in=null; //clears scanner
    
    List list=new List(); //creates new List named list
    token=new String[numlines]; //creates string array
    in=new Scanner(new File(args[0])); //recreates scanner
    out=new PrintWriter(new FileWriter(args[1]));
    
    while(in.hasNextLine()){
      token[++lineNum]=in.nextLine(); //puts lines of file into array
    }
    
    list.append(0); //puts first line  into list
    
    //insertion sort
    for(int i=1; i<token.length; ++i){
      String temp= token[i];
      int j = i-1; 
      list.moveBack(); //resets list index to back
      
      //compares current line to each line in list
      while(j>=0 && temp.compareTo(token[list.get()]) <=0){
        j--;
        list.movePrev();
      }
      
      if(list.index()>=0){
        list.insertAfter(i);
      }else{
        list.prepend(i);
      }
    }
  
    list.moveFront(); //resets index to front of list
   
    //loops through list printing out lines in order
    while(list.index()>=0){
      out.println(token[list.get()]);
      list.moveNext();
    }
  
    in.close(); //closes file
    out.close(); //closes file writer
  }
}
      