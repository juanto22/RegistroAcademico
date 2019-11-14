package sv.com.registroacademico.view;

import java.util.Arrays;

public class test {

	public static void main(String[] args) {
		String nombre = "Ana   del   Carmen";
        String[] nombres = nombre.split("\\s+");
        boolean first = true;
        String primerNombre = "", segundoNombre = "";
        for(int i = 0; i < nombres.length; i++) {
        	if(first) {
        		primerNombre = nombres[i];
        		first = false;
        	}else {
        		segundoNombre += " " + nombres[i];
        	}
        }
        System.out.println(primerNombre.trim());
        System.out.println(segundoNombre.trim());

	}

}
