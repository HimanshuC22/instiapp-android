package app.insti.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import app.insti.Constants;
import app.insti.R;
import app.insti.adapter.BodyAdapter;
import app.insti.api.model.Body;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link BodyRecyclerViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BodyRecyclerViewFragment extends Fragment implements TransitionTargetFragment, TransitionTargetChild {
    public static final String TAG = "BodyRecyclerViewFragment";
    public Fragment parentFragment = null;

    private List<Body> bodyList;

    public BodyRecyclerViewFragment() {
        // Required empty public constructor
    }

    @Override
    public void transitionEnd() {
        if (parentFragment instanceof TransitionTargetFragment) {
            ((TransitionTargetFragment) parentFragment).transitionEnd();
        }
    }

    @Override
    public Fragment getParent() {
        return parentFragment;
    }

    // TODO: Rename and change types and number of parameters
    public static BodyRecyclerViewFragment newInstance(List<Body> bodyList) {
        BodyRecyclerViewFragment fragment = new BodyRecyclerViewFragment();
        Bundle args = new Bundle();
        args.putString(Constants.BODY_LIST_JSON, new Gson().toJson(bodyList));
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            bodyList = new Gson().fromJson(getArguments().getString(Constants.BODY_LIST_JSON
            ), new TypeToken<List<Body>>() {
            }.getType());
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        RecyclerView recyclerView = (RecyclerView) getActivity().findViewById(R.id.body_recycler_view);
        BodyAdapter bodyAdapter = new BodyAdapter(bodyList, this);
        bodyAdapter.uid = "BRVFrag";
        recyclerView.setAdapter(bodyAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_body_recycler_view, container, false);
    }


}