package com.example.tourism.view.catefragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.tourism.R;
import com.example.tourism.contract.FoodSmallCateContract;
import com.example.tourism.databinding.CateTourFragmentBinding;
import com.example.tourism.viewmodel.catefragment.FoodCateFragmentViewModel;

import java.util.Optional;

public class CateTourFragment extends Fragment implements FoodSmallCateContract {
    private static final String TAG = CateTourFragment.class.getSimpleName();
    private CateTourFragmentBinding binding;
    private FoodCateFragmentViewModel viewModel;
    private NavController controller;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.cate_tour_fragment,container,false);
        setupView();
        return  binding.getRoot();
    }

    private void setupView() {
        binding.setClick(new FoodCateFragmentViewModel(getActivity().getApplication(),this));
        viewModel = binding.getClick();

    }

    @Override
    public void btnClick(View view) {
        Button btn = (Button)view;
        Bundle bundle = new Bundle();
        Log.d(TAG, "btnClick: "+btn.getText().toString());
        bundle.putString("smallTourCate",btn.getText().toString());
        try {
            NavHostFragment host = Optional.ofNullable((NavHostFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.view_controller)).orElseThrow(Exception::new);
            controller = host.getNavController();
            controller.navigate(R.id.action_homeFragment_to_tourActivity,bundle);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}
