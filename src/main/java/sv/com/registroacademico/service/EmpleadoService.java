package sv.com.registroacademico.service;

import javax.ejb.Stateless;
import javax.inject.Inject;

import sv.com.registroacademico.config.BaseRepository;
import sv.com.registroacademico.config.BaseService;
import sv.com.registroacademico.model.Empleado;
import sv.com.registroacademico.repository.EmpleadoRepository;


@Stateless
public class EmpleadoService extends BaseService<Empleado, Long> {

	@Inject
	private EmpleadoRepository departamentoRepository;

	@Override
	public BaseRepository<Empleado, Long> getRepository() {
		return departamentoRepository;
	}


}
