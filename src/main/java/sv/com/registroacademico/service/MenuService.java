package sv.com.registroacademico.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.mysema.query.types.OrderSpecifier;
import com.mysema.query.types.expr.BooleanExpression;

import sv.com.registroacademico.config.BaseRepository;
import sv.com.registroacademico.config.BaseService;
import sv.com.registroacademico.model.Menu;
import sv.com.registroacademico.model.QMenu;
import sv.com.registroacademico.repository.MenuRepository;

@Stateless
public class MenuService extends BaseService<Menu, Long> {

	@Inject
	private MenuRepository menuRepository;

	private static final QMenu qMenu = QMenu.menu;

	@Override
	public BaseRepository<Menu, Long> getRepository() {
		return menuRepository;
	}

	public List<Menu> findByDependeDe(Long dependeDe) {
		OrderSpecifier<Integer> order = qMenu.orden.asc();
		BooleanExpression byDependeDe = dependeDe == null ? qMenu.dependeDe.isNull() : qMenu.dependeDe.eq(dependeDe);
		return newJpaQuery().from(qMenu).where(byDependeDe).orderBy(order).list(qMenu);
	}

}
