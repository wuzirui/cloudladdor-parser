package ast;

import lexer.CL;

import java.util.ArrayList;
import java.util.List;

public class ObjPostfixNode implements ASTNode {
    private List<ASTNode> children = new ArrayList<>();
    @Override
    public int getType() {
        return -13;
    }

    @Override
    public List<ASTNode> getChildren() {
        return children;
    }

    @Override
    public void parse(CL lexer) {
        var next = lexer.getToken();
        if (next.getType() == CL.Colon) {
            children.add(new TokenNode(CL.Colon));
            children.add(new ExpressionNode());
        }
        else {
            return;
        }

        printChildren(next);
        extendChildren(lexer);
    }
}
