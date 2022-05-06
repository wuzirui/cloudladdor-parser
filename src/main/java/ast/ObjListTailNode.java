package ast;

import lexer.CL;

import java.util.ArrayList;
import java.util.List;

public class ObjListTailNode implements ASTNode {
    private List<ASTNode> children = new ArrayList<>();

    @Override
    public int getType() {
        return -14;
    }

    @Override
    public List<ASTNode> getChildren() {
        return this.children;
    }

    @Override
    public void parse(CL lexer) {
        var next = lexer.getToken();
        if (next.getType() == CL.RBrace) {
            return;
        }
        else if (next.getType() == CL.Comma) {
            children.add(new TokenNode(CL.Comma));
            children.add(new ObjItemNode());
            children.add(new ObjListTailNode());
        }

        printChildren(next);
        extendChildren(lexer);
    }
}
