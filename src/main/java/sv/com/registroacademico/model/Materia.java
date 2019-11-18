package sv.com.registroacademico.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import sv.com.registroacademico.config.BaseEntity;

@Entity
@Table(name = "MATERIA", uniqueConstraints = { @UniqueConstraint(columnNames = { "PK_EMPRESA", "IDMATERIA" }) })
@EqualsAndHashCode(of = { "id" }, callSuper = false)
@ToString(of = { "id" })
@Getter
@Setter
public class Materia implements BaseEntity<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5528738108299928155L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PK_MATERIA", nullable = false)
	private Long id;

	@Column(length = 7, nullable = false)
	@NotNull
	private String idmateria;

	@Column(name = "NOMBRE_MATERIA", length = 60, nullable = false)
	@NotNull
	private String nombreMateria;

	@Column(name = "MAT_ACUMULADORA", length = 7)
	private String matAcumuladora;

	@Column(name = "ORDEN_IMPRESION")
	private Integer ordenImpresion;

	@Column(name = "PESO_PORCENTUAL")
	private Double pesoPorcentual;

	@Column(name = "VIGENTE_DESDE", nullable = false)
	@Temporal(TemporalType.DATE)
	@NotNull
	private Date vigenteDesde;

	@Column(name = "VIGENTE_HASTA")
	@Temporal(TemporalType.DATE)
	private Date vigenteHasta;

	@ManyToOne
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumns({ @JoinColumn(name = "PK_EMPRESA", referencedColumnName = "PK_EMPRESA", nullable = false) })
	@NotNull
	private Empresa empresa;

	@ManyToOne
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumns({
			@JoinColumn(name = "SK_AREA_KNOWLEDGE", referencedColumnName = "PK_AREA_KNOWLEDGE", nullable = false) })
	@NotNull
	private CgAreaConocimiento cgAreaConocimiento;

	@ManyToOne
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumns({ @JoinColumn(name = "TIPOMATERIA_ID", referencedColumnName = "PK_TIPOMATERIA", nullable = false) })
	@NotNull
	private CgTipoMateria cgTipoMateria;

}
