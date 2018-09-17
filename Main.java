import java.io.*;

public class Main{ 
    public static void main(String argv[]){
        try{
            parser p = new parser(new Lexer(new FileReader(argv[0])));
            Object result = p.parse().value;
        }catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Main: file not specified.");
        }catch(FileNotFoundException e){
            System.out.println("Main: no file found.");
        }catch(Exception e){
            System.out.println("Main: Unknown fatal error.");
            e.printStackTrace();
        }
    }
}