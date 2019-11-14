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
public class Empresa implements BaseEntity<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3096627481406897159L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PK_EMPRESA", nullable = false)
	private Long id;

	@Column(length = 80, nullable = false)
	private String deempresa;

	@Column(length = 14)
	private String nronit;

	@Column(length = 20)
	private String nroissspatronal;

	@Column(name = "ANNIO_ESCOLAR")
	private Integer annioEscolar;

	@Column(length = 10)
	private String infraestructura;

	@Column(name = "FECHA_REGISTRO")
	@Temporal(TemporalType.DATE)
	private Date fechaRegistro;

	@Column(length = 2, nullable = false)
	private String activo;

	@Column(length = 100)
	private String slogan;

	@Column(length = 100)
	private String logotipo;

	@ManyToOne
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumns({ @JoinColumn(name = "MUNICIPIO_ID", referencedColumnName = "PK_MUNICIPIOS", nullable = false) })
	private CgMunicipios cgMunicipios;

}
