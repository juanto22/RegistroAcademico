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
@Table(name = "CGMETA_GENERO", uniqueConstraints = { @UniqueConstraint(columnNames = { "IDGENERO" }) })
@EqualsAndHashCode(of = { "id" }, callSuper = false)
@ToString(of = { "id" })
@Getter
@Setter
public class CgMetaGenero implements BaseEntity<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3096627481406897159L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PKGENERO", nullable = false)
	private Long id;

	@Column(length = 2, nullable = false)
	private String idgenero;

	@Column(length = 50, nullable = false)
	private String degenero;

	@Column(length = 2, nullable = false)
	private String vigente;

}
