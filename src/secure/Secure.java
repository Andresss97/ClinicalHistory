package secure;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Secure {

	public boolean IsInt(double d) {
		if (d == (int) d) {
			return true;
		} else {
			return false;
		}
	}

	public boolean IsDouble(int d) {
		if (d == (double) d) {
			return true;
		} else {
			return false;
		}
	}

	public boolean IsFloat(int d) {
		if (d == (float) d) {
			return true;
		} else {
			return false;
		}
	}

	public boolean IsInt2(String cadena) {
		try {
			Integer.parseInt(cadena);
			return true;
		} catch (NumberFormatException nfe) {
			return false;
		}
	}

	public boolean IsDouble2(String cadena) {
		try {
			Double.parseDouble(cadena);
			return true;
		} catch (NumberFormatException nfe) {
			return false;
		}
	}

	public boolean IsFloat2(String cadena) {
		try {
			Float.parseFloat(cadena);
			return true;
		} catch (NumberFormatException nfe) {
			return false;
		}
	}

	public boolean isValid(String email) {
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
				+ "A-Z]{2,7}$";

		Pattern pat = Pattern.compile(emailRegex);
		if (email == null)
			return false;
		return pat.matcher(email).matches();
	}
}

