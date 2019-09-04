package sv.com.registroacademico.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QEmpleado is a Querydsl query type for Empleado
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QEmpleado extends EntityPathBase<Empleado> {

    private static final long serialVersionUID = 1714813729L;

    public static final QEmpleado empleado = new QEmpleado("empleado");

    public final StringPath cargo = createString("cargo");

    public final StringPath codigo = createString("codigo");

    public final DatePath<java.util.Date> fechaContratacion = createDate("fechaContratacion", java.util.Date.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath nombre = createString("nombre");

    public final NumberPath<Integer> salario = createNumber("salario", Integer.class);

    public QEmpleado(String variable) {
        super(Empleado.class, forVariable(variable));
    }

    public QEmpleado(Path<? extends Empleado> path) {
        super(path.getType(), path.getMetadata());
    }

    public QEmpleado(PathMetadata<?> metadata) {
        super(Empleado.class, metadata);
    }

}

