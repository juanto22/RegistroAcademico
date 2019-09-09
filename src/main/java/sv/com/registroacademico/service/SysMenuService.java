package sv.com.registroacademico.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.mysema.query.types.OrderSpecifier;
import com.mysema.query.types.expr.BooleanExpression;

import sv.com.registroacademico.config.BaseRepository;
import sv.com.registroacademico.config.BaseService;
import sv.com.registroacademico.model.QSysMenu;
import sv.com.registroacademico.model.SysMenu;
import sv.com.registroacademico.repository.SysMenuRepository;

@Stateless
public class SysMenuService extends BaseService<SysMenu, Long> {

	@Inject
	private SysMenuRepository menuRepository;

	private static final QSysMenu qSysMenu = QSysMenu.sysMenu;

	@Override
	public BaseRepository<SysMenu, Long> getRepository() {
		return menuRepository;
	}

	public List<SysMenu> findByDependeDe(Long dependeDe) {
		OrderSpecifier<Integer> order = qSysMenu.orden.asc();
		BooleanExpression byDependeDe = dependeDe == null ? qSysMenu.dependeDe.isNull() : qSysMenu.dependeDe.eq(dependeDe);
		return newJpaQuery().from(qSysMenu).where(byDependeDe).orderBy(order).list(qSysMenu);
	}

}