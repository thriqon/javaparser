package me.tomassetti.symbolsolver.model.declarations;

import com.github.javaparser.ast.Node;
import me.tomassetti.symbolsolver.model.Context;
import me.tomassetti.symbolsolver.model.SymbolReference;
import me.tomassetti.symbolsolver.model.TypeSolver;
import me.tomassetti.symbolsolver.model.usages.MethodUsage;
import me.tomassetti.symbolsolver.model.usages.TypeUsage;
import me.tomassetti.symbolsolver.model.usages.TypeUsageOfTypeDeclaration;

import java.util.List;
import java.util.Optional;

/**
 * @author Federico Tomassetti
 */
public interface TypeDeclaration extends Declaration, TypeParametrized {
    String getQualifiedName();
    Context getContext();

    default SymbolReference<MethodDeclaration> solveMethod(String name, List<TypeUsage> parameterTypes, TypeSolver typeSolver) {
        return getContext().solveMethod(name, parameterTypes, typeSolver);
    }

    /**
     * Get how the type is used in the given context.
     * @param node
     * @return
     */
    TypeUsage getUsage(Node node);

    boolean isAssignableBy(TypeUsage typeUsage, TypeSolver typeSolver);

    boolean isTypeVariable();

    default boolean isArray() {
        return false;
    }

    FieldDeclaration getField(String name);

    boolean hasField(String name);

    default Optional<MethodUsage> solveMethodAsUsage(String name, List<TypeUsage> parameterTypes, TypeSolver typeSolver, Context invokationContext) {
        return getContext().solveMethodAsUsage(name, parameterTypes, typeSolver);
    }

    default boolean canBeAssignedBy(TypeDeclaration other, TypeSolver typeSolver) {
        return isAssignableBy(new TypeUsageOfTypeDeclaration(other), typeSolver);
    }

    SymbolReference<? extends ValueDeclaration> solveSymbol(String substring, TypeSolver typeSolver);

    /**
     * Try to solve a symbol just in the declaration, it does not delegate to the container.
     * @param substring
     * @param typeSolver
     * @return
     */
    SymbolReference<TypeDeclaration> solveType(String substring, TypeSolver typeSolver);

    List<TypeDeclaration> getAllAncestors(TypeSolver typeSolver);

    default boolean isPrimitive() {
        return false;
    }
}
