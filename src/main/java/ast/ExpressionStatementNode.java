package ast;

import lexer.CL;

import java.util.ArrayList;
import java.util.List;

public class ExpressionStatementNode implements ASTNode {
    private List<ASTNode> children = new ArrayList<>();

    @Override
    public int getType() {
        return -5;
    }

    @Override
    public List<ASTNode> getChildren() {
        return this.children;
    }

    @Override
    public void parse(CL lexer) {
        var next = lexer.getToken();
        children.add(new PrimaryExpressionNode());
        children.add(new ExpressionTailNode());
        printChildren(next);
        extendChildren(lexer);
    }
}
