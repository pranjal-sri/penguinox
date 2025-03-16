package penguinox;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

public class Tokenizer {
    private final static HashMap<String, TokenType> keywords = new HashMap<String, TokenType>();


    private final String source;
    private final List<Token> tokens = new ArrayList<Token>();


    private int start = 0;
    private int current = 0;
    private int line = 1;

    static {    
        keywords.put("class", TokenType.CLASS);
        keywords.put("func", TokenType.FUNCTION);
        keywords.put("var", TokenType.VARIABLE);
        keywords.put("this", TokenType.THIS);
        keywords.put("super", TokenType.SUPER);

        keywords.put("if", TokenType.IF);
        keywords.put("else", TokenType.ELSE);
        keywords.put("for", TokenType.FOR);
        keywords.put("while", TokenType.WHILE);
        
        keywords.put("continue", TokenType.CONTINUE);
        keywords.put("break", TokenType.BREAK);
        keywords.put("return", TokenType.RETURN);

        keywords.put("true", TokenType.TRUE);
        keywords.put("false", TokenType.FALSE);

        keywords.put("and", TokenType.AND);
        keywords.put("or", TokenType.OR);
        keywords.put("not", TokenType.NOT);

        keywords.put("print", TokenType.PRINT);
        keywords.put("nil", TokenType.NIL);        
    }

    public Tokenizer(String source){
        this.source = source;
    }

    public List<Token> scanTokens(){
        while (!isAtEnd()){
            start = current;
            scanToken();
        }
        addToken(TokenType.EOF);
        return tokens;
    }

    private void scanToken(){
        if(isAtEnd()) return;
        
        char c = advance();
        switch(c){
            case '(': addToken(TokenType.LEFT_PARENTHESIS); break;
            case ')': addToken(TokenType.RIGHT_PARENTHESIS); break;
            case '{': addToken(TokenType.LEFT_BRACE); break;
            case '}': addToken(TokenType.RIGHT_BRACE); break;
            case ',': addToken(TokenType.COMMA); break;
            case '.': addToken(TokenType.DOT); break;
            case ';': addToken(TokenType.SEMICOLON); break;

            case '-': addToken(TokenType.MINUS); break;
            case '+': addToken(TokenType.PLUS); break;
            case '*': addToken(TokenType.STAR); break;
            case '%': addToken(TokenType.MODULUS); break;
            case '/': 
                if(match('/')){
                    while(!isAtEnd() && peek() != '\n') advance();
                } 
                else{
                    addToken(TokenType.SLASH);
                }
                break;
            

            case '!':
                if(match('=')){
                    addToken(TokenType.BANG_EQUAL);
                }
                else{
                    addToken(TokenType.BANG);
                }
                break;
            
            case '=':
                if(match('=')){
                    addToken(TokenType.EQUAL_EQUAL);
                }
                else{
                    addToken(TokenType.EQUAL);
                }
                break;

            case '>':
                if(match('=')){
                    addToken(TokenType.GREATER_EQUAL);
                }
                else{
                    addToken(TokenType.GREATER);
                }
                break;

            case '<':
                if(match('=')){
                    addToken(TokenType.LESS_EQUAL);
                }
                else{
                    addToken(TokenType.LESS);
                }
                break;
            
            case '"':
                consumeString();
                break;
                
            case ' ':
            case '\r':
            case '\t':
                break;
            case '\n':
                line++;
                break;
            
            default:
                if(isDigit(c)){
                    consumeNumber();
                }
                else if(isAlphaOrDigit(c)){
                    consumeIdentifier();
                }
                else{
                    Penguinox.error(line, "Unexpected character.");
                }
                break;
        }
    }

    private void consumeString(){
        while(!isAtEnd() && peek() != '"'){
            if(peek() == '\n') line++;
            advance();
        }

        if(isAtEnd()) {
            Penguinox.error(line, "Unterminated string.");
            return;
        }

        advance();
        String value = source.substring(start+1, current-1);
        addToken(TokenType.STRING, value);
    }

    private void consumeNumber(){
        while(!isAtEnd() && isDigit(peek())) advance();

        if(match('.')){
            if(!isDigit(peek())){
                Penguinox.error(line, "Unexpected character after '.': A fractional part to the number is expected.");
                return;
            }
            while(!isAtEnd() && isDigit(peek())) advance();
        }

        String value = source.substring(start, current);
        addToken(TokenType.NUMBER, Double.parseDouble(value));
    }

    private void consumeIdentifier(){
        while(!isAtEnd() && isAlphaOrDigit(peek()) ){
            advance();
        }

        String text = source.substring(start, current);

        if(keywords.containsKey(text)){
            addToken(keywords.get(text));
        }
        else{
            addToken(TokenType.IDENTIFIER, text);
        }
    }

    private void addToken(TokenType type){
        addToken(type, null);
    }

    private void addToken(TokenType type, Object literal){
        String tokenSpan = source.substring(start, current);
        Token token = new Token(type, tokenSpan, literal, line);
        tokens.add(token);
    }


    
    private boolean isAtEnd(){
        return current >= this.source.length();
    }

    private char advance(){
        // consumes the current character and returns it while moving forward
        if (isAtEnd()) throw new RuntimeException("Cannot advance past end of source");
        return source.charAt(current++);
    }

    private char peek(){
        // only consumes the current character and returns it without moving forward
        if(isAtEnd()) return '\0';
        return source.charAt(current);
    }

    private boolean match(char expected){
        if(!isAtEnd() && peek() == expected){
            current++;
            return true;
        }
        return false;
    }



    private boolean isDigit(char c){
        return c >= '0' && c <= '9';
    }

    private boolean isAlpha(char c){
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || c == '_';
    }

    private boolean isAlphaOrDigit(char c){
        return isAlpha(c) || isDigit(c);
    }
    
}
