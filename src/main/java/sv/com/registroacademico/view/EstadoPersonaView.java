package sv.com.registroacademico.view;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.util.Messages;

import lombok.Getter;
import lombok.Setter;
import sv.com.registroacademico.model.CgEstadoPersona;
import sv.com.registroacademico.service.CgEstadoPersonaService;
import sv.com.registroacademico.util.Utilidades;
import sv.com.registroacademico.util.enumeration.ViewStatus;
import sv.com.registroacademico.util.web.BaseLazyModel;

@Named
@ViewScoped
@Getter
@Setter
public class EstadoPersonaView implements Serializable {

	private static final long serialVersionUID = 43568786343L;

	@Inject
	private transient CgEstadoPersonaService cgEstadoPersonaService;

//	@Inject
//	private FacesContext facesContext;

	private transient BaseLazyModel<CgEstadoPersona, Long> estadoPersonaLazy;
	private CgEstadoPersona selectedItem;

	private ViewStatus statusView;

	@PostConstruct
	public void init() {
		loadLazyData();
		statusView = ViewStatus.NONE;
	}

	public void loadLazyData() {
		estadoPersonaLazy = new BaseLazyModel<CgEstadoPersona, Long>(cgEstadoPersonaService);
	}

	public void preparedCreate() {
		statusView = ViewStatus.NEW;
		selectedItem = new CgEstadoPersona();
	}

	public void preparedUpdate() {
		statusView = ViewStatus.EDIT;
	}

	public void preparedUpdate(CgEstadoPersona selectedItem) {
		statusView = ViewStatus.EDIT;
		this.selectedItem = selectedItem;
	}

	public void save() {
		try {
			cgEstadoPersonaService.save(selectedItem);
			Messages.create("Información").detail("Registro ingresado exitosamente").add();
			
		} catch (Exception e) {
			String mensaje = Utilidades.getMessage(e);
			Messages.create("ERROR").detail(mensaje).error().add();
		}

	}

	public void update() {
		cgEstadoPersonaService.save(selectedItem);
		Messages.create("Información").detail("Registro actualizado exitosamente").add();

	}

	public void delete(CgEstadoPersona selectedItem) {
		cgEstadoPersonaService.delete(selectedItem);
		Messages.create("Información").detail("Registro eliminado exitosamente").add();

	}

}
