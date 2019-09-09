package sv.com.registroacademico.view;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.util.Messages;

import lombok.Getter;
import lombok.Setter;
import sv.com.registroacademico.model.CgAcreedores;
import sv.com.registroacademico.service.CgAcreedoresService;
import sv.com.registroacademico.util.Utilidades;
import sv.com.registroacademico.util.enumeration.ViewStatus;
import sv.com.registroacademico.util.web.BaseLazyModel;

@Named
@ViewScoped
@Getter
@Setter
public class AcreedoresView implements Serializable {

	private static final long serialVersionUID = 43568786343L;

	@Inject
	private transient CgAcreedoresService cgAcreedoresService;

//	@Inject
//	private FacesContext facesContext;

	private transient BaseLazyModel<CgAcreedores, Long> acreedoresLazy;
	private CgAcreedores selectedItem;

	private ViewStatus statusView;

	@PostConstruct
	public void init() {
		loadLazyData();
		statusView = ViewStatus.NONE;
	}

	public void loadLazyData() {
		acreedoresLazy = new BaseLazyModel<CgAcreedores, Long>(cgAcreedoresService);
	}

	public void preparedCreate() {
		statusView = ViewStatus.NEW;
		selectedItem = new CgAcreedores();
	}

	public void preparedUpdate() {
		statusView = ViewStatus.EDIT;
	}

	public void preparedUpdate(CgAcreedores cgAcreedores) {
		statusView = ViewStatus.EDIT;
		selectedItem = cgAcreedores;
	}

	public void save() {
		try {
			cgAcreedoresService.save(selectedItem);
			Messages.create("Información").detail("Registro ingresado exitosamente").add();
			
		} catch (Exception e) {
			String mensaje = Utilidades.getMessage(e);
			Messages.create("ERROR").detail(mensaje).error().add();
		}

	}

	public void update() {
		cgAcreedoresService.save(selectedItem);
		Messages.create("Información").detail("Registro actualizado exitosamente").add();

	}

	public void delete(CgAcreedores selectedItem) {
		cgAcreedoresService.delete(selectedItem);
		Messages.create("Información").detail("Registro eliminado exitosamente").add();

	}

}
