package com.camera.module;

import 	android.hardware.Camera;
import android.util.Log;

// Always opens and holds a camera instance from the mobile
public class CameraInstance
{
    private static CameraInstance SingletonCameraInstance=null;
    private CameraInstance()
    {

    }

    public static CameraInstance getInstance()
    {
        if(SingletonCameraInstance == null)
        {
            SingletonCameraInstance = new CameraInstance();
        }
            return SingletonCameraInstance;
    }

    public boolean isCameraAvailable()
    {
        boolean returnBoolean = false;
        int cameraCount = Camera.getNumberOfCameras();
        if( Camera.getNumberOfCameras() > 0 )
         {
           returnBoolean = true;
           Log.e("CAMERA COUNT",new String(String.valueOf(cameraCount)) );
         }
         return returnBoolean;
    }

    public void cameraAccess()
    {
        Camera c = null;
        try
        {
            c = Camera.open();

            Log.e("Parameters",c.getParameters().toString());
        }
        catch(Exception e)
        {
            Log.e("Exception",e.getMessage());
        }
        finally
        {
        }

    }

}
