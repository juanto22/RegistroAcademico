package sv.com.registroacademico.view;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.util.Messages;

import lombok.Getter;
import lombok.Setter;
import sv.com.registroacademico.model.CgAreaConocimiento;
import sv.com.registroacademico.model.CgTipoMateria;
import sv.com.registroacademico.model.Materia;
import sv.com.registroacademico.service.CgAreaConocimientoService;
import sv.com.registroacademico.service.CgTipoMateriaService;
import sv.com.registroacademico.service.MateriaService;
import sv.com.registroacademico.util.Utilidades;
import sv.com.registroacademico.util.enumeration.ViewStatus;
import sv.com.registroacademico.util.web.BaseLazyModel;

@Named
@ViewScoped
@Getter
@Setter
public class MateriasView implements Serializable {

	private static final long serialVersionUID = 43568786343L;

	@Inject
	private transient MateriaService materiaService;
	
	@Inject
	private transient CgTipoMateriaService cgTipoMateriaService;
	
	@Inject
	private transient CgAreaConocimientoService cgAreaConocimientoService;

	private transient BaseLazyModel<Materia, Long> materiasLazy;
	private Materia selectedItem;

	private ViewStatus statusView;
	
	private List<CgTipoMateria> tipoMateriaList;
	
	private List<CgAreaConocimiento> areasConocimientoList;

	@PostConstruct
	public void init() {
		loadLazyData();
		statusView = ViewStatus.NONE;
		tipoMateriaList = cgTipoMateriaService.findMateriasByVigencia("SI");
		areasConocimientoList = cgAreaConocimientoService.findMateriasByVigencia("SI");
	}
	
	public void goBack() {
		statusView = ViewStatus.NONE;
	}

	public void loadLazyData() {
		materiasLazy = new BaseLazyModel<Materia, Long>(getMateriaService());
	}

	public void preparedCreate() {
		statusView = ViewStatus.NEW;
		selectedItem = new Materia();
	}

	public void preparedUpdate(Materia materia) {
		statusView = ViewStatus.EDIT;
		selectedItem = materia;
	}

	public void save() {
		try {
			materiaService.save(selectedItem);
			Messages.create("Información").detail("Registro ingresado exitosamente").add();
			
		} catch (Exception e) {
			String mensaje = Utilidades.getMessage(e);
			Messages.create("ERROR").detail(mensaje).error().add();
		}

	}

	public void update() {
		materiaService.save(selectedItem);
		Messages.create("Información").detail("Registro actualizado exitosamente").add();

	}

	public void delete(Materia selectedItem) {
		materiaService.delete(selectedItem);
		Messages.create("Información").detail("Registro eliminado exitosamente").add();

	}

}
