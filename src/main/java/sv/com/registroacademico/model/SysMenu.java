package sv.com.registroacademico.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import sv.com.registroacademico.config.BaseEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "SYS_MENU")
@EqualsAndHashCode(of = { "id" }, callSuper = false)
@ToString(of = { "id" })
@Getter
@Setter
public class SysMenu implements BaseEntity<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3110247754069731993L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PK_MENU", nullable = false)
	private Long id;

	@Column(name = "DEPENDE_DE")
	private Long dependeDe;

	private String descripcion;

	private String accion;

	private int orden;

	private String activa;

}
