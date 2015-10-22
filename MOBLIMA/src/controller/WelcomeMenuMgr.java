package controller;
import dao_module.CineplexDAO;
import model.Cineplex;
import view.AdminLoginView;

import java.util.ArrayList;

public class WelcomeMenuMgr {
	
	AdminLoginView mAdminLogin;
	CineplexDAO cDao;
	ArrayList<String> cineplexes = new ArrayList<String>();
	
	public void processOptions(int choice){
		
		switch(choice)
		{
		
		case 3:
			if(mAdminLogin == null)
				mAdminLogin = new AdminLoginView();
			if(cDao == null)
				cDao = new CineplexDAO();
		
			for(int i=0; i<cDao.getAllCineplex().size(); i++)
				cineplexes.add(cDao.getAllCineplex().get(i).getName());
			
			mAdminLogin.loginForm(cineplexes);
		}
		
	}

}
