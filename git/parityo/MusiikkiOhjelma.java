package ohjelmaluokat;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


import tietokanta.DaoMusiikki;
import kohdeluokat.Musiikki;

public class MusiikkiOhjelma {
	private Scanner input;
	
	
	public MusiikkiOhjelma() {
		input = new Scanner(System.in);
	}
	
	public void aja () {
		int valinta=-1;

		do
		{
			System.out.println("\n1) hae kaikki levyt");
			System.out.println("2) hae tietty levy");
			System.out.println("0) lopeta ");
			System.out.print("Valitse: ");
			valinta = lueValinta();

			switch (valinta)
			{
			case 0: break;
			case 1: haeLevyt();
					break;
			case 2: haeLevy();
					break;
			default: System.out.println("Anna luku väliltä 0-2");
			}
		}while (valinta != 0);
	}
	private int lueValinta () {
		int valinta ;
		try {
			valinta = input.nextInt();
			
		}
		catch ( Exception e) {
			input.nextLine(); // syttöpuskuris tyhjäksi
			valinta = -1;
		}
		return valinta;
	}
	private void haeLevyt() {
		List<Musiikki> lista;
		DaoMusiikki dao = new DaoMusiikki();

		try
		{
			lista = dao.haeLevyt();

			if (lista != null)
			{
				System.out.println("\nLevyt:");
				for (int i = 0;i <lista.size();i++)
					System.out.println("\n" + lista.get(i));
			}
			else
				System.out.println("Tietokannassa ei ole yhtään levyä");
		}
		catch (SQLException e )
		{
			System.out.println("Tietokantaan ei saada yhteyttä, korjaamme vian tuotapikaa");
		}
		catch (Exception e)
		{
			System.out.println("Ohjelmointi virhe");
		}

	}
	private void haeLevy() {
		String tunnus;
		Musiikki musiikki;

		System.out.print("Anna levyn tunnus: ");
		tunnus = input.next();

		DaoMusiikki dao = new DaoMusiikki();
		try
		{
			musiikki = dao.haeLevy(tunnus);

			if (musiikki != null)
				System.out.println(musiikki);
			else
				System.out.println(tunnus + " levyä ei löytynyt");
		}
		catch (SQLException e)
		{
			System.out.println("Tietokantaan ei saada yhteyttä, korjaamme vian tuotapikaa");
		}
		catch (Exception e)
		{
			System.out.println("Ohjelmointi virhe!");
		}
	}

	public static void main(String[] args) {
		MusiikkiOhjelma ohj = new MusiikkiOhjelma();
		ohj.aja();
	}

}
