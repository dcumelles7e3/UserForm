package cat.itb.userform;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import androidx.navigation.Navigation;


public class StartScreenFragment extends Fragment implements View.OnClickListener {
    private Button bLogin, bRegister;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.start_screen, container, false);

        bLogin = v.findViewById(R.id.bStartLogin);
        bRegister = v.findViewById(R.id.bStartRegister);
        bLogin.setOnClickListener(this);
        bRegister.setOnClickListener(this);

        return v;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bStartLogin:
                Navigation.findNavController(v).navigate(R.id.action_startScreenFragment_to_loginFragment);
                break;
            case R.id.bStartRegister:
                Navigation.findNavController(v).navigate(R.id.action_startScreenFragment_to_registerFragment);
                break;
        }
    }
}

