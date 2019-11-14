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
@Table(name = "CG_GRADOS")
@EqualsAndHashCode(of = { "id" }, callSuper = false)
@ToString(of = { "id" })
@Getter
@Setter
public class CgParentesco implements BaseEntity<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3096627481406897159L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PK_CPER_PARENTESCO", nullable = false)
	private Long id;

	@Column(length = 30, nullable = false)
	private String deparentesco;

	@Column(length = 2, nullable = false)
	private String padre;

	@Column(length = 2, nullable = false)
	private String madre;

	@Column(length = 2, nullable = false)
	private String vigente;

}
