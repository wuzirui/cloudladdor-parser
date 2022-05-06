package ast;

import lexer.CL;

import java.util.List;

public class ContinueStatementNode implements ASTNode {
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

    }
}
