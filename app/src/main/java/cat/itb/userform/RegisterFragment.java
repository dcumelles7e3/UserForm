package cat.itb.userform;

import android.os.Build;
import android.os.Bundle;


import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class RegisterFragment extends Fragment {
    private TextInputLayout ilUser, ilPass, ilRepPass, ilEmail, ilName, ilBirth, ilPronoun;
    private TextInputEditText etUser, etPass, etRepPass, etEmail, etName, etBirth;
    private AutoCompleteTextView etPronoun;
    private Button bReg, bLogin;

    private ArrayList<String> pronounList;
    private ArrayAdapter<String> pronounAdapter;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_register, container, false);

        ilUser = v.findViewById(R.id.regUser);
        ilPass = v.findViewById(R.id.regPass);
        ilRepPass = v.findViewById(R.id.regRepPass);
        ilEmail = v.findViewById(R.id.regEmail);
        ilName = v.findViewById(R.id.regName);
        ilBirth = v.findViewById(R.id.regBirth);
        ilPronoun = v.findViewById(R.id.regPronoun);

        etUser = v.findViewById(R.id.etregUser);
        etPass = v.findViewById(R.id.etregPass);
        etRepPass = v.findViewById(R.id.etregRepPass);
        etEmail = v.findViewById(R.id.etregEmail);
        etName = v.findViewById(R.id.etregName);
        etBirth = v.findViewById(R.id.etregBirth);
        etPronoun = v.findViewById(R.id.etregPronoun);

        bReg = v.findViewById(R.id.regBReg);
        bLogin = v.findViewById(R.id.regBLogin);

        pronounList=new ArrayList<>();
        pronounList.add("Mr.");
        pronounList.add("Ms.");
        pronounList.add("Miss.");
        pronounList.add("Dr.");

        pronounAdapter=new ArrayAdapter<>(v.getContext(),R.layout.support_simple_spinner_dropdown_item,pronounList);
        etPronoun.setAdapter(pronounAdapter);
        etPronoun.setThreshold(1);
        etPronoun.setShowSoftInputOnFocus(false);

        etUser.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 0) {
                    ilUser.setError("Cannot leave empty");
                } else {
                    ilUser.setError(null);
                }
            }
        });

        etPass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() < 8) {
                    ilPass.setError("Must be at least 8 characters");
                } else {
                    ilPass.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        etRepPass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().equals(etPass.getText().toString())) {
                    ilRepPass.setError("Passwords must match");
                } else {
                    ilRepPass.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        etEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 0) {
                    ilEmail.setError("Cannot leave empty");
                } else {
                    ilEmail.setError(null);
                }
            }
        });

        etBirth.setShowSoftInputOnFocus(false);
        etBirth.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    MaterialDatePicker.Builder<Long> builder = MaterialDatePicker.Builder.datePicker();
                    builder.setTitleText("Date");
                    final MaterialDatePicker<Long> picker = builder.build();
                    picker.show(getParentFragmentManager(), picker.toString());
                    if (picker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Long>() {
                        @Override
                        public void onPositiveButtonClick(Long selection) {
                            etBirth.setText(String.valueOf(picker.getHeaderText()));
                        }
                    })) ;
                }
            }
        });


        bReg.setOnClickListener(new View.OnClickListener() {
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
                if (etEmail.getText().toString().equals("")){
                    ilEmail.setError("Cannot leave empty");
                    ok=false;
                }
                if (ok){
                    Navigation.findNavController(v).navigate(R.id.action_registerFragment_to_welcomeFragment);
                }else {
                    Toast.makeText(v.getContext(), "Please fill in all the required fields", Toast.LENGTH_SHORT).show();
                }
            }
        });

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_registerFragment_to_loginFragment);
            }
        });

        return v;
    }
}