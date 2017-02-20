package me.sr1.rajawalitutorial.setup;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;

import org.rajawali3d.lights.DirectionalLight;
import org.rajawali3d.materials.Material;
import org.rajawali3d.materials.methods.DiffuseMethod;
import org.rajawali3d.materials.textures.ATexture;
import org.rajawali3d.materials.textures.Texture;
import org.rajawali3d.math.vector.Vector3;
import org.rajawali3d.primitives.Sphere;
import org.rajawali3d.renderer.Renderer;

import me.sr1.rajawalitutorial.R;

/**
 * Created by SR1 on 2017/2/21.
 */

public class SetUpRenderer extends Renderer {

    private static final String TAG = "SetUpRenderer";

    private Context mContext;
    private DirectionalLight mDirectionalLight;
    private Sphere mEarthSphere;

    public SetUpRenderer(Context context) {
        super(context);
        mContext = context;
        setFrameRate(60);
    }

    @Override
    protected void initScene() {
        mDirectionalLight = new DirectionalLight(1f, 0.2f, -1f);
        mDirectionalLight.setColor(1f, 1f, 1f);
        mDirectionalLight.setPower(2);
        getCurrentScene().addLight(mDirectionalLight);

        Material material = new Material();
        material.enableLighting(true);
        material.setDiffuseMethod(new DiffuseMethod.Lambert());
        material.setColor(0);

        Texture earthTexture = new Texture("Earth", R.drawable.earthtruecolor_nasa_big);
        try {
            material.addTexture(earthTexture);
        } catch (ATexture.TextureException ex) {
            Log.w(TAG, "TEXTURE ERROR", ex);
        }

        mEarthSphere = new Sphere(1, 24, 24);
        mEarthSphere.setMaterial(material);
        getCurrentScene().addChild(mEarthSphere);
        getCurrentCamera().setZ(4.2f);
    }

    @Override
    protected void onRender(long ellapsedRealtime, double deltaTime) {
        super.onRender(ellapsedRealtime, deltaTime);
        mEarthSphere.rotate(Vector3.Axis.Y, 1.0);
    }

    @Override
    public void onOffsetsChanged(float xOffset, float yOffset, float xOffsetStep, float yOffsetStep, int xPixelOffset, int yPixelOffset) {

    }

    @Override
    public void onTouchEvent(MotionEvent event) {

    }
}
