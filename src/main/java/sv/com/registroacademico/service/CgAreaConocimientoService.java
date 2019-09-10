package sv.com.registroacademico.service;

import javax.ejb.Stateless;
import javax.inject.Inject;

import sv.com.registroacademico.config.BaseRepository;
import sv.com.registroacademico.config.BaseService;
import sv.com.registroacademico.model.CgAreaConocimiento;
import sv.com.registroacademico.repository.CgAreaConocimientoRepository;

@Stateless
public class CgAreaConocimientoService extends BaseService<CgAreaConocimiento, Long> {

	@Inject
	private CgAreaConocimientoRepository cgAreaConocimientoRepository;

	@Override
	public BaseRepository<CgAreaConocimiento, Long> getRepository() {
		return cgAreaConocimientoRepository;
	}

}
