package ht.queeny.nbpharma;

import android.content.res.Configuration;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.graphics.drawable.DrawerArrowDrawable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import ht.queeny.nbpharma.Adapter.CustomExpendableListAdapter;
import ht.queeny.nbpharma.Helper.FragmentNavigationManage;
import ht.queeny.nbpharma.Interface.NavigationManage;

public class MenueDrawer extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private String mActivityTitle;
    private String[] items;

    private ExpandableListView expandableListView;
    private ExpandableListAdapter adapter;
    private List<String> listTitle;
    private Map<String ,List<String>> listChild;
    private NavigationManage navigationManage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menue_drawer);

        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        mActivityTitle = getTitle().toString();
        expandableListView =(ExpandableListView)findViewById(R.id.nav_list);
        navigationManage = FragmentNavigationManage.getmInstance(this);

        initItems();
        View listHeaderView = getLayoutInflater().inflate(R.layout.nav_header, null, false);
        expandableListView.addHeaderView(listHeaderView);
        
        genData();
        addDrawer();
        SetUpDrawer();

        if(savedInstanceState == null)
            selectFirstItemAsDefault();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Quennyyy");

        
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    private void selectFirstItemAsDefault() {
        if (navigationManage != null){

            String firstitem = listTitle.get(0);
            navigationManage.showFragment(firstitem);
            getSupportActionBar().setTitle(firstitem);
        }
    }

    private void SetUpDrawer() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close ){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

                getActionBar().setTitle("Queenyy");
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActionBar().setTitle(mActivityTitle);
                invalidateOptionsMenu();
            }
        };

            mDrawerToggle.setDrawerIndicatorEnabled(true);
            mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    private void addDrawer() {
        adapter = new CustomExpendableListAdapter(this, listTitle, listChild);
        expandableListView.setAdapter(adapter);
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                getSupportActionBar().setTitle(listTitle.get(groupPosition).toString());// Set title for toolbar when open menu
            }
        });

        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
                getSupportActionBar().setTitle("Queeny");

            }

        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int groupPosition, int childPosition, long l) {
                // change fragment when click on item

                String selectedItem = ((List)(listChild.get(listTitle.get(groupPosition)))).get(childPosition).toString();
                getSupportActionBar().setTitle(selectedItem);


                if(items[0].equals(listTitle.get(groupPosition)))
                    navigationManage.showFragment(selectedItem);

                else
                    throw new IllegalArgumentException("Not a supported Fragment");
                    mDrawerLayout.closeDrawer(GravityCompat.START);

                    return false;
            }
        });
    }

    private void genData() {
        List<String> title = Arrays.asList("Medicament", "Pharmacie", "Conseil", "A propos");
        List<String> childitem = Arrays.asList("Liste Medicament","Liste Pharmacie","Location Pharmacie", "Prix Medicament");

        listChild  = new TreeMap<>();
        listChild.put(title.get(0), childitem);
        listChild.put(title.get(1), childitem);
        listChild.put(title.get(2), childitem);
        listChild.put(title.get(3), childitem);

        listTitle = new ArrayList<>(listChild.keySet());
    }

    private void initItems() {
        items = new String[]{"Medicament","Pharmacie", "Conseil", "A Propos"};
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mainmenue, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id  = item.getItemId();

        if(mDrawerToggle.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);
    }
}
