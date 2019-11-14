package sv.com.registroacademico.service;

import javax.ejb.Stateless;
import javax.inject.Inject;

import sv.com.registroacademico.config.BaseRepository;
import sv.com.registroacademico.config.BaseService;
import sv.com.registroacademico.model.CgMetaTipoSangre;
import sv.com.registroacademico.repository.CgMetaTipoSangreRepository;

@Stateless
public class CgMetaTipoSangreService extends BaseService<CgMetaTipoSangre, Long> {

	@Inject
	private CgMetaTipoSangreRepository cgMetaTipoSangreRepository;

	@Override
	public BaseRepository<CgMetaTipoSangre, Long> getRepository() {
		return cgMetaTipoSangreRepository;
	}

}
