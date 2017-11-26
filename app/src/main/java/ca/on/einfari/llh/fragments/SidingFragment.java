/*
    SidingFragment.java
    Assignment 1

    Revision History:
        Gonzalo Ramos Zúñiga, 2017.09.22: Created
 */

package ca.on.einfari.llh.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ca.on.einfari.llh.R;

public class SidingFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_siding, container, false);
    }

}