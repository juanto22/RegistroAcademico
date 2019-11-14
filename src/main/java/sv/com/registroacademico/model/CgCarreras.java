package sv.com.registroacademico.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import sv.com.registroacademico.config.BaseEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "CG_CARRERAS", uniqueConstraints = { @UniqueConstraint(columnNames = { "IDCARRERA" }) })
@EqualsAndHashCode(of = { "id" }, callSuper = false)
@ToString(of = { "id" })
@Getter
@Setter
public class CgCarreras implements BaseEntity<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3096627481406897159L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PK_CARRERA", nullable = false)
	private Long id;

	@Column(nullable = false)
	private String idcarrera;

	@Column(length = 60, nullable = false)
	private String decarrera;

	@Column(length = 2, nullable = false)
	private String vigente;

	@ManyToOne
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumns({ @JoinColumn(name = "GRADO_ID", referencedColumnName = "GRADO", nullable = false) })
	private CgGrados cgGrados;

	@ManyToOne
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumns({ @JoinColumn(name = "AREA_KNOWLEDGE_ID", referencedColumnName = "IDCONOCIMIENTO", nullable = false) })
	private CgAreaConocimiento cgAreaConocimiento;

}
