package sv.com.registroacademico.service;

import javax.ejb.Stateless;
import javax.inject.Inject;

import sv.com.registroacademico.config.BaseRepository;
import sv.com.registroacademico.config.BaseService;
import sv.com.registroacademico.model.CgEstadoPersona;
import sv.com.registroacademico.repository.CgEstadoPersonaRepository;

@Stateless
public class CgEstadoPersonaService extends BaseService<CgEstadoPersona, Long> {

	@Inject
	private CgEstadoPersonaRepository cgEstadoPersonaRepository;

	@Override
	public BaseRepository<CgEstadoPersona, Long> getRepository() {
		return cgEstadoPersonaRepository;
	}

}
