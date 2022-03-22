package com.atu.diplomas.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.atu.diplomas.R;
import com.atu.diplomas.databinding.FragmentHomeBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;

import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private ConferenceAdapter adapter;
    String TAG = "HomeFragment";
    RecyclerView recyclerView;
    private ArrayList<ConferenceData> arrayList;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View view =  inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        arrayList = new ArrayList<>();
        Log.d(TAG, String.valueOf(arrayList.size()));
        adapter = new ConferenceAdapter(arrayList, getContext());
        recyclerView.setAdapter(adapter);
        showDate();
        return view;
    }

    public void showDate(){
        db.collection("Conferences").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                ConferenceData data =  new ConferenceData(document.get("nameConf").toString(), document.get("dateConf").toString(), document.get("locationConf").toString(), document.get("formaConf").toString(), document.get("languageConf").toString(), document.get("organizatorConf").toString(), document.get("textConf").toString());
                                arrayList.add(data);
                            }
                            Log.d(TAG, String.valueOf(arrayList.size()));

                            adapter.notifyDataSetChanged();
                        } else {
                            Toast.makeText(getContext(), "Error getting documents.", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}