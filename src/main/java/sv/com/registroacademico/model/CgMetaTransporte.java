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
@Table(name = "CGMETA_TRANSPORTE")
@EqualsAndHashCode(of = { "id" }, callSuper = false)
@ToString(of = { "id" })
@Getter
@Setter
public class CgMetaTransporte implements BaseEntity<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5988858111351387506L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PK_TRANSPORTE", nullable = false)
	private Long id;

	@Column(name = "DE_TRANSPORTE", length = 50, nullable = false)
	private String deTransporte;

	@Column(length = 2, nullable = false)
	private String vigente;

}
