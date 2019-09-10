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
@Table(name = "CG_ESTADO_PERSONA", uniqueConstraints = { @UniqueConstraint(columnNames = { "IDESTADOPER" }) })
@EqualsAndHashCode(of = { "id" }, callSuper = false)
@ToString(of = { "id" })
@Getter
@Setter
public class CgEstadoPersona implements BaseEntity<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6375495536635084382L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PK_ESTADOPER", nullable = false)
	private Long id;

	@Column(length = 2, nullable = false)
	private String idEstadoPer;

	@Column(length = 50, nullable = false)
	private String deEstado;

	@Column(length = 2, nullable = false)
	private String enplanilla;

	@Column(name = "ESTADO_INICIAL", length = 2, nullable = false)
	private String estadoInicial;

	@Column(name = "PENSION_TMP", length = 2, nullable = false)
	private String pensionTmp;

	@Column(name = "PERMITE_ALTAS", length = 2, nullable = false)
	private String permiteAltas;

	@Column(name = "VIGENTE", length = 2, nullable = false)
	private String vigente;

}
