package sv.com.registroacademico.service;

import javax.ejb.Stateless;
import javax.inject.Inject;

import sv.com.registroacademico.config.BaseRepository;
import sv.com.registroacademico.config.BaseService;
import sv.com.registroacademico.model.Materia;
import sv.com.registroacademico.repository.MateriaRepository;

@Stateless
public class MateriaService extends BaseService<Materia, Long> {

	@Inject
	private MateriaRepository materiaRepository;

	@Override
	public BaseRepository<Materia, Long> getRepository() {
		return materiaRepository;
	}

}
