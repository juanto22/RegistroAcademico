package sv.com.registroacademico.service;

import javax.ejb.Stateless;
import javax.inject.Inject;

import sv.com.registroacademico.config.BaseRepository;
import sv.com.registroacademico.config.BaseService;
import sv.com.registroacademico.model.CgGrados;
import sv.com.registroacademico.repository.CgGradosRepository;

@Stateless
public class CgGradosService extends BaseService<CgGrados, Long> {

	@Inject
	private CgGradosRepository cgGradosRepository;

	@Override
	public BaseRepository<CgGrados, Long> getRepository() {
		return cgGradosRepository;
	}

}
