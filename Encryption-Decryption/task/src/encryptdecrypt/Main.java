package encryptdecrypt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String command = "";
        Integer key = -99;
        String line = "";
        String in = "";
        String out = "";
        String alg = "";

        for (int i = 0; i < args.length; i++) {
            if ("-mode".equals(args[i]) && (i+1)<args.length){
                command = args[i+1];
            } else if ("-key".equals(args[i]) && (i+1)<args.length){
                key = Integer.parseInt(args[i+1]);
            }if ("-data".equals(args[i]) && (i+1)<args.length){
                line = args[i+1];
            }else if ("-in".equals(args[i]) && (i+1)<args.length){
                in = args[i+1];
            }else if ("-out".equals(args[i]) && (i+1)<args.length){
                out = args[i+1];
            }else if ("-alg".equals(args[i]) && (i+1)<args.length){
                alg = args[i+1];
            }
        }
        //
        Scanner scanner = new Scanner(System.in);
        if (command.isEmpty()){command = "enc";}
        if (line.isEmpty()) {
            if (in.isEmpty()) {
            line = scanner.nextLine();} else{
            try(BufferedReader reader = new BufferedReader(new FileReader(new File(in)))){
                line = reader.readLine();
            } catch (Exception e){
                System.out.println("Can't read from specified file!");
            }
        }
        }

        if (key ==-99) { key = scanner.nextInt();}
        String message = "";
        if ("enc".equals(command)) {
            //message = encrypt(line,key);}
            message = Encrypt.encrypt(line, key, alg);}
        else if ("dec".equals(command)) {
            //message = decrypt(line,key);}
            message = Decrypt.decrypt(line, key, alg);}
        if (out.isEmpty()) {
            System.out.println(message);
        } else {
            try (FileWriter writer = new FileWriter(out)){
                writer.write(message);
            } catch (Exception e){
                System.out.println("Can't write to specified file!");
            }
        }

    }

    public static String encrypt (String line, Integer key){
        String message="";
        for (char el: line.toCharArray()){
            int ind = (int)el;
            if (ind >31 && ind<127) {
                message +=ind+key<127?(char)(ind+key):(char)(ind+key-95);
            } else message +=el;
        }
        return message;
    }

    public static String decrypt (String line, Integer key){
        String message="";
        for (char el: line.toCharArray()){
            int ind = (int)el;
            if (ind >31 && ind<127) {
                message +=ind-key>31?(char)(ind-key):(char)(ind-key+95);
            } else message +=el;
        }
        return message;
    }
}