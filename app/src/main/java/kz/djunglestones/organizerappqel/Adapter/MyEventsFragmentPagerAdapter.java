package kz.djunglestones.organizerappqel.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import kz.djunglestones.organizerappqel.Activities.CreateEventActivity;
import kz.djunglestones.organizerappqel.Fragments.CreateEventFragment;
import kz.djunglestones.organizerappqel.Fragments.DraftEventsFragment;
import kz.djunglestones.organizerappqel.Fragments.EditCompanyFragment;
import kz.djunglestones.organizerappqel.Fragments.PastEventsFragment;
import kz.djunglestones.organizerappqel.Fragments.StatisticsFragment;

public class MyEventsFragmentPagerAdapter extends FragmentStatePagerAdapter{

    private int numOfTabs;
    Fragment fragment;

    public MyEventsFragmentPagerAdapter(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                fragment = new PastEventsFragment();
                break;
            case 1:
                fragment = new PastEventsFragment();
                break;
            case 2:
                fragment = new DraftEventsFragment();
                break;
            default:
                return null;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
