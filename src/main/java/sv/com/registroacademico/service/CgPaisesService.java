package sv.com.registroacademico.service;

import javax.ejb.Stateless;
import javax.inject.Inject;

import sv.com.registroacademico.config.BaseRepository;
import sv.com.registroacademico.config.BaseService;
import sv.com.registroacademico.model.CgPaises;
import sv.com.registroacademico.repository.CgPaisesRepository;

@Stateless
public class CgPaisesService extends BaseService<CgPaises, Long> {

	@Inject
	private CgPaisesRepository cgPaisesRepository;

	@Override
	public BaseRepository<CgPaises, Long> getRepository() {
		return cgPaisesRepository;
	}

}
