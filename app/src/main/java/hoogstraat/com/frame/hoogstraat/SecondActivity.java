package hoogstraat.com.frame.hoogstraat;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class SecondActivity extends AppCompatActivity {

    private BottomNavigationView mainbottomNav;

    private NowFragment nowFragment;
    private HistoryFragment historyFragment;
    private FutureFragment futureFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        getSupportActionBar().setTitle("Hoogstraat");

        mainbottomNav = findViewById(R.id.mainBottomNav);

        // FRAGMENTS
        nowFragment = new NowFragment();
        historyFragment = new HistoryFragment();
        futureFragment = new FutureFragment();

        initializeFragment();

        mainbottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.main_container);

                switch (item.getItemId()) {

                    case R.id.bottom_action_history:

                        replaceFragment(historyFragment, currentFragment);
                        return true;

                    case R.id.bottom_action_now:

                        replaceFragment(nowFragment, currentFragment);
                        return true;

                    case R.id.bottom_action_future:

                        replaceFragment(futureFragment, currentFragment);
                        return true;

                    default:
                        return false;


                }

            }
        });
    }

    private void initializeFragment(){

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        fragmentTransaction.add(R.id.main_container, historyFragment);
        fragmentTransaction.add(R.id.main_container, nowFragment);
        fragmentTransaction.add(R.id.main_container, futureFragment);

        fragmentTransaction.hide(historyFragment);
        fragmentTransaction.hide(futureFragment);

        fragmentTransaction.commit();

    }

    private void replaceFragment(Fragment fragment, Fragment currentFragment){

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if(fragment == historyFragment){

            fragmentTransaction.hide(nowFragment);
            fragmentTransaction.hide(futureFragment);

        }

        if(fragment == nowFragment){

            fragmentTransaction.hide(historyFragment);
            fragmentTransaction.hide(futureFragment);

        }

        if(fragment == futureFragment){

            fragmentTransaction.hide(historyFragment);
            fragmentTransaction.hide(nowFragment);

        }
        fragmentTransaction.show(fragment);

        //fragmentTransaction.replace(R.id.main_container, fragment);
        fragmentTransaction.commit();

    }
}
