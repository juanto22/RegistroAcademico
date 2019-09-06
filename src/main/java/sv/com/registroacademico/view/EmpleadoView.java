package sv.com.registroacademico.view;

import java.io.Serializable;

import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;

import lombok.Getter;
import lombok.Setter;

@Named
@ViewScoped
@Getter
@Setter
public class EmpleadoView implements Serializable {

	private static final long serialVersionUID = 43568786343L;

//	@Inject
//	private transient EmpleadoService empleadoService;
//
//	@Inject
//	private FacesContext facesContext;
//
//	private transient BaseLazyModel<Empleado, Long> empleadosLazy;
//	private Empleado selectedItem;
//
//	private ViewStatus statusView;
//
//	@PostConstruct
//	public void init() {
//		loadLazyData();
//		statusView = ViewStatus.NONE;
//	}
//
//	private void loadLazyData() {
//		empleadosLazy = new BaseLazyModel<Empleado, Long>(empleadoService);
//	}
//
//	public void preparedCreate() {
//		statusView = ViewStatus.NEW;
//		selectedItem = new Empleado();
//	}
//
//	public void preparedUpdate() {
//		statusView = ViewStatus.EDIT;
//		}
//	
//	public void preparedUpdate(Empleado empleado) {
//		statusView = ViewStatus.EDIT;
//		selectedItem = empleado;
//	}
//
//	public void save() {
//		empleadoService.save(selectedItem);
//		Messages.create("Información").detail("Registro ingresado exitosamente").add();
//
//	}
//
//	public void update() {
//		empleadoService.save(selectedItem);
//		Messages.create("Información").detail("Registro actualizado exitosamente").add();
//
//	}
//
//	public void delete(Empleado selectedItem) {
//		empleadoService.delete(selectedItem);
//		Messages.create("Información").detail("Registro eliminado exitosamente").add();
//
//	}

}
