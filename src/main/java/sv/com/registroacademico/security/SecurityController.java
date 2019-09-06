package sv.com.registroacademico.security;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;
import sv.com.registroacademico.model.Menu;
import sv.com.registroacademico.model.pojo.MenuPojo;
import sv.com.registroacademico.service.MenuService;

@Named
@SessionScoped
@Setter
@Getter
@Log
public class SecurityController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3425014171719474763L;

	@Inject
	private MenuService menuService;

	private List<MenuPojo> menuModel;

	@PostConstruct
	public void init() {
		if (isEmptyMenu()) {
			menuModel = new ArrayList<>();
			loadMenu();
		}
	}

	private boolean isEmptyMenu() {
		return menuModel == null || menuModel.isEmpty();
	}

	private void loadMenu() {
		List<Menu> menuList = menuService.findByDependeDe(null);
		for (Menu currentMenu : menuList) {
			MenuPojo menu = new MenuPojo(currentMenu, getChildrenMenu(currentMenu));
			menuModel.add(menu);
		}
	}

	public List<Menu> getChildrenMenu(Menu currentMenu) {
		return menuService.findByDependeDe(currentMenu.getId());
	}

}
