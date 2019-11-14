package sv.com.registroacademico.service;

import javax.ejb.Stateless;
import javax.inject.Inject;

import sv.com.registroacademico.config.BaseRepository;
import sv.com.registroacademico.config.BaseService;
import sv.com.registroacademico.model.CgMetaConvivencia;
import sv.com.registroacademico.repository.CgMetaConvivenciaRepository;

@Stateless
public class CgMetaConvivenciaService extends BaseService<CgMetaConvivencia, Long> {

	@Inject
	private CgMetaConvivenciaRepository cgMetaConvivenciaRepository;

	@Override
	public BaseRepository<CgMetaConvivencia, Long> getRepository() {
		return cgMetaConvivenciaRepository;
	}

}
