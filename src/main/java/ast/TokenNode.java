package ast;

import lexer.CL;

import java.util.List;

public class TokenNode implements ASTNode {
    private final int tokenType;

    public int getTokenType() {
        return tokenType;
    }

    public TokenNode(int tokenType) {
        this.tokenType = tokenType;
    }

    @Override
    public int getType() {
        return 0;
    }

    @Override
    public List<ASTNode> getChildren() {
        return null;
    }

    @Override
    public void parse(CL lexer) {
        if (lexer.getToken().getType() == tokenType) {
            System.out.println("Token accepted: " + lexer.getToken().getText());
            var next = lexer.nextToken();
            System.out.printf("Next Token: %s(text: %s)\n", CL.VOCABULARY.getSymbolicName(next.getType()),
                    next.getText());
        }
        else {
            System.out.printf("[ERROR] token type %s expected, got %s instead.\n",
                    CL.VOCABULARY.getSymbolicName(tokenType),
                    CL.VOCABULARY.getSymbolicName(lexer.getToken().getType()));
            dispatchError(lexer.getToken());
            throw new RuntimeException();
        }
    }
}
