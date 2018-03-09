package secure;

public class secure {

	public static void main(String[] args) {
		String w= "2";
		double a = 1.1; 
		float b = 2.6f;
		int c= 1;
		boolean iw = IsDouble2(w);
		boolean ia = IsInt(a); 
		boolean ib = IsDouble(c);
		boolean ic = IsFloat(c);
		
		System.out.println(iw);
		System.out.println(ib);
		System.out.println(ic);
		
	}

	private static boolean IsInt(double d) {
		if(d==(int)d){
	    	return true;
	    }
	    else{ 
	    	return false;
	    }}
		public static boolean IsDouble(int d){
		    if(d==(double)d){
		    	return true;
		    }
		    else{ 
		    	return false;
		    }}
		public static boolean IsFloat(int d){
		    if(d==(float)d){
		    	return true;
		    }
		    else{ 
		    	return false;
		    }}
		private static boolean IsInt2(String cadena){
			try {
				Integer.parseInt(cadena);
				return true;
			} catch (NumberFormatException nfe){
				return false;
			}}
		private static boolean IsDouble2(String cadena){
			try {
				Double.parseDouble(cadena);
				return true;
			} catch (NumberFormatException nfe){
				return false;
			}}
		private static boolean IsFloat2(String cadena){
			try {
				Float.parseFloat(cadena);
				return true;
			} catch (NumberFormatException nfe){
				return false;
			}}

}

