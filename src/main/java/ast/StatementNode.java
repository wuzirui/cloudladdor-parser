package ast;

import lexer.CL;
import org.antlr.v4.runtime.Token;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StatementNode implements ASTNode {
    private List<ASTNode> children = null;

    @Override
    public int getType() {
        return -3;
    }

    @Override
    public List<ASTNode> getChildren() {
        return children;
    }

    @Override
    public void parse(CL lexer) {
        final List<Integer> r1 = List.of(CL.If);
        final List<Integer> r2 = List.of(CL.Let);
        final List<Integer> r3 = List.of(CL.LBrace);
        final List<Integer> r4 = List.of(CL.Break);
        final List<Integer> r5 = List.of(CL.Continue);
        final List<Integer> r6 = Arrays.asList(CL.For, CL.While);
        final List<Integer> r7 = List.of(CL.Return);
        final List<Integer> r8 = Arrays.asList(CL.StringLiteral, CL.BoolLiteral, CL.NumberLiteral,
                CL.LBrack, CL.LParen, CL.Identifier);
        children = new ArrayList<>();
        Token next = lexer.getToken();

        if (r1.contains(next.getType())) {
            children.add(new SelectionStatementNode());
        }
        else if (r2.contains(next.getType())) {
            children.add(new DataStatementNode());
            children.add(new TokenNode(CL.Semi));
        }
        else if (r3.contains(next.getType())) {
            children.add(new CompoundStatementNode());
        }
        else if (r4.contains(next.getType())) {
            children.add(new BreakStatementNode());
            children.add(new TokenNode(CL.Semi));
        }
        else if (r5.contains(next.getType())) {
            children.add(new ContinueStatementNode());
            children.add(new TokenNode(CL.Semi));
        }
        else if (r6.contains(next.getType())) {
            children.add(new IterationStatementNode());
        }
        else if (r7.contains(next.getType())) {
            children.add(new ReturnStatementNode());
            children.add(new TokenNode(CL.Semi));
        }
        else if (r8.contains(next.getType())) {
            children.add(new ExpressionStatementNode());
            children.add(new TokenNode(CL.Semi));
        }
        else {
            dispatchError(next);
        }

        printChildren(next);
        extendChildren(lexer);
    }
}
