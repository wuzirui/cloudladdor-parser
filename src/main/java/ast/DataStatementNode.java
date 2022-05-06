package ast;

import lexer.CL;

import java.util.ArrayList;
import java.util.List;

public class DataStatementNode implements ASTNode {
    private List<ASTNode> children = null;

    @Override
    public int getType() {
        return -6;
    }

    @Override
    public List<ASTNode> getChildren() {
        return children;
    }

    @Override
    public void parse(CL lexer) {
        children = new ArrayList<>();
        children.add(new TokenNode(CL.Let));
        children.add(new TokenNode(CL.Identifier));
        children.add(new TokenNode(CL.Assign));
        children.add(new ExpressionNode());
        children.add(new DataItemListNode());
        printChildren(lexer.getToken());
        extendChildren(lexer);
    }
}
