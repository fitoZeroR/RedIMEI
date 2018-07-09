package com.fito.redimei.utils;

import android.app.*;
import android.content.Context;
import android.content.res.Resources;
import android.net.*;
import android.view.inputmethod.InputMethodManager;

import com.fito.redimei.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;

import static android.content.Context.INPUT_METHOD_SERVICE;

/**
 * Created by luisr on 17/10/2017.
 */

public class Tools {
    /**
     * Hides the current open keyboard
     */
    public static void hideKeyboard(Activity activity) {
        try {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(INPUT_METHOD_SERVICE);
            Objects.requireNonNull(imm).hideSoftInputFromWindow(Objects.requireNonNull(activity.getCurrentFocus()).getWindowToken(), 0);
        } catch (Exception ignored) {}
    }

    public static void mensajeInformativo(Activity activity, String mensaje, boolean finalizaActividad) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setMessage(mensaje);
        builder.setPositiveButton(activity.getString(R.string.action_accept), (dialog, which) -> {
            dialog.dismiss();
            if (finalizaActividad) {
                activity.finish();
            }
        });
        builder.setCancelable(false);
        builder.create();
        builder.show();
    }

    public static AlertDialog.Builder mensajeOpcional(Activity activity, String mensaje) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(activity);
        alertDialogBuilder.setTitle(R.string.app_name);
        alertDialogBuilder.setMessage(mensaje);
        alertDialogBuilder.setNegativeButton(R.string.action_cancel, (dialog, which) ->
                dialog.dismiss());
        alertDialogBuilder.setCancelable(false);
        return alertDialogBuilder;
    }

    public static String parsearFechaCumpleanos(String fecha, String formato) {
        try {
            String ArregloFecha[] = fecha.length() > 10 ? fecha.substring(0, 10).split("-") : fecha.split("-");

            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, Integer.parseInt(ArregloFecha[0]));
            calendar.set(Calendar.MONTH, Integer.parseInt(ArregloFecha[1]) - 1);
            calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(ArregloFecha[2]));

            return new SimpleDateFormat(formato).format(calendar.getTime());
        } catch (Exception exception) {
            return fecha;
        }
    }

    public static boolean isConnectionNetwork(Activity activity) {
        NetworkInfo netInfo;
        ConnectivityManager cm = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        try {
            netInfo = Objects.requireNonNull(cm).getActiveNetworkInfo();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return netInfo != null && netInfo.isAvailable();
    }

    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }
}