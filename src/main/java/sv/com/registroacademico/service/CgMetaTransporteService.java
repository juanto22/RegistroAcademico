package sv.com.registroacademico.service;

import javax.ejb.Stateless;
import javax.inject.Inject;

import sv.com.registroacademico.config.BaseRepository;
import sv.com.registroacademico.config.BaseService;
import sv.com.registroacademico.model.CgMetaTransporte;
import sv.com.registroacademico.repository.CgMetaTransporteRepository;

@Stateless
public class CgMetaTransporteService extends BaseService<CgMetaTransporte, Long> {

	@Inject
	private CgMetaTransporteRepository cgMetaTransporteRepository;

	@Override
	public BaseRepository<CgMetaTransporte, Long> getRepository() {
		return cgMetaTransporteRepository;
	}

}
