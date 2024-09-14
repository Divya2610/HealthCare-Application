package com.example.healthcareminiproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class AppointmentRequestsAdapter extends RecyclerView.Adapter<AppointmentRequestsAdapter.ViewHolder> {

    private List<AppointmentRequestsActivity.AppointmentRequest> appointmentRequests;

    public AppointmentRequestsAdapter(List<AppointmentRequestsActivity.AppointmentRequest> appointmentRequests) {
        this.appointmentRequests = appointmentRequests;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_appointment_request, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AppointmentRequestsActivity.AppointmentRequest request = appointmentRequests.get(position);
        holder.textViewPatientName.setText(request.patientName);
        holder.textViewPatientEmail.setText(request.patientEmail);
        holder.textViewPatientPhone.setText(request.patientPhone);
        holder.textViewPatientIllness.setText(request.patientIllness);
        holder.textViewTimeSlot.setText(request.timeSlot);
    }

    @Override
    public int getItemCount() {
        return appointmentRequests.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewPatientName;
        TextView textViewPatientEmail;
        TextView textViewPatientPhone;
        TextView textViewPatientIllness;
        TextView textViewTimeSlot;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewPatientName = itemView.findViewById(R.id.textViewPatientName);
            textViewPatientEmail = itemView.findViewById(R.id.textViewPatientEmail);
            textViewPatientPhone = itemView.findViewById(R.id.textViewPatientPhone);
            textViewPatientIllness = itemView.findViewById(R.id.textViewPatientIllness);
            textViewTimeSlot = itemView.findViewById(R.id.textViewTimeSlot);
        }
    }
}