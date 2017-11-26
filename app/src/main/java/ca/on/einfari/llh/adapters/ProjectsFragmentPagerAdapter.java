/*
    ProjectsFragmentPagerAdapter.java
    Assignment 2

    Revision History:
        Gonzalo Ramos Zúñiga, 2017.11.25: Created
 */

package ca.on.einfari.llh.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import ca.on.einfari.llh.fragments.FencingFragment;
import ca.on.einfari.llh.fragments.SidingFragment;

public class ProjectsFragmentPagerAdapter extends FragmentPagerAdapter {

    public ProjectsFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new FencingFragment();
            case 1:
                return new SidingFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Fencing";
            case 1:
                return "Siding";
            default:
                return null;
        }
    }

}