package me.tomassetti.symbolsolver.model.javassist;

import javassist.CtField;
import me.tomassetti.symbolsolver.model.TypeSolver;
import me.tomassetti.symbolsolver.model.declarations.TypeDeclaration;

/**
 * Created by federico on 01/08/15.
 */
public class JavassistFieldDeclaration implements me.tomassetti.symbolsolver.model.declarations.FieldDeclaration {
    public JavassistFieldDeclaration(CtField ctField, TypeSolver typeSolver) {
        this.ctField = ctField;
        this.typeSolver = typeSolver;
    }

    private CtField ctField;
    private TypeSolver typeSolver;

    @Override
    public TypeDeclaration getType(TypeSolver typeSolver) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getName() {
        return ctField.getName();
    }

    @Override
    public boolean isField() {
        return true;
    }

    @Override
    public boolean isParameter() {
        return false;
    }

    @Override
    public boolean isVariable() {
        return false;
    }

    @Override
    public boolean isType() {
        return false;
    }

    @Override
    public boolean isClass() {
        return false;
    }

    @Override
    public boolean isInterface() {
        return false;
    }
}
