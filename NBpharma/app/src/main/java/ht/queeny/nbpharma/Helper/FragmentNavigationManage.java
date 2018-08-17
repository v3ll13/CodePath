package ht.queeny.nbpharma.Helper;

import android.app.Fragment;
import android.app.FragmentManager;

import ht.queeny.nbpharma.Interface.NavigationManage;
import ht.queeny.nbpharma.MenueDrawer;

/**
 * Created by root on 8/16/18.
 */

public class FragmentNavigationManage implements NavigationManage{

    private static FragmentNavigationManage mInstance;
    private android.support.v4.app.FragmentManager mFragmentManager;
    private MenueDrawer menuDrawer;

    public static FragmentNavigationManage getmInstance (MenueDrawer menuDrawer){
        if (mInstance == null)
            mInstance = new FragmentNavigationManage();
        mInstance.configure(menuDrawer);

        return mInstance;
    }

    private void configure(MenueDrawer menuDrawer) {
        menuDrawer = menuDrawer;
        mFragmentManager = menuDrawer.getSupportFragmentManager();

    }
        public void showFragment(String title){

        }
}
