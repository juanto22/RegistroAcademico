package sv.com.registroacademico.service;

import javax.ejb.Stateless;
import javax.inject.Inject;

import sv.com.registroacademico.config.BaseRepository;
import sv.com.registroacademico.config.BaseService;
import sv.com.registroacademico.model.CgMetaGenero;
import sv.com.registroacademico.repository.CgMetaGeneroRepository;

@Stateless
public class CgMetaGeneroService extends BaseService<CgMetaGenero, Long> {

	@Inject
	private CgMetaGeneroRepository cgMetaGeneroRepository;

	@Override
	public BaseRepository<CgMetaGenero, Long> getRepository() {
		return cgMetaGeneroRepository;
	}

}
