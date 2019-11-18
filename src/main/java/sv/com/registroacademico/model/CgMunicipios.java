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
@Table(name = "CG_MUNICIPIOS", uniqueConstraints = { @UniqueConstraint(columnNames = { "IDMUNICIPIO" }) })
@EqualsAndHashCode(of = { "id" }, callSuper = false)
@ToString(of = { "id" })
@Getter
@Setter
public class CgMunicipios implements BaseEntity<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3268507161787681773L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PK_MUNICIPIOS", nullable = false)
	private Long id;

	@Column(length = 4, nullable = false)
	private String idmunicipio;

	@Column(length = 60, nullable = false)
	private String demunicipio;

	@Column(length = 2, nullable = false)
	private String vigente;

	@ManyToOne
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumns({ @JoinColumn(name = "PARENT_IDMUNICIPIO", referencedColumnName = "IDMUNICIPIO") })
	private CgMunicipios cgMunicipios;

	@ManyToOne
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumns({ @JoinColumn(name = "IDPAIS", referencedColumnName = "IDPAIS", nullable = false) })
	private CgPaises cgPaises;

}
