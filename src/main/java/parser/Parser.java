package parser;

import ast.ASTNode;
import ast.ProgramNode;
import ast.TokenNode;
import lexer.CL;
import org.antlr.v4.runtime.CharStreams;

import java.io.IOException;

public class Parser {
    private CL lexer;

    public static Parser fromString(String src) {
        return new Parser(new CL(CharStreams.fromString(src)));
    }

    public static Parser fromFilename(String filename) throws IOException {
        return new Parser(new CL(CharStreams.fromFileName(filename)));
    }

    public Parser(CL lexer) {
        this.lexer = lexer;
    }


    public ASTNode parse() {
        ASTNode root = new ProgramNode();
        root.parse(lexer);
        return root;
    }

    public static String renderNodeToDotString(ASTNode node) {
        if (node instanceof TokenNode) {
            return CL.VOCABULARY.getSymbolicName(((TokenNode) node).getTokenType()) + " @" + node.hashCode();
        }
        return node.getClass().getSimpleName() + " @" + node.hashCode();
    }
}
