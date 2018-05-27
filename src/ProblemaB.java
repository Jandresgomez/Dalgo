//David Felipe Nino Romero 				201412734
//Maria del Rosario Leen				201423755

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class ProblemaB {
	public static String NOT_FOUND = "*";
	
	public static void close(BufferedReader br) {
		try {
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static int sortedIndex(int[] n) {
		for (int i = 0, prev = 0; i < n.length; i++) {
			if(prev > n[i]) {
				return i;
			} else {
				prev = n[i];
			}
		}
		return n.length;
	}
	
	public static int[] toIntArray(String n) {
		int length = n.length();
		int[] arr = new int[length];
		for(int i = 0; i < length; i++) {
			arr[length - 1 - i] = Integer.parseInt(String.valueOf(n.charAt(i)));
		}
		return arr;
	}
	
	public static String sp(int[] n, int k) {
		for(int kActual = 1; kActual <= k; kActual++) {
			int i = sortedIndex(n);
			if(i >= n.length) {
				//Caso donde no existe sp(n,1), puesto que n es el maximo posible.
				return NOT_FOUND;
			} else {
				int[] arr = Arrays.copyOf(n, i);
				String res = sp(arr, 1);
				
				if(res.equals(NOT_FOUND)) {
					//Caso donde el arreglo[0..i] esta maximizado (no tiene sp con k=1)
					int replacement = 10;
					int index = -1;
					
					//Encontrar el menor digito que sea mayor a n[i]
					for(int j = 0; j < i; j++) {
						if(n[i] < n[j] && n[j] < replacement) {
							index = j;
							replacement = n[j];
						}
					}
					
					n[index] = n[i];
					n[i] = replacement;
					arr = Arrays.copyOf(n, i);
					
					//Encontrar el sp maximo de la subcadena
					Arrays.sort(arr);
					
					//Anexarla al reves a la cadena original, dando como resultado la subcadena de menor valor
					for(int j = 0; j < arr.length; j++) {
						n[j] = arr[arr.length - 1 - j];
					}
				} else {
					//Caso donde el arreglo[0..i] si tiene sp
					arr = toIntArray(res);
					
					//Anexarla a la cadena original
					for(int j = 0; j < arr.length; j++) {
						n[j] = arr[j];
					}
				}
			}
		}
		
		//Pasar de cadena de ints a un string
		String num = "";
		for(int p = n.length - 1; p >= 0; p--) num += n[p] + "";
		return num;
	}
	
	public static void main(String[] args) {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			
			String line = br.readLine();
			while(line != null) {
				String[] split = line.split(" ");
				int k = Integer.parseInt(split[0]);
				int[] n = toIntArray(split[1]);
				
				String aws = sp(n,k);
				System.out.println(aws);
				
				line = br.readLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
			if(br != null) {
				close(br);
			}
		}
	}
}
