package sv.com.registroacademico.model.pojo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sv.com.registroacademico.model.SysMenu;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MenuPojo {

	private SysMenu menuElement;
	private List<SysMenu> childrenElements;

}
