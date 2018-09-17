package kz.djunglestones.organizerappqel;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class PopUpFragmentTheme extends DialogFragment{

    RecyclerView recyclerView;
    PopUpThemeRecyclerAdapter popUpThemeRecyclerAdapter;
    private List<PopUpThemeClass> popUpThemeClassList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.pop_up_themes,container);
        addThemes();
        recyclerView = rootView.findViewById(R.id.pop_up_themes_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        popUpThemeRecyclerAdapter = new PopUpThemeRecyclerAdapter(this.getActivity(),popUpThemeClassList);
        recyclerView.setAdapter(popUpThemeRecyclerAdapter);

        this.getDialog().setTitle("{azca}");

        return rootView;
    }

    private void addThemes() {
        popUpThemeClassList = new ArrayList<>();
        popUpThemeClassList.add(new PopUpThemeClass("Конференция",R.drawable.microphone_theme));
        popUpThemeClassList.add(new PopUpThemeClass("Конференция",R.drawable.microphone_theme));
        popUpThemeClassList.add(new PopUpThemeClass("Конференция",R.drawable.microphone_theme));
        popUpThemeClassList.add(new PopUpThemeClass("Конференция",R.drawable.microphone_theme));
        popUpThemeClassList.add(new PopUpThemeClass("Конференция",R.drawable.microphone_theme));
        popUpThemeClassList.add(new PopUpThemeClass("Конференция",R.drawable.microphone_theme));
        popUpThemeClassList.add(new PopUpThemeClass("Конференция",R.drawable.microphone_theme));
    }
}
