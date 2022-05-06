package ast;

import lexer.CL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ObjItemList implements ASTNode {
    private List<ASTNode> children = new ArrayList<>();
    @Override
    public int getType() {
        return -11;
    }

    @Override
    public List<ASTNode> getChildren() {
        return children;
    }

    @Override
    public void parse(CL lexer) {
        var next = lexer.getToken();
        var r1 = Arrays.asList(CL.Identifier, CL.StringLiteral, CL.LBrack);
        var r2 = List.of(CL.RBrace);
        if (r1.contains(next.getType())) {
            children.add(new ObjItemNode());
            children.add(new ObjListTailNode());
        }
        else {
            return;
        }

        printChildren(next);
        extendChildren(lexer);
    }
}
