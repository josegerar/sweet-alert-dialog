package com.github.josegerar.sweetalert.sample;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

import com.github.josegerar.sweetalert.Constants;
import com.github.josegerar.sweetalert.SweetAlertDialog;

import org.jspecify.annotations.NonNull;

public class SampleActivity extends Activity implements View.OnClickListener {

    private int i = -1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.enableEdgeToEdge(getWindow());
        setContentView(R.layout.sample_activity);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content), new OnApplyWindowInsetsListener() {
            @Override
            public @NonNull WindowInsetsCompat onApplyWindowInsets(@NonNull View v, @NonNull WindowInsetsCompat insets) {
                var bars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(bars.left, bars.top, bars.right, bars.bottom);
                return insets;
            }
        });
        int[] btnIds = {
                R.id.basic_test, R.id.styled_text_and_stroke, R.id.basic_test_without_buttons, R.id.under_text_test,
                R.id.error_text_test, R.id.success_text_test, R.id.warning_confirm_test, R.id.warning_cancel_test,
                R.id.custom_img_test, R.id.progress_dialog, R.id.neutral_btn_test, R.id.disabled_btn_test, R.id.dark_style,
                R.id.custom_view_test, R.id.custom_btn_colors_test
        };
        for (Integer id : btnIds) {
            findViewById(id).setOnClickListener(this);
            findViewById(id).setOnTouchListener(Constants.FOCUS_TOUCH_LISTENER);
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.basic_test) {
            SweetAlertDialog sd = new SweetAlertDialog.Builder(this)
                    .setContentText("Here's a message")
                    .setCancelable(true)
                    .build();
            sd.setCanceledOnTouchOutside(true);
            sd.show();
        } else if (id == R.id.basic_test_without_buttons) {
            SweetAlertDialog sd2 = new SweetAlertDialog.Builder(this)
                    .setContentText("Here's a message")
                    .build();
            sd2.setCancelable(true);
            sd2.setCanceledOnTouchOutside(true);
            sd2.show();
        } else if (id == R.id.under_text_test) {
            new SweetAlertDialog.Builder(this)
                    .setTitleText("Title")
                    .setContentText("It's pretty, isn't it?")
                    .show();
        } else if (id == R.id.styled_text_and_stroke) {
            new SweetAlertDialog.Builder(this)
                    .setTitleText("<font color='red'>Red</font> title", true)
                    .setContentText("Big <font color='green'>green </font><b><i> bold</i></b>", true)
                    .setContentTextSize(21)
                    .setStrokeWidth(2)
                    .show();
        } else if (id == R.id.error_text_test) {
            new SweetAlertDialog.Builder(this)
                    .setAlertType(SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Oops...")
                    .setContentText("Something went wrong!")
                    .show();
        } else if (id == R.id.success_text_test) {
            new SweetAlertDialog.Builder(this)
                    .setAlertType(SweetAlertDialog.SUCCESS_TYPE)
                    .setTitleText("Good job!")
                    .setContentText("You clicked the button!")
                    .show();
        } else if (id == R.id.warning_confirm_test) {
            new SweetAlertDialog.Builder(this)
                    .setAlertType(SweetAlertDialog.WARNING_TYPE)
                    .setTitleText("Are you sure?")
                    .setContentText("Won't be able to recover this file!")
                    .setCancelText("Yes, delete it!")
                    .setCancelClickListener(sweetAlertDialog -> {
                        // reuse previous dialog instance
                        sweetAlertDialog.setTitleText("Deleted!")
                                .setContentText("Your imaginary file has been deleted!")
                                .setConfirmClickListener(null)
                                .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                    })
                    .show();
        } else if (id == R.id.warning_cancel_test) {
            new SweetAlertDialog.Builder(this)
                    .setAlertType(SweetAlertDialog.WARNING_TYPE)
                    .setTitleText("Are you sure?")
                    .setContentText("Won't be able to recover this file!")
                    .setCancelText("No, cancel pls!")
                    .setConfirmText("Yes, delete it!")
                    .setCancelClickListener(sDialog -> {
                        // reuse previous dialog instance, keep widget user state, reset them if you need
                        sDialog.setTitleText("Cancelled!")
                                .setContentText("Your imaginary file is safe :)")
                                .setConfirmText("OK")
                                .showCancelButton(false)
                                .setCancelClickListener(null)
                                .setConfirmClickListener(null)
                                .changeAlertType(SweetAlertDialog.ERROR_TYPE);
                    })
                    .setConfirmClickListener(sDialog -> sDialog.setTitleText("Deleted!")
                            .setContentText("Your imaginary file has been deleted!")
                            .setConfirmText("OK")
                            .showCancelButton(false)
                            .setCancelClickListener(null)
                            .setConfirmClickListener(null)
                            .changeAlertType(SweetAlertDialog.SUCCESS_TYPE))
                    .show();
        } else if (id == R.id.custom_img_test) {
            new SweetAlertDialog.Builder(this)
                    .setAlertType(SweetAlertDialog.CUSTOM_IMAGE_TYPE)
                    .setTitleText("Sweet!")
                    .setContentText("Here's a custom image.")
                    .setCustomImage(getResources().getDrawable(R.drawable.custom_img))
                    .show();
        } else if (id == R.id.progress_dialog) {
            final SweetAlertDialog pDialog = new SweetAlertDialog.Builder(this)
                    .setAlertType(SweetAlertDialog.PROGRESS_TYPE)
                    .setTitleText("Loading")
                    .build();
            pDialog.show();
            pDialog.setCancelable(false);
            new CountDownTimer(800 * 7, 800) {
                public void onTick(long millisUntilFinished) {
                    // you can change the progress bar color by ProgressHelper every 800 millis
                    i++;
                    switch (i) {
                        case 0:
                            pDialog.getProgressHelper().setBarColor(getResources().getColor(com.github.josegerar.sweetalert.R.color.blue_btn_bg_color));
                            break;
                        case 1:
                            pDialog.getProgressHelper().setBarColor(getResources().getColor(com.github.josegerar.sweetalert.R.color.material_deep_teal_50));
                            break;
                        case 2:
                            pDialog.getProgressHelper().setBarColor(getResources().getColor(com.github.josegerar.sweetalert.R.color.success_stroke_color));
                            break;
                        case 3:
                            pDialog.getProgressHelper().setBarColor(getResources().getColor(com.github.josegerar.sweetalert.R.color.material_deep_teal_20));
                            break;
                        case 4:
                            pDialog.getProgressHelper().setBarColor(getResources().getColor(com.github.josegerar.sweetalert.R.color.material_blue_grey_80));
                            break;
                        case 5:
                            pDialog.getProgressHelper().setBarColor(getResources().getColor(com.github.josegerar.sweetalert.R.color.warning_stroke_color));
                            break;
                        case 6:
                            pDialog.getProgressHelper().setBarColor(getResources().getColor(com.github.josegerar.sweetalert.R.color.success_stroke_color));
                            break;
                    }
                }

                public void onFinish() {
                    i = -1;
                    pDialog.setTitleText("Success!")
                            .setConfirmText("OK")
                            .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                }
            }.start();
        } else if (id == R.id.neutral_btn_test) {
            new SweetAlertDialog.Builder(this)
                    .setTitleText("Title")
                    .setContentText("Three buttons dialog \n with neutral button")
                    .setConfirmText("Confirm")
                    .setCancelText("Cancel")
                    .setNeutralText("Neutral")
                    .show();
        } else if (id == R.id.disabled_btn_test) {
            final SweetAlertDialog disabledBtnDialog = new SweetAlertDialog.Builder(this)
                    .setTitleText("Title")
                    .setContentText("Disabled button dialog")
                    .setConfirmText("OK")
                    .setCancelText("Cancel")
                    .setNeutralText("Neutral")
                    .build();

            disabledBtnDialog.setOnShowListener(dialog -> disabledBtnDialog.getButton(SweetAlertDialog.BUTTON_CONFIRM).setEnabled(false));
            disabledBtnDialog.show();
        } else if (id == R.id.dark_style) {
            if (((CheckBox) v).isChecked()) {
                SweetAlertDialog.DARK_STYLE = true;
            } else {
                SweetAlertDialog.DARK_STYLE = false;
            }
        } else if (id == R.id.custom_view_test) {
            final EditText editText = new EditText(this);
            final CheckBox checkBox = new CheckBox(this);
            editText.setText("Some edit text");
            checkBox.setChecked(true);
            checkBox.setText("Some checkbox");

            if (SweetAlertDialog.DARK_STYLE) {
                editText.setTextColor(Color.WHITE);
                checkBox.setTextColor(Color.WHITE);
            }

            LinearLayout linearLayout = new LinearLayout(getApplicationContext());
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            linearLayout.addView(editText);
            linearLayout.addView(checkBox);

            new SweetAlertDialog.Builder(this)
                    .setTitleText("Custom view")
                    .setCustomView(linearLayout)
                    .show();
        } else if (id == R.id.custom_btn_colors_test) {
            new SweetAlertDialog.Builder(this)
                    .setTitleText("Custom view")
                    .setCancelText("red")
                    .setCancelButtonBackgroundColor(Color.RED)
                    .setNeutralText("cyan")
                    .setNeutralButtonBackgroundColor(Color.CYAN)
                    .setConfirmText("blue")
                    .setConfirmButtonBackgroundColor(Color.BLUE)
                    .show();
        }
    }
}
