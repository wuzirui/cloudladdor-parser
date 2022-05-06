package ast;

import lexer.CL;

import java.util.ArrayList;
import java.util.List;

public class CompoundStatementNode implements ASTNode {
    private List<ASTNode> children = new ArrayList<>();

    @Override
    public int getType() {
        return -24;
    }

    @Override
    public List<ASTNode> getChildren() {
        return this.children;
    }

    @Override
    public void parse(CL lexer) {
        var next = lexer.getToken();
        children.add(new TokenNode(CL.LBrace));
        children.add(new StatsNode());
        children.add(new TokenNode(CL.RBrace));
        printChildren(next);
        extendChildren(lexer);
    }
}
