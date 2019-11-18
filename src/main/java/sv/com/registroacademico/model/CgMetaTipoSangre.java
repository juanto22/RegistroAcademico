package sv.com.registroacademico.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import sv.com.registroacademico.config.BaseEntity;

@Entity
@Table(name = "CGMETA_TIPO_SANGRE")
@EqualsAndHashCode(of = { "id" }, callSuper = false)
@ToString(of = { "id" })
@Getter
@Setter
public class CgMetaTipoSangre implements BaseEntity<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6898493426758165785L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PK_TIPEO_SANGRE", nullable = false)
	private Long id;

	@Column(name = "DETIPO_SANGRE", length = 10, nullable = false)
	private String detipoSangre;

	@Column(length = 2, nullable = false)
	private String vigente;

}
