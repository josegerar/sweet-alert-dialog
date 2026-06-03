package com.github.josegerar.sweetalert;

import android.content.DialogInterface;
import android.os.Handler;
import android.os.Looper;
import java.util.LinkedList;
import java.util.Queue;

public class SweetAlertQueue {
    private final Queue<SweetAlertDialog.Builder> queue = new LinkedList<>();
    private SweetAlertDialog currentDialog;

    public SweetAlertQueue add(SweetAlertDialog.Builder builder) {
        queue.add(builder);
        return this;
    }

    public void showNext() {
        if (queue.isEmpty()) {
            currentDialog = null;
            return;
        }

        SweetAlertDialog.Builder builder = queue.poll();
        if (builder == null) return;

        currentDialog = builder.build();
        
        final DialogInterface.OnDismissListener originalDismiss = currentDialog.getOnDismissListener();
        currentDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                if (originalDismiss != null) {
                    originalDismiss.onDismiss(dialogInterface);
                }

                // Post to next loop to ensure current dismissal is complete
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        showNext();
                    }
                });
            }
        });

        currentDialog.show();
    }
    
    public SweetAlertDialog getCurrentDialog() {
        return currentDialog;
    }
}
