import java.io.BufferedReader;  
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;

import static java.lang.Character.isUpperCase;

public class Main {  
//    public static ArrayList <String> sTokens = new ArrayList<>();
    public static ArrayList<String> sInput = new ArrayList<>();
    public static String token = "";

//    public static ArrayList<String> rawgram =new ArrayList<>();
//    public static ArrayList<String> soutput =new ArrayList<>();

    public static void main(String args[])throws Exception{    
        System.out.println("START");
        inputreader();
        filewriter();
        System.out.println("DONE");
    }    
 
     //read input file
    public static void inputreader()throws Exception
    {
        try{
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        String thisLine = br.readLine();
        while (thisLine != null) {
            // System.out.println(thisLine);
            sInput.add(thisLine);

            if(!thisLine.isEmpty())
            {
                String []temp=thisLine.split(" ");
                String nospace="";
                for(int i=0;i<temp.length;i++)
                {
                    nospace+=temp[i];
                }
             
                tokentype(nospace);
            }
            
            thisLine = br.readLine();
          
        }

        br.close();    
        } 
         catch(Exception e) 
         {
            e.printStackTrace();
         }
    }
  
        //write file
        public static void filewriter()
        {
            try {
                FileWriter writer = new FileWriter("Output.txt");
                for(int i=0;i<sInput.size();i++)
                {
                    writer.write(sInput.get(i));
                }
                
                writer.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }   
    
    public static void tokentype(String input)
    {
      
            char[] a = input.toCharArray();
            boolean isError=false;
        
            String temp ="";
            for(int i=0;i<a.length;i++)
            {
                char c= a[i];
                switch (c) {
                    case '*':
                        temp+= "star ";
                        break;
                    case  '+':
                        temp+= "plus ";
                        break;
                    case 'E':
                        temp+= "epsilon ";
                        break;
                    case '(':
                        temp+= "lparen ";
                        break;
                    case ')':
                        temp+= "rparen ";
                        break;
                    case '?':
                        temp+= "question ";
                        break;
                    case 'U':
                        temp+= "union ";
                        break;
                    case 'a':
                    case 'b':
                    case 'c':
                    case 'd':
                    case 'e':
                    case 'f':
                    case 'g':
                    case 'h':
                    case 'i':
                    case 'j':
                    case 'k':
                    case 'l':
                    case 'm':
                    case 'n':
                    case 'o':
                    case 'p':
                    case 'q':
                    case 'r':
                    case 's':
                    case 't':
                    case 'u':
                    case 'v':
                    case 'w':
                    case 'x':
                    case 'y':
                    case 'z':
                    case '0':
                    case '1':
                    case '2':
                    case '3':
                    case '4':
                    case '5':
                    case '6':
                    case '7':
                    case '8':
                    case '9':
                        temp+= "terminal ";
                        break;

                    default:
                        isError = true;
                        break;
                }
            }
            if(isError)
            {
                System.out.println(sInput.get(sInput.size()-1)+" - Reject");
                sInput.add(" - REJECT \n");

            }
            else
            {
                token = temp+"$" ;
                parse(token);
            }
     }

     public static void parse(String token)
     {
        boolean isError=false;
        Stack<String> stack = new Stack<String>();
        stack.push("S");

        String[] temptoken = token.split(" ");
        Stack<String> input = new Stack<>();
        for(int i=temptoken.length-1;i>-1;i--)
        {
            input.push(temptoken[i]);
        }

        LL1Table ll1 = new LL1Table();
        HashMap<String, HashMap<String,String>> table = ll1.getTable();

        while (!isError && !stack.isEmpty() && !input.isEmpty())
        {
            String currRule = stack.peek();
            String currToken= input.peek();
//          System.out.println(stack.peek());
            
             if(isUpperCase(currRule.charAt(0))) //nonterminal
             {
                 String temp = table.get(currRule).get(currToken);
//            System.out.println("Current RULE: "+currRule);
//            System.out.println("NEW RULE: "+temp);
                 if(temp == null)
                 {
                    isError = true;

                 }
                 else
                 {
                     stack.pop();
                     String[] ttemp=temp.trim().split(" ");
                     for(int i = ttemp.length-1; i>-1;i--)
                     {
                         if(ttemp[i]!="")
                             stack.push(ttemp[i]);
                     }

                 }
             }//end (isUpperCase(currRule.charAt(0)))
             else
             {
//                System.out.println(" INPUT: "+input.peek());

                 if(input.peek().equals(currRule))
                 {
                     input.pop();
                     stack.pop();
                 }
                 else
                 {
                    isError = true;
                 }
             }
         }// end while(!isError && !input.isEmpty() && !stack.isEmpty())

         if(!isError && input.isEmpty() && stack.isEmpty())
         {
             System.out.println(sInput.get(sInput.size()-1)+" - Accept");
             sInput.add(" - ACCEPT \n");
         }
         else
         {
             System.out.println(sInput.get(sInput.size()-1)+" - Reject");
             sInput.add(" - REJECT \n");
         }
     }//end class


}    

