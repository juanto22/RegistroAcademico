package sv.com.registroacademico.util;

import com.mysema.query.types.Path;
import com.mysema.query.types.path.SetPath;
import java.io.Serializable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(of = {"alias"})
public class JPAQueryJoin implements Serializable {

    private static final long serialVersionUID = -6846787987671741210L;

    SetPath entityPath;
    Path alias;
    boolean eagerFetch = false;

    public JPAQueryJoin(SetPath entityPath, Path alias, boolean eagerFetch) {
        this(entityPath, alias);
        this.eagerFetch = eagerFetch;
    }

    public JPAQueryJoin(SetPath entityPath, Path alias) {
        this.entityPath = entityPath;
        this.alias = alias;
    }

    @Override
    public String toString() {
        return "JPAQueryJoin{" + "entityPath=" + entityPath + ", alias=" + alias + ", eagerFetch=" + eagerFetch + '}';
    }
}
