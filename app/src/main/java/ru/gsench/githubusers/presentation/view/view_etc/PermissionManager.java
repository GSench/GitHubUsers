package ru.gsench.githubusers.presentation.view.view_etc;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import ru.gsench.githubusers.R;
import ru.gsench.githubusers.domain.utils.function;

/**
 * Created by Григорий Сенченок on 10.03.2017.
 */

public class PermissionManager {

    private static final int WRITE_EXTERNAL_STORAGE = 1;

    private Activity act;
    private function onOKFunction;
    private function lastPermission;

    public PermissionManager(Activity act){
        this.act=act;
    }

    public void requestBasePermissions(final Activity act, final function doAfter){
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M&&!writeExternalPermGranted(act)){
            new AlertDialog.Builder(act)
                    .setMessage(R.string.write_permission_msg)
                    .setPositiveButton(act.getString(R.string.ok), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                            ActivityCompat.requestPermissions(act, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, WRITE_EXTERNAL_STORAGE);
                            onOKFunction = doAfter;
                            lastPermission = new function() {
                                @Override
                                public void run(String... params) {
                                    requestBasePermissions(act, doAfter);
                                }
                            };
                        }
                    })
                    .setNeutralButton(act.getString(R.string.cancel), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                            act.finish();
                        }
                    })
                    .setCancelable(false)
                    .create()
                    .show();
        } else {
            doAfter.run();
        }
    }

    private static boolean writeExternalPermGranted(Context context) {
        return (context.checkCallingOrSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED);
    }

    public void onPermissionCallback(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case WRITE_EXTERNAL_STORAGE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if(onOKFunction==null) return;
                    onOKFunction.run();
                    onOKFunction =null;
                } else {
                    Toast.makeText(act, act.getString(R.string.write_permission_denied), Toast.LENGTH_SHORT).show();
                    if(lastPermission==null) return;
                    lastPermission.run();
                    lastPermission=null;
                }
            }
        }
    }
}
