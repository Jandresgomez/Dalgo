//David Felipe Nino Romero 				201412734
//Maria del Rosario Leon				201423755

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ProblemaC 
{
	private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

	private String[] palabras;

	private String parrafo;

	private int anchoMin;

	private int anchoFinal;

	private int rioFinal;

	private ArrayList<String> lineas;

	public ProblemaC()
	{
		try 
		{
			ingresarParametros();			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public void ingresarParametros() throws IOException
	{
		parrafo = "";
		parrafo = in.readLine();
		while (parrafo != null)
		{
			if(parrafo.isEmpty())
			{
				System.out.println("El texto ingresado esta vacio, intente de nuevo");
			}
			else 
			{
				rioFinal=0;

				anchoFinal=0;

				funcion();
			}
			parrafo = in.readLine();
		}
	}

	public void funcion()
	{
		palabras = parrafo.split(" ");

		palabraMasLarga();

		rioMasLargo();

		System.out.println(""+anchoFinal+" "+rioFinal);
	}

	public void palabraMasLarga()
	{
		int respuesta = -1;

		for(int i=0;i <palabras.length; i++)
		{
			if (palabras[i].length()>respuesta)
			{
				respuesta = palabras[i].length();
			}
		}

		anchoMin =  respuesta;
		anchoFinal=anchoMin;
	}

	public void rioMasLargo(){

		int anchoActual =anchoMin;

		int rioActual=0;

		while((parrafo.length()/anchoActual)>1 && (parrafo.length()/anchoActual)>rioFinal) {

			lineas = new ArrayList<String>();

			int a =partir(anchoActual);

			if(lineas.size()<rioFinal)
				break;

			rioActual=encontrarRio();

			if(rioActual>rioFinal)
			{
				rioFinal=rioActual;
				anchoFinal= anchoActual;
			}

			anchoActual+=a;
		}
	}

	public int partir(int ancho) {
		String subParrafo = parrafo;
		int palabraCorta=Integer.MAX_VALUE;
		int i=ancho;

		while (subParrafo.length()>i) {
			if(subParrafo.charAt(i)==' ') 
			{
				lineas.add(subParrafo.substring(0,i));
				subParrafo=subParrafo.substring(i+1);
			}
			else if(subParrafo.charAt(i)!=' ')
			{
				if(subParrafo.contains(" ")) {
					int posiblePalabraCorta = subParrafo.substring(i).indexOf(" ");
					palabraCorta= posiblePalabraCorta<palabraCorta && posiblePalabraCorta>0?posiblePalabraCorta:palabraCorta;
					i=subParrafo.substring(0, i+1).contains(" ")?subParrafo.substring(0, i+1).lastIndexOf(" "):i;
					lineas.add(subParrafo.substring(0,i));
					subParrafo=subParrafo.substring(i+1);
					i=ancho;
				}
			}
			if(subParrafo.length()<=i && !subParrafo.isEmpty())
				lineas.add(subParrafo);
		}

		return palabraCorta;

	}

	public List<Integer> indicesDeEspacios(String linea) {
		ArrayList<Integer> indices= new ArrayList<>();
		for (int i = 0; i < linea.length();) {
			i=linea.indexOf(" ", i+1)!=-1?linea.indexOf(" ", i+1):linea.length();
			if(i!=linea.length())
				indices.add(i);
		}
		return indices;
	}

	public int encontrarRio() {
		int largoMax=1;
		int largoActual = 1;
		ArrayList<Integer> indicesActuales = new ArrayList<>();
		for (int i = 0; i+1 < lineas.size(); i++) {
			if(indicesActuales.isEmpty())
			{
				largoActual=1;
				indicesActuales=(ArrayList<Integer>) compararEspacios(indicesDeEspacios(lineas.get(i)), indicesDeEspacios(lineas.get(i+1)));
			}
			else if(indicesActuales.size()>=1)
			{
				largoActual++;
				indicesActuales=(ArrayList<Integer>) compararEspacios(indicesActuales, indicesDeEspacios(lineas.get(i+1)));
			}

			if(largoMax<largoActual)
				largoMax=largoActual;
		}
		return largoMax;
	}

	public List<Integer> compararEspacios(List<Integer> lista, List<Integer> lista2)
	{
		ArrayList<Integer> respuesta = new ArrayList<>();
		for (Integer integer : lista2) {
			if(lista.contains(integer)||lista.contains(integer+1)||lista.contains(integer-1))
				respuesta.add(integer);
		}
		return respuesta;
	}

	public static void main(String args[])	
	{		
		new ProblemaC();		
	}
}
