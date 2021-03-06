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
@Table(name = "CG_ESTADOFAM", uniqueConstraints = { @UniqueConstraint(columnNames = { "ESTADO_CIVIL" }) })
@EqualsAndHashCode(of = { "id" }, callSuper = false)
@ToString(of = { "id" })
@Getter
@Setter
public class CgEstadofam implements BaseEntity<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 750126420739417385L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PK_ESTADO_CIVIL", nullable = false)
	private Long id;

	@Column(name = "ESTADO_CIVIL", length = 4, nullable = false)
	private String estadoCivil;

	@Column(length = 30, nullable = false)
	private String destadocivil;

	@Column(length = 2, nullable = false)
	private String vigente;

}
