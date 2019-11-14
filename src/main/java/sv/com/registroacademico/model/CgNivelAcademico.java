package sv.com.registroacademico.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import sv.com.registroacademico.config.BaseEntity;

@Entity
@Table(name = "CG_GRADOS", uniqueConstraints = { @UniqueConstraint(columnNames = "IDNIVEL") })
@EqualsAndHashCode(of = { "id" }, callSuper = false)
@ToString(of = { "id" })
@Getter
@Setter
public class CgNivelAcademico implements BaseEntity<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3096627481406897159L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PK_NIVEL_ACADEMICO", nullable = false)
	private Long id;

	@Column(length = 2, nullable = false)
	private String idnivel;

	@Column(length = 40, nullable = false)
	private String denivel;

	@Column(nullable = false)
	private Integer orden;

	@Column(name = "NEG_ESCUELA", length = 2, nullable = false)
	private String negEscuela;
	
	@Column(length = 2, nullable = false)
	private String vigente;

}
