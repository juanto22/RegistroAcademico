package sv.com.registroacademico.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.mysema.query.types.expr.BooleanExpression;

import sv.com.registroacademico.config.BaseRepository;
import sv.com.registroacademico.config.BaseService;
import sv.com.registroacademico.model.CgAreaConocimiento;
import sv.com.registroacademico.model.QCgAreaConocimiento;
import sv.com.registroacademico.repository.CgAreaConocimientoRepository;

@Stateless
public class CgAreaConocimientoService extends BaseService<CgAreaConocimiento, Long> {

	@Inject
	private CgAreaConocimientoRepository cgAreaConocimientoRepository;

	private static final QCgAreaConocimiento qCgAreaConocimiento = QCgAreaConocimiento.cgAreaConocimiento;

	@Override
	public BaseRepository<CgAreaConocimiento, Long> getRepository() {
		return cgAreaConocimientoRepository;
	}

	public List<CgAreaConocimiento> findMateriasByVigencia(String vigente) {
		BooleanExpression byVigencia = qCgAreaConocimiento.vigente.eq(vigente);
		return newJpaQuery().from(qCgAreaConocimiento).where(byVigencia).list(qCgAreaConocimiento);
	}

}
