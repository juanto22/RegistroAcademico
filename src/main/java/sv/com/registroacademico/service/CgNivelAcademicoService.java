package sv.com.registroacademico.service;

import javax.ejb.Stateless;
import javax.inject.Inject;

import sv.com.registroacademico.config.BaseRepository;
import sv.com.registroacademico.config.BaseService;
import sv.com.registroacademico.model.CgNivelAcademico;
import sv.com.registroacademico.repository.CgNivelAcademicoRepository;

@Stateless
public class CgNivelAcademicoService extends BaseService<CgNivelAcademico, Long> {

	@Inject
	private CgNivelAcademicoRepository cgNivelAcademicoRepository;

	@Override
	public BaseRepository<CgNivelAcademico, Long> getRepository() {
		return cgNivelAcademicoRepository;
	}

}
