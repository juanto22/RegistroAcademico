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
@Table(name = "CG_TIPOMATERIA", uniqueConstraints = { @UniqueConstraint(columnNames = { "TIPO_MATERIA" }) })
@EqualsAndHashCode(of = { "id" }, callSuper = false)
@ToString(of = { "id" })
@Getter
@Setter
public class CgTipoMateria implements BaseEntity<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5363612795583516212L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PK_TIPOMATERIA", nullable = false)
	private Long id;

	@Column(name = "TIPO_MATERIA", length = 2, nullable = false)
	private String tipoMateria;

	@Column(name = "DETIPO_MATERIA", length = 25, nullable = false)
	private String detipoMateria;

	@Column(length = 2, nullable = false)
	private String vigente;

}
