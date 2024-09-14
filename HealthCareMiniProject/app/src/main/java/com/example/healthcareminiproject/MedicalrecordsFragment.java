package com.example.healthcareminiproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MedicalrecordsFragment extends Fragment {
    private RecyclerView recyclerViewAppointments;
    private AppointmentAdapter appointmentAdapter;
    private List<AppointmentData> appointmentDataList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_medicalrecords, container, false);

        recyclerViewAppointments = view.findViewById(R.id.recyclerViewAppointments);
        recyclerViewAppointments.setLayoutManager(new LinearLayoutManager(requireContext()));

        appointmentDataList = new ArrayList<>();
        appointmentAdapter = new AppointmentAdapter(appointmentDataList);
        recyclerViewAppointments.setAdapter(appointmentAdapter);

        loadAppointmentData();

        return view;
    }

    private void loadAppointmentData() {
        DBHelper_records_database dbHelper = new DBHelper_records_database(requireContext());
        List<AppointmentData> appointmentData = dbHelper.getAllAppointmentRequests();

        appointmentDataList.clear();
        appointmentDataList.addAll(appointmentData);
        appointmentAdapter.notifyDataSetChanged();
    }

    private static class AppointmentAdapter extends RecyclerView.Adapter<AppointmentAdapter.AppointmentViewHolder> {
        private List<AppointmentData> appointmentDataList;

        public AppointmentAdapter(List<AppointmentData> appointmentDataList) {
            this.appointmentDataList = appointmentDataList;
        }

        @NonNull
        @Override
        public AppointmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_appointment, parent, false);
            return new AppointmentViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull AppointmentViewHolder holder, int position) {
            AppointmentData appointmentData = appointmentDataList.get(position);
            holder.bind(appointmentData);
        }

        @Override
        public int getItemCount() {
            return appointmentDataList.size();
        }

        static class AppointmentViewHolder extends RecyclerView.ViewHolder {
            private TextView textViewPatientName;
            private TextView textViewPatientEmail;
            private TextView textViewPatientPhoneNumber;
            private TextView textViewPatientIllness;
            private TextView textViewTimeSlot;

            public AppointmentViewHolder(@NonNull View itemView) {
                super(itemView);
                textViewPatientName = itemView.findViewById(R.id.textViewPatientName);
                textViewPatientEmail = itemView.findViewById(R.id.textViewPatientEmail);
                textViewPatientPhoneNumber = itemView.findViewById(R.id.textViewPatientPhoneNumber);
                textViewPatientIllness = itemView.findViewById(R.id.textViewPatientIllness);
                textViewTimeSlot = itemView.findViewById(R.id.textViewTimeSlot);
            }

            public void bind(AppointmentData appointmentData) {
                textViewPatientName.setText(appointmentData.getPatientName());
                textViewPatientEmail.setText(appointmentData.getPatientEmail());
                textViewPatientPhoneNumber.setText(appointmentData.getPatientPhoneNumber());
                textViewPatientIllness.setText(appointmentData.getPatientIllness());
                textViewTimeSlot.setText(appointmentData.getTimeSlot());
            }
        }
    }
}