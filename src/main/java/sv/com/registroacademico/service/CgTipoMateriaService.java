package sv.com.registroacademico.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.mysema.query.types.expr.BooleanExpression;

import sv.com.registroacademico.config.BaseRepository;
import sv.com.registroacademico.config.BaseService;
import sv.com.registroacademico.model.CgTipoMateria;
import sv.com.registroacademico.model.QCgTipoMateria;
import sv.com.registroacademico.repository.CgTipoMateriaRepository;

@Stateless
public class CgTipoMateriaService extends BaseService<CgTipoMateria, Long> {

	@Inject
	private CgTipoMateriaRepository cgTipoMateriaRepository;

	private static final QCgTipoMateria qCgTipoMateria = QCgTipoMateria.cgTipoMateria;

	@Override
	public BaseRepository<CgTipoMateria, Long> getRepository() {
		return cgTipoMateriaRepository;
	}

	public List<CgTipoMateria> findMateriasByVigencia(String vigente) {
		BooleanExpression byVigencia = qCgTipoMateria.vigente.eq(vigente);
		return newJpaQuery().from(qCgTipoMateria).where(byVigencia).list(qCgTipoMateria);
	}

}
