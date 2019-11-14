package sv.com.registroacademico.service;

import javax.ejb.Stateless;
import javax.inject.Inject;

import sv.com.registroacademico.config.BaseRepository;
import sv.com.registroacademico.config.BaseService;
import sv.com.registroacademico.model.CgEstadofam;
import sv.com.registroacademico.repository.CgEstadofamRepository;

@Stateless
public class CgEstadofamService extends BaseService<CgEstadofam, Long> {

	@Inject
	private CgEstadofamRepository cgEstadofamRepository;

	@Override
	public BaseRepository<CgEstadofam, Long> getRepository() {
		return cgEstadofamRepository;
	}

}
