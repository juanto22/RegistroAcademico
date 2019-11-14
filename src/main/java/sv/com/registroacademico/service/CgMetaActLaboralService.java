package sv.com.registroacademico.service;

import javax.ejb.Stateless;
import javax.inject.Inject;

import sv.com.registroacademico.config.BaseRepository;
import sv.com.registroacademico.config.BaseService;
import sv.com.registroacademico.model.CgMetaActLaboral;
import sv.com.registroacademico.repository.CgMetaActLaboralRepository;

@Stateless
public class CgMetaActLaboralService extends BaseService<CgMetaActLaboral, Long> {

	@Inject
	private CgMetaActLaboralRepository cgMetaActLaboralRepository;

	@Override
	public BaseRepository<CgMetaActLaboral, Long> getRepository() {
		return cgMetaActLaboralRepository;
	}

}
