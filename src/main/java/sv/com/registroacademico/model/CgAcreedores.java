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
@Table(name = "CG_ACREEDORES", uniqueConstraints = { @UniqueConstraint(columnNames = { "NIT" }) })
@EqualsAndHashCode(of = { "id" }, callSuper = false)
@ToString(of = { "id" })
@Getter
@Setter
public class CgAcreedores implements BaseEntity<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3096627481406897159L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PK_ACREEDORES", nullable = false)
	private Long id;

	@Column(length = 14, nullable = false)
	private String nit;

	@Column(name = "NOMBRE_NIT", nullable = false)
	private String nombreNit;

	@Column(length = 2, nullable = false)
	private String esBanco;

	@Column(length = 2, nullable = false)
	private String esAfp;

	@Column(name = "ESADM_SALUD", length = 2, nullable = false)
	private String esAdmSalud;

	@Column(name = "CODIGO_AFP", length = 4)
	private String codigoAfp;

	@Column(name = "CODIGO_SALUD", length = 4)
	private String codigoSalud;

	@Column(name = "CODIGO_BANCO", length = 4)
	private String codigoBanco;

}
