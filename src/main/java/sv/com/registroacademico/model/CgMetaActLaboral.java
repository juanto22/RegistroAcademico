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
@Table(name = "CGMETA_ACT_LABORAL", uniqueConstraints = { @UniqueConstraint(columnNames = { "IDACTILABORAL" }) })
@EqualsAndHashCode(of = { "id" }, callSuper = false)
@ToString(of = { "id" })
@Getter
@Setter
public class CgMetaActLaboral implements BaseEntity<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3096627481406897159L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PK_ACTLABORAL", nullable = false)
	private Long id;

	@Column(length = 2, nullable = false)
	private String idactilaboral;

	@Column(length = 60, nullable = false)
	private String descripcion;

	@Column(length = 2, nullable = false)
	private String vigente;

}
