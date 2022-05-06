package ast;

import lexer.CL;

import java.util.ArrayList;
import java.util.List;

public class ExpListTailNode implements ASTNode {
    private List<ASTNode> children = new ArrayList<>();

    @Override
    public int getType() {
        return -18;
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
            children.add(new ExpListNode());
        }
        else {
            return;
        }
        printChildren(next);
        extendChildren(lexer);
    }
}
