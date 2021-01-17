package cat.itb.userform;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class LoginFragment extends Fragment {
    private TextInputLayout ilUser, ilPass;
    private TextInputEditText etUser, etPass;
    private TextView tvForgot;
    private Button bLogin, bReg;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_login, container, false);

        ilUser = v.findViewById(R.id.loginUser);
        ilPass = v.findViewById(R.id.loginPass);
        etUser = v.findViewById(R.id.etloginUser);
        etPass = v.findViewById(R.id.etloginPass);
        tvForgot = v.findViewById(R.id.tvForgot);
        bLogin = v.findViewById(R.id.bLoginLogin);
        bReg = v.findViewById(R.id.bLoginRegister);

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean ok = true;
                if (etUser.getText().toString().equals("")){
                   ilUser.setError("Cannot leave empty");
                   ok=false;
                }
                if (etPass.getText().toString().equals("")){
                   ilPass.setError("Cannot leave empty");
                   ok=false;
                }
                if (ok){
                    Navigation.findNavController(v).navigate(R.id.action_loginFragment_to_welcomeFragment);
                }

            }
        });
        bReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_loginFragment_to_registerFragment);
            }
        });
        tvForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Forgot your password?");
                builder.setMessage("An email will be sent to you, check spam folder.");
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
            }
        });
        return v;
    }
}