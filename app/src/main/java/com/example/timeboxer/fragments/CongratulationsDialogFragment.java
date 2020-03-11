package com.example.timeboxer.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.timeboxer.R;

public class CongratulationsDialogFragment extends DialogFragment
{
    public static CongratulationsDialogFragment newInstance()
    {
        return new CongratulationsDialogFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.congratulations_dialog_fragment, container, false);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState)
    {
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.congratulations_dialog_fragment,null, false);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(view).setPositiveButton("Ok", null);
        return builder.create();
    }
}
