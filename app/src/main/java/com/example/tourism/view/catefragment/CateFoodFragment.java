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
import com.example.tourism.databinding.CateFoodFragmentBinding;
import com.example.tourism.viewmodel.catefragment.FoodCateFragmentViewModel;

import java.util.Optional;

public class CateFoodFragment extends Fragment implements FoodSmallCateContract {
    private static final String TAG = CateFoodFragment.class.getSimpleName();
    private CateFoodFragmentBinding binding;
    private FoodCateFragmentViewModel viewModel;
    private NavController controller;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.cate_food_fragment,container,false);

        setup();
        return binding.getRoot();
    }

    private void setup() {
        binding.setClick(new FoodCateFragmentViewModel(getActivity().getApplication(),this));
        viewModel = binding.getClick();
        binding.row.setClickable(true);
    }

    @Override
    public void btnClick(View view) {
        Button btn = (Button)view;
        Bundle bundle = new Bundle();
        bundle.putString("smallCate",btn.getText().toString());
        try {
            NavHostFragment host = Optional.ofNullable((NavHostFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.view_controller)).orElseThrow(Exception::new);
            controller = host.getNavController();
            controller.navigate(R.id.action_homeFragment_to_foodActivity,bundle);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

    }
}
