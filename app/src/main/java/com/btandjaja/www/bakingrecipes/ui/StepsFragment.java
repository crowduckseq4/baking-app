package com.btandjaja.www.bakingrecipes.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.btandjaja.www.bakingrecipes.R;
import com.btandjaja.www.bakingrecipes.data.InstructionAdapter;
import com.btandjaja.www.bakingrecipes.data.Recipe;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StepsFragment extends Fragment {
    // define a new interface OnStepClickListener that triggers a callback to the host activity
    OnStepClickListener mCallback;
    Recipe mRecipe;

    @BindView(R.id.rv_recipe_instruction_list) RecyclerView mRecyclerView;

    // OnStepClickListener interface, calls a method in the host activity named onStepSelected
    public interface OnStepClickListener {
        void onStepSelected(Recipe recipe);
    }

    // Override onAttach to make sure that the container activity has implemented the callback
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // This makes sure that the host activity has implemented the callback interface
        // If not, it throws an exception
        try {
            mCallback = (OnStepClickListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnStepClickListener");
        }
    }

    public StepsFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_recipe_steps_list, container, false);
        ButterKnife.bind(getContext(), rootView);
        InstructionAdapter instructionAdapter = new InstructionAdapter(getContext(), mRecipe);
        mRecyclerView.setAdapter(instructionAdapter);
        return rootView;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelable(Recipe.RECIPE, mRecipe);
    }
}
