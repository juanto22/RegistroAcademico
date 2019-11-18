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

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import sv.com.registroacademico.config.BaseEntity;

@Entity
@Table(name = "CG_GRADOS", uniqueConstraints = { @UniqueConstraint(columnNames = { "GRADO" }) })
@EqualsAndHashCode(of = { "id" }, callSuper = false)
@ToString(of = { "id" })
@Getter
@Setter
public class CgGrados implements BaseEntity<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7511759043242852105L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PK_GRADOS", nullable = false)
	private Long id;

	@ManyToOne
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumns({ @JoinColumn(name = "NIVEL_ACADEMICO_ID", referencedColumnName = "IDNIVEL", nullable = false) })
	private CgNivelAcademico cgNivelAcademico;

	@Column(length = 12, nullable = false)
	private String grado;

	@Column(length = 50, nullable = false)
	private String degrado;

	@Column(length = 3)
	private String idciclo;

	@Column(nullable = false)
	private Integer ordengrado;

	@Column(length = 2, nullable = false)
	private String vigente;

}
