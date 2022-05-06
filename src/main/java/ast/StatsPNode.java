package ast;

import lexer.CL;

import java.util.ArrayList;
import java.util.List;

public class StatsPNode implements ASTNode {
    private List<ASTNode> children = new ArrayList<>();

    @Override
    public int getType() {
        return -26;
    }

    @Override
    public List<ASTNode> getChildren() {
        return this.children;
    }

    @Override
    public void parse(CL lexer) {
        var next = lexer.getToken();
        if (next.getType() == CL.Comma) {
            children.add(new TokenNode(CL.Comma));
            children.add(new StatementNode());
            children.add(new StatsPNode());
        }
        else {
            return;
        }
        printChildren(next);
        extendChildren(lexer);
    }
}
