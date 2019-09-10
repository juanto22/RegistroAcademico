package sv.com.registroacademico.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import sv.com.registroacademico.config.BaseEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "CG_AREA_CONOCIMIENTO", uniqueConstraints = { @UniqueConstraint(columnNames = { "IDCONOCIMIENTO" }) })
@EqualsAndHashCode(of = { "id" }, callSuper = false)
@ToString(of = { "id" })
@Getter
@Setter
public class CgAreaConocimiento implements BaseEntity<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2227714787734101538L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PK_AREA_KNOWLEDGE", nullable = false)
	private Long id;

	@Column(length = 5, nullable = false)
	private String idConocimiento;

	@Column(length = 40, nullable = false)
	private String descripcion;

	@Column(name = "VIGENTE", length = 2, nullable = false)
	private String vigente;

}
