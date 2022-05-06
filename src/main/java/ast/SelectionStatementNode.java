package ast;

import lexer.CL;

import java.util.ArrayList;
import java.util.List;

public class SelectionStatementNode implements ASTNode {
    private List<ASTNode> children = new ArrayList<>();

    @Override
    public int getType() {
        return -4;
    }

    @Override
    public List<ASTNode> getChildren() {
        return this.children;
    }

    @Override
    public void parse(CL lexer) {
        var next = lexer.getToken();
        children.add(new TokenNode(CL.If));
        children.add(new TokenNode(CL.LParen));
        children.add(new ExpressionNode());
        children.add(new TokenNode(CL.RParen));
        children.add(new StatementNode());
        printChildren(next);
        extendChildren(lexer);
    }
}
