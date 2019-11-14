package sv.com.registroacademico.service;

import javax.ejb.Stateless;
import javax.inject.Inject;

import sv.com.registroacademico.config.BaseRepository;
import sv.com.registroacademico.config.BaseService;
import sv.com.registroacademico.model.CgCarreras;
import sv.com.registroacademico.repository.CgCarrerasRepository;

@Stateless
public class CgCarrerasService extends BaseService<CgCarreras, Long> {

	@Inject
	private CgCarrerasRepository cgCarrerasRepository;

	@Override
	public BaseRepository<CgCarreras, Long> getRepository() {
		return cgCarrerasRepository;
	}

}
