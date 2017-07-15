package dnhieuhuy.hoanghuy.smarttools.flashlight

import android.app.AlertDialog
import android.content.Context
import android.content.pm.PackageManager
import android.hardware.Camera
import android.content.DialogInterface
import android.util.Log





/**
 * Created by Administrator on 05/07/2017.
 */
class SetOnOffFlashLight
{
    private var camera: Camera? = null
    public var isFlashOn: Boolean = false
    private var hasFlash: Boolean = false
    private var params: Camera.Parameters? = null
    private var packageManager: PackageManager? = null

    fun checkHasFlashLight(context: Context)
    {
        hasFlash = context.getPackageManager()
                .hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)
        if (!hasFlash) {
            // device doesn't support flash
            // Show alert message and close the application
            val alert = AlertDialog.Builder(context)
                    .create()
            alert.setTitle("Error")
            alert.setMessage("Sorry, your device doesn't support flash light!")
            alert.setButton("OK", DialogInterface.OnClickListener { dialog, which ->
                // closing the application
                alert.cancel()
            })
            alert.show()
            return
        }
    }

    // getting camera parameters
     fun getCamera() {
        if (camera == null) {
            try {
                camera = Camera.open()
                params = (camera as Camera?)?.getParameters()
            } catch (e: RuntimeException) {
                Log.e("Camera Error. ", e.message)
            }

        }
    }


    /*
* Turning On flash
*/
     fun turnOnFlash() {
            if (camera == null || params == null) {
                return
            }

            params = camera?.getParameters()
            params?.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH)
            camera!!.setParameters(params)
            camera!!.startPreview()
            // changing button/switch image
            isFlashOn = true;
    }


     fun turnOffFlash() {
            if (camera == null || params == null) {
                return
            }
            params = camera?.getParameters()
            params?.setFlashMode(Camera.Parameters.FLASH_MODE_OFF)
            camera!!.setParameters(params)
            camera!!.stopPreview()
            isFlashOn = false;

    }
}
