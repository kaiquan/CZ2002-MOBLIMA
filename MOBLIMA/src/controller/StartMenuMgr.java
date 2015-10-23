package controller;

public class StartMenuMgr {

	public void processOptions(int choice){
		
		switch(choice)
		{
		
		case 3:
			AdminLoginMgr mAdminLoginMgr = new AdminLoginMgr();	
			mAdminLoginMgr.prepareloginForm();
		}
		
	}

}
