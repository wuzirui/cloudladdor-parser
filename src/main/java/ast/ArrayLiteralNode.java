package ast;

import lexer.CL;

import java.util.ArrayList;
import java.util.List;

public class ArrayLiteralNode implements ASTNode {
    private List<ASTNode> children = new ArrayList<>();

    @Override
    public int getType() {
        return -15;
    }

    @Override
    public List<ASTNode> getChildren() {
        return this.children;
    }

    @Override
    public void parse(CL lexer) {
        children.add(new TokenNode(CL.LBrack));
        children.add(new ArrayLiteralTailNode());
        printChildren(lexer.getToken());
        extendChildren(lexer);
    }
}
