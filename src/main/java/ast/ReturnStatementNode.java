package ast;

import lexer.CL;

import java.util.ArrayList;
import java.util.List;

public class ReturnStatementNode implements ASTNode {
    private List<ASTNode> children = new ArrayList<>();

    @Override
    public int getType() {
        return -28;
    }

    @Override
    public List<ASTNode> getChildren() {
        return this.children;
    }

    @Override
    public void parse(CL lexer) {
        var next = lexer.getToken();
        children.add(new TokenNode(CL.Return));
        children.add(new ExpressionNode());
        printChildren(next);
        extendChildren(lexer);
    }
}
