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
@Table(name = "CG_PAISES", uniqueConstraints = { @UniqueConstraint(columnNames = { "IDPAIS" }) })
@EqualsAndHashCode(of = { "id" }, callSuper = false)
@ToString(of = { "id" })
@Getter
@Setter
public class CgPaises implements BaseEntity<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2878619167188670119L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PK_CG_PAISES", nullable = false)
	private Long id;

	@Column(length = 4, nullable = false)
	private String idpais;

	@Column(length = 50, nullable = false)
	private String depais;

	@Column(nullable = false)
	private Integer iso3166;

	@Column(length = 2, nullable = false)
	private String elsalvador;

	@Column(length = 2, nullable = false)
	private String vigente;

}
