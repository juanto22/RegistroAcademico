package sv.com.registroacademico.service;

import javax.ejb.Stateless;
import javax.inject.Inject;

import sv.com.registroacademico.config.BaseRepository;
import sv.com.registroacademico.config.BaseService;
import sv.com.registroacademico.model.CgMunicipios;
import sv.com.registroacademico.repository.CgMunicipiosRepository;

@Stateless
public class CgMunicipiosService extends BaseService<CgMunicipios, Long> {

	@Inject
	private CgMunicipiosRepository cgMunicipiosRepository;

	@Override
	public BaseRepository<CgMunicipios, Long> getRepository() {
		return cgMunicipiosRepository;
	}

}
