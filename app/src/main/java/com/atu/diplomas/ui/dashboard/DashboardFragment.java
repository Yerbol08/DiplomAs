package com.atu.diplomas.ui.dashboard;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.atu.diplomas.databinding.FragmentDashboardBinding;
import com.atu.diplomas.ui.home.ConferenceData;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import es.dmoral.toasty.Toasty;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        MaterialDatePicker.Builder materialDateBuilder = MaterialDatePicker.Builder.datePicker();
        materialDateBuilder.setTitleText("SELECT A DATE");
        final MaterialDatePicker materialDatePicker = materialDateBuilder.build();

        binding.inputDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                materialDatePicker.show(getParentFragmentManager(), "MATERIAL_DATE_PICKER");
            }
        });

        materialDatePicker.addOnPositiveButtonClickListener(
                new MaterialPickerOnPositiveButtonClickListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onPositiveButtonClick(Object selection) {
                        binding.inputDate.setText("???????????????? ????????");
                        binding.showDate.setVisibility(View.VISIBLE);
                        binding.showDate.setText(materialDatePicker.getHeaderText());
                    }
                });

        binding.addConference.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameConf = String.valueOf(binding.inputNameConf.getText());
                String dateConf = String.valueOf(binding.showDate.getText());
                String languages = String.valueOf(binding.inputLanguagesConf.getText());
                String location = String.valueOf(binding.inputLocationConf.getText());
                String formaConf = String.valueOf(binding.inputFormaConf.getText());
                String organizer = String.valueOf(binding.inputOrgConf.getText());
                String textConf = String.valueOf(binding.inputTextConf.getText());
                if (nameConf.equals("")){
                    binding.inputNameConf.setError("?????????????? ???????????????? ?????? ??????????????????????!");
                }else if (dateConf.equals("")){
                    binding.inputDate.setError("???????????????? ???????? ??????????????????????!");
                }
                else if (languages.equals("")){
                    binding.inputLanguagesConf.setError("?????????????? ???????? ????????????????????!");
                }
                else if (location.equals("")){
                    binding.inputLocationConf.setError("?????????????? ???????????????????????????? ??????????????????????!");
                }
                else if (formaConf.equals("")){
                    binding.inputFormaConf.setError("?????????????? ?????????? ??????????????!");
                }
                else if (organizer.equals("")){
                    binding.inputOrgConf.setError("?????????????? ?????????????????????????? ??????????????????????!");
                }
                else if (textConf.equals("")){
                    binding.textDashboard.setError("?????????????? ???????????????? ??????????????????????!");
                }
                else {
                    addDateFireStore(nameConf, dateConf, location,formaConf, languages,organizer,textConf);
                }
            }
        });
        return root;

    }

    public void addDateFireStore(String name, String dateConf, String location, String formaConf, String language, String organizer, String textCoonf){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference dbConference = db.collection("Conferences");
        ConferenceData data = new ConferenceData(name, dateConf,location, formaConf, language, organizer, textCoonf);
        dbConference.add(data).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toasty.success(getContext(), "?????????????????????? ??????????????????!",
                        Toast.LENGTH_SHORT, true).show();
                binding.inputNameConf.setText("");
                binding.inputDate.setText("");
                binding.inputLocationConf.setText("");
                binding.inputFormaConf.setText("");
                binding.inputLanguagesConf.setText("");
                binding.inputOrgConf.setText("");
                binding.inputTextConf.setText("");
          }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toasty.error(getContext(), "Fail to add course \n" + e,Toast.LENGTH_SHORT, true).show();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}