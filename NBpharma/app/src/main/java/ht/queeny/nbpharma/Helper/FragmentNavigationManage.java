package ht.queeny.nbpharma.Helper;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;


import ht.queeny.nbpharma.Fragments.FragmentContent;
import ht.queeny.nbpharma.Interface.NavigationManage;
import ht.queeny.nbpharma.MenueDrawer;
import ht.queeny.nbpharma.R;

/**
 * Created by root on 8/16/18.
 */

public class FragmentNavigationManage implements NavigationManage{

    private static FragmentNavigationManage mInstance;

    private FragmentManager mFragmentManager;
    private MenueDrawer menuDrawer;

    public static FragmentNavigationManage getmInstance (MenueDrawer menuDrawer){
        if (mInstance == null)
            mInstance = new FragmentNavigationManage();
        mInstance.configure(menuDrawer);

        return mInstance;
    }

    private void configure(MenueDrawer menuDrawer) {
        menuDrawer = menuDrawer;
        mFragmentManager = menuDrawer.getFragmentManager();

    }

    @Override
    public void showFragment(String title) {
        //showFragment(FragmentContent.newInstance(title),  false);
    }

    public void showFragment(Fragment fragmentContent, boolean allowStateLoss){
        FragmentManager fm = mFragmentManager;
        android.app.FragmentTransaction ft = fm.beginTransaction().replace(R.id.container, fragmentContent);



    }
}
