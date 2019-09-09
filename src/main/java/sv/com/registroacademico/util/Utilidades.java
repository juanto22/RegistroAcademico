package sv.com.registroacademico.util;

import java.math.BigDecimal;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.PersistenceException;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Utilidades {

	public static boolean validarFechas(Date inicialDate, Date finalDate) {
		if (inicialDate != null && finalDate != null) {
			return inicialDate.before(finalDate);
		}
		if (inicialDate == null) {
			return false;
		}
		return true;
	}

	public static boolean validadRegex(String patterToValidate, String text) {
		Pattern pattern = Pattern.compile(patterToValidate);
		Matcher matcher = pattern.matcher(text);
		return matcher.matches();
	}

	public static String cantidadConLetra(String s) {
		StringBuilder result = new StringBuilder();
		BigDecimal totalBigDecimal = new BigDecimal(s).setScale(2, BigDecimal.ROUND_DOWN);
		long parteEntera = totalBigDecimal.toBigInteger().longValue();
		int triUnidades = (int) ((parteEntera % 1000));
		int triMiles = (int) ((parteEntera / 1000) % 1000);
		int triMillones = (int) ((parteEntera / 1000000) % 1000);
		int triMilMillones = (int) ((parteEntera / 1000000000) % 1000);

		if (parteEntera == 0) {
			result.append("Cero ");
			return result.toString();
		}

		if (triMilMillones > 0) {
			result.append(triTexto(triMilMillones).toString() + "Mil ");
		}
		if (triMillones > 0) {
			result.append(triTexto(triMillones).toString());
		}

		if (triMilMillones == 0 && triMillones == 1) {
			result.append("Millón ");
		} else if (triMilMillones > 0 || triMillones > 0) {
			result.append("Millones ");
		}

		if (triMiles > 0) {
			result.append(triTexto(triMiles).toString() + "Mil ");
		}
		if (triUnidades > 0) {
			result.append(triTexto(triUnidades).toString());
		}

		return result.toString();
	}

	private static StringBuilder triTexto(int n) {
		StringBuilder result = new StringBuilder();
		int centenas = n / 100;
		int decenas = (n % 100) / 10;
		int unidades = (n % 10);

		switch (centenas) {
		case 0:
			break;
		case 1:
			if (decenas == 0 && unidades == 0) {
				result.append("Cien ");
				return result;
			} else {
				result.append("Ciento ");
			}
			break;
		case 2:
			result.append("Doscientos ");
			break;
		case 3:
			result.append("Trescientos ");
			break;
		case 4:
			result.append("Cuatrocientos ");
			break;
		case 5:
			result.append("Quinientos ");
			break;
		case 6:
			result.append("Seiscientos ");
			break;
		case 7:
			result.append("Setecientos ");
			break;
		case 8:
			result.append("Ochocientos ");
			break;
		case 9:
			result.append("Novecientos ");
			break;
		}

		switch (decenas) {
		case 0:
			break;
		case 1:
			if (unidades == 0) {
				result.append("Diez ");
				return result;
			} else if (unidades == 1) {
				result.append("Once ");
				return result;
			} else if (unidades == 2) {
				result.append("Doce ");
				return result;
			} else if (unidades == 3) {
				result.append("Trece ");
				return result;
			} else if (unidades == 4) {
				result.append("Catorce ");
				return result;
			} else if (unidades == 5) {
				result.append("Quince ");
				return result;
			} else {
				result.append("Dieci");
			}
			break;
		case 2:
			if (unidades == 0) {
				result.append("Veinte ");
				return result;
			} else {
				result.append("Veinti");
			}
			break;
		case 3:
			result.append("Treinta ");
			break;
		case 4:
			result.append("Cuarenta ");
			break;
		case 5:
			result.append("Cincuenta ");
			break;
		case 6:
			result.append("Sesenta ");
			break;
		case 7:
			result.append("Setenta ");
			break;
		case 8:
			result.append("Ochenta ");
			break;
		case 9:
			result.append("Noventa ");
			break;
		}

		if (decenas > 2 && unidades > 0) {
			result.append("y ");
		}

		switch (unidades) {
		case 0:
			break;
		case 1:
			result.append("Un ");
			break;
		case 2:
			result.append("Dos ");
			break;
		case 3:
			result.append("Tres ");
			break;
		case 4:
			result.append("Cuatro ");
			break;
		case 5:
			result.append("Cinco ");
			break;
		case 6:
			result.append("Seis ");
			break;
		case 7:
			result.append("Siete ");
			break;
		case 8:
			result.append("Ocho ");
			break;
		case 9:
			result.append("Nueve ");
			break;
		}

		return result;
	}

	public static Date sumarRestarDiasFecha(Date fecha, int dias) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fecha);
		calendar.add(Calendar.DAY_OF_YEAR, dias);
		return calendar.getTime();
	}

	public static String getMessage(Exception ex) {
		String message = "";
		if (ex.getCause() instanceof javax.transaction.RollbackException) {
			javax.transaction.RollbackException excause = (javax.transaction.RollbackException) ex.getCause();
			if (excause.getCause() instanceof PersistenceException) {
				PersistenceException pe = (PersistenceException) excause.getCause();
				if (pe.getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
					org.hibernate.exception.ConstraintViolationException cvex = (org.hibernate.exception.ConstraintViolationException) pe
							.getCause();
					if (cvex.getCause() instanceof SQLIntegrityConstraintViolationException) {
						SQLIntegrityConstraintViolationException sqie = (SQLIntegrityConstraintViolationException) cvex
								.getCause();

						message = sqie.getMessage();

						String em = message;
						em = em.replaceFirst(".+\\(", "");
						em = em.replaceFirst("\\).+$", "");
						int beginIndex = em.indexOf(".");
						int endIndex = em.length();
						if (beginIndex >= 0 && (beginIndex + 1) <= endIndex) {
							em = em.substring(beginIndex + 1, endIndex);
						}
						message = em.trim();
					}
				}
			}
		}

		return message;
	}
}
