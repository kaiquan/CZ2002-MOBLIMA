package main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

import controller.StartMenuMgr;
import dao_module.AdminDAO;
import dao_module.CineplexDAO;
import dao_module.GuestDAO;
import dao_module.HoildayDAO;
import dao_module.JSONDAO;
import dao_module.MovieDAO;

public class SysStart {
	static Scanner sc;
	
	public static void main(String a[]) throws IOException{
		int choice;
		sc= new Scanner(System.in);
		showOptions();
		StartMenuMgr mMgr = new StartMenuMgr();
		
		choice = sc.nextInt();
		mMgr.processOptions(choice);
		
	}
	
	public static void showOptions(){
		
		System.out.println("Welcome");
		System.out.println("Please select an option");
		System.out.println("1.List all available movies");
		System.out.println("2.List movies by cineplex");
		System.out.println("3.Admin Login");
		System.out.print("Choice: ");
		
		//fill in the blanks
		
		
	}
}
