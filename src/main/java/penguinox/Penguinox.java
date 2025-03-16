package penguinox;



import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;



public class Penguinox {
    static boolean hadError = false;
    public static void main(String[] args) throws IOException {
        if(args.length > 1){
            System.out.println("Usage: penguinox [script.pngnx]");
            System.exit(64);
        } else if(args.length == 1){
            runFile(args[0]);
        } else {
            runPrompt();
        }
    }

    private static void runFile(String path) throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get(path));
        run(new String(bytes, Charset.defaultCharset()));

        if(hadError) System.exit(65);

      }

    private static void runPrompt() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while(true){
            System.out.print("> ");
            String line = reader.readLine();
            if(line == null) break;

            run(line);

            hadError = false;
        }
    }

    private static void run(String source) {

        Tokenizer tokenizer = new Tokenizer(source);
        List<Token> tokens = tokenizer.scanTokens();

        for(Token token: tokens){
            System.out.println(token);
        }
        // for(String line: source.split("\n")){
        //     System.out.println(line);
        // }

    }

    static void error(int line, String message){
        report(line, "", message);
    }

    private static void report(int line, String where, String message){
        System.err.println("[line " + line + "] Error" + where + ": " + message);
    }
}
