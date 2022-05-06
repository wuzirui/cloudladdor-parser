package ast;

import lexer.CL;

import java.util.ArrayList;
import java.util.List;

public class DataItemListNode implements ASTNode {
    private List<ASTNode> children = new ArrayList<>();
    @Override
    public int getType() {
        return -7;
    }

    @Override
    public List<ASTNode> getChildren() {
        return children;
    }

    @Override
    public void parse(CL lexer) {
        var next = lexer.getToken();
        if (next.getType() == CL.Semi) {
            return;
        }
        else if (next.getType() == CL.Comma) {
            children.add(new TokenNode(CL.Comma));
            children.add(new TokenNode(CL.Identifier));
            children.add(new TokenNode(CL.Assign));
            children.add(new ExpressionNode());
        }
        else {
            dispatchError(next);
        }
        printChildren(next);
        extendChildren(lexer);
    }
}
