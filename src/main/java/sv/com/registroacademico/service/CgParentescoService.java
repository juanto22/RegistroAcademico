package sv.com.registroacademico.service;

import javax.ejb.Stateless;
import javax.inject.Inject;

import sv.com.registroacademico.config.BaseRepository;
import sv.com.registroacademico.config.BaseService;
import sv.com.registroacademico.model.CgParentesco;
import sv.com.registroacademico.repository.CgParentescoRepository;

@Stateless
public class CgParentescoService extends BaseService<CgParentesco, Long> {

	@Inject
	private CgParentescoRepository cgParentescoRepository;

	@Override
	public BaseRepository<CgParentesco, Long> getRepository() {
		return cgParentescoRepository;
	}

}
