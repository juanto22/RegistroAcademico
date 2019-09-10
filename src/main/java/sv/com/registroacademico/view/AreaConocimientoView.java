package sv.com.registroacademico.view;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.util.Messages;

import lombok.Getter;
import lombok.Setter;
import sv.com.registroacademico.model.CgAreaConocimiento;
import sv.com.registroacademico.service.CgAreaConocimientoService;
import sv.com.registroacademico.util.Utilidades;
import sv.com.registroacademico.util.enumeration.ViewStatus;
import sv.com.registroacademico.util.web.BaseLazyModel;

@Named
@ViewScoped
@Getter
@Setter
public class AreaConocimientoView implements Serializable {

	private static final long serialVersionUID = 43568786343L;

	@Inject
	private transient CgAreaConocimientoService cgAreaConocimientoService;

//	@Inject
//	private FacesContext facesContext;

	private transient BaseLazyModel<CgAreaConocimiento, Long> areaConocimientoLazy;
	private CgAreaConocimiento selectedItem;

	private ViewStatus statusView;

	@PostConstruct
	public void init() {
		loadLazyData();
		statusView = ViewStatus.NONE;
	}

	public void loadLazyData() {
		areaConocimientoLazy = new BaseLazyModel<CgAreaConocimiento, Long>(cgAreaConocimientoService);
	}

	public void preparedCreate() {
		statusView = ViewStatus.NEW;
		selectedItem = new CgAreaConocimiento();
	}

	public void preparedUpdate() {
		statusView = ViewStatus.EDIT;
	}

	public void preparedUpdate(CgAreaConocimiento selectedItem) {
		statusView = ViewStatus.EDIT;
		this.selectedItem = selectedItem;
	}

	public void save() {
		try {
			cgAreaConocimientoService.save(selectedItem);
			Messages.create("Información").detail("Registro ingresado exitosamente").add();
			
		} catch (Exception e) {
			String mensaje = Utilidades.getMessage(e);
			Messages.create("ERROR").detail(mensaje).error().add();
		}

	}

	public void update() {
		cgAreaConocimientoService.save(selectedItem);
		Messages.create("Información").detail("Registro actualizado exitosamente").add();

	}

	public void delete(CgAreaConocimiento selectedItem) {
		cgAreaConocimientoService.delete(selectedItem);
		Messages.create("Información").detail("Registro eliminado exitosamente").add();

	}

}
