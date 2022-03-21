package com.atu.diplomas.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.atu.diplomas.R;
import com.atu.diplomas.databinding.FragmentHomeBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private ConferenceAdapter adapter;
    private ArrayList<ConferenceData> arrayList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View view =  inflater.inflate(R.layout.fragment_home, container, false);
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        arrayList = new ArrayList<>();
        adapter = new ConferenceAdapter(arrayList, getContext());
        binding.recyclerView.setAdapter(adapter);

//        db.collection("Conferences").get()
//                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//                    @Override
//                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//                        if (!queryDocumentSnapshots.isEmpty()) {
//                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
//
//                            for (DocumentSnapshot d : list) {
//                                Toast.makeText(getContext(), list.toString(), Toast.LENGTH_LONG);
//                                    ConferenceData data = d.toObject(ConferenceData.class);
//                                    arrayList.add(data);
//                            }
//                            adapter.notifyDataSetChanged();
//                        }
//                    }
//                }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Toast.makeText(getContext(), "Error getting documents." + e.toString(), Toast.LENGTH_LONG).show();
//            }
//        });

        db.collection("Conferences").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                arrayList.add(new ConferenceData(document.get("nameConf").toString(), document.get("dateConf").toString(), document.get("locationConf").toString(), document.get("formaConf").toString(), document.get("languageConf").toString(), document.get("organizatorConf").toString(), document.get("textConf").toString()));
                                Toast.makeText(getContext(), document.getId() + " => " + arrayList.toString(), Toast.LENGTH_LONG).show();

                            }
                            adapter.notifyDataSetChanged();
                        } else {
                            Toast.makeText(getContext(), "Error getting documents.", Toast.LENGTH_LONG).show();
                        }
                    }
                });

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}