package sv.com.registroacademico.service;

import javax.ejb.Stateless;
import javax.inject.Inject;

import sv.com.registroacademico.config.BaseRepository;
import sv.com.registroacademico.config.BaseService;
import sv.com.registroacademico.model.Empresa;
import sv.com.registroacademico.repository.EmpresaRepository;

@Stateless
public class EmpresaService extends BaseService<Empresa, Long> {

	@Inject
	private EmpresaRepository empresaRepository;

	@Override
	public BaseRepository<Empresa, Long> getRepository() {
		return empresaRepository;
	}

}
