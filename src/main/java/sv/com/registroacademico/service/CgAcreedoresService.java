package sv.com.registroacademico.service;

import javax.ejb.Stateless;
import javax.inject.Inject;

import sv.com.registroacademico.config.BaseRepository;
import sv.com.registroacademico.config.BaseService;
import sv.com.registroacademico.model.CgAcreedores;
import sv.com.registroacademico.repository.CgAcreedoresRepository;

@Stateless
public class CgAcreedoresService extends BaseService<CgAcreedores, Long> {

	@Inject
	private CgAcreedoresRepository cgAcreedoresRepository;

	@Override
	public BaseRepository<CgAcreedores, Long> getRepository() {
		return cgAcreedoresRepository;
	}

}
